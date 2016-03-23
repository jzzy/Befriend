package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.befriend.dao.CollectDAO;
import com.befriend.dao.EduCommentDAO;
import com.befriend.dao.EduServicesDAO;
import com.befriend.dao.FollectDAO;
import com.befriend.dao.ForumDAO;
import com.befriend.dao.NewsDAO;
import com.befriend.dao.ReviewDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Attention;
import com.befriend.entity.Collect;
import com.befriend.entity.EduActivity;
import com.befriend.entity.EduComment;
import com.befriend.entity.EduServices;
import com.befriend.entity.Follect;
import com.befriend.entity.ForumOne;
import com.befriend.entity.ForumThree;
import com.befriend.entity.ForumTwo;
import com.befriend.entity.News;
import com.befriend.entity.Review;
import com.befriend.entity.User;
import com.befriend.util.JsonUtil;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;

public class EduCommentAction implements ServletRequestAware {

	private EduCommentDAO eduCommentDAO;
	private EduServicesDAO eduServicesDAO;
	private UserDAO userDAO;
	private NewsDAO newsDAO;
	private CollectDAO collectDAO;
	private ForumDAO forumdao;// 论坛dao
	private FollectDAO foldao;// 论坛收藏dao
	private ReviewDAO rdao;// 评论dao

	List<User> us = new ArrayList<User>();
	Follect fo = new Follect();// 论坛收藏类
	ForumOne fone = new ForumOne();// 论坛主类
	ForumTwo ftwo = new ForumTwo();// 论坛主页类
	ForumThree fehree = new ForumThree();// 论坛回复类
	List<Follect> fos = new ArrayList<Follect>();// 论坛收藏类List
	List<ForumOne> fones = new ArrayList<ForumOne>();// 论坛主类List
	List<ForumTwo> ftwos = new ArrayList<ForumTwo>();// 论坛主类List
	List<ForumThree> fehrees = new ArrayList<ForumThree>();// 论坛主类List

	private HttpServletResponse response;
	private HttpServletRequest request;
	private String merchantId;
	private int score = 0;
	private String userId;
	private String replyId;
	private String content;
	private String fatherId;
	private String path;
	private File[] pictures;
	private String[] picturesFileName;
	private String[] picturesContentType;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private int currentPage = 1;
	private int pageSize = 10;

	List<EduActivity> eaal = new ArrayList<EduActivity>();
	List<EduServices> edusl = new ArrayList<EduServices>();
	List<EduComment> educl = new ArrayList<EduComment>();
	List<Attention> attl = new ArrayList<Attention>();
	List<News> nl = new ArrayList<News>();
	List<Review> rl = new ArrayList<Review>();
	private int id;

	public void webRemoveReviews() throws IOException {
		try {

			String[] st = fatherId.split(",");
			for (int i = 0; i < st.length; i++) {
				if (OpeFunction.isEmpty(st[i])) {
					continue;
				}
				id = Integer.parseInt(st[i].toString());
				switch (content) {
				case "F":
					ftwo = forumdao.getForumTwoid(id);
					if (ftwo != null) {
						forumdao.Remove(ftwo);
						fone=forumdao.getForumOne(ftwo.getForumid());
						if(fone!=null){
							fone.setFrs(forumdao.getForumTwoALL(ftwo.getForumid()).size());
							forumdao.update(fone);
						}
						
						System.out.println("删除论坛评论成功");
						// OpeFunction.Out().println(true);
						// return;
					} else {
						System.out.println("删除论坛评论失败");
					}

					break;
				case "E":

					EduComment educ = eduCommentDAO.find(id);
					if (educ != null) {
						eduCommentDAO.delete(educ);
						System.out.println("删除教辅机构评论成功");
						// OpeFunction.Out().println(true);
						// return;
					} else {
						System.out.println("删除教辅机构评论失败");
					}

					break;
				case "N":
					Review r = rdao.byid(id);
					if (r != null) {
						rdao.remove(r);
						News n=newsDAO.byid(r.getNewsid());
						if(n!=null){
						n.setReviews(rdao.Alln(r.getNewsid()).size());
						newsDAO.Upnews(n);
						}
						
						System.out.println("删除新闻评论成功");
						// OpeFunction.Out().println(true);
						// return;
					} else {
						System.out.println("删除新闻评论失败");
					}

					break;

				default:
					System.out.println("不知道删除什么");
					break;
				}
			}
			OpeFunction.Out().println(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			OpeFunction.Out().println(false);
		}

	}

	/**
	 * 通过username查询评论 新闻和对每个新闻的最新 评论
	 * 
	 * @throws IOException
	 */
	public String webEduReviews() throws IOException {

		User u = (User) session.getAttribute("u");

		if (u == null) {

			((HttpServletResponse) OpeFunction.response())
					.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
			return null;
		}

		String username = u.getUsername();

		List<Integer> rn = new ArrayList<Integer>();// 收藏 List
		List<Review> rel=rdao.Allu(username);
		for (Review r1 : rdao.Allu(username)) {

			Boolean b = true;

//			for (int i = 0; i < rn.size(); i++) {
//
//				if (rn.get(i) == r1.getNewsid()) {
//
//				//	b = false;
//				}
//
//			}

//			if (b) {
//				//System.out.println("我评论过的新闻  用户名和新闻id" + username + "+" + r1.getNewsid() + "时间是：" + r1.getTime());
//				rl.add(rdao.unid(username, r1.getNewsid()).get(0));
//				nl.add(newsDAO.byid(r1.getNewsid()));
//			}
			nl.add(newsDAO.byid(r1.getNewsid()));
			rn.add(r1.getNewsid());

		}

		request.setAttribute("nl", nl);
		request.setAttribute("rl", rel);
		System.out.println("nl"+nl.size()+"rl"+rl.size());
		int userid = u.getId();

		ftwos = forumdao.getFuserALL(userid);
		List<Integer> l = new ArrayList<Integer>();
		List<ForumTwo> fow = new ArrayList<ForumTwo>();
		Boolean b = true;
		for (int i = 0; i < ftwos.size(); i++) {

//			for (int y = 0; y < l.size(); y++) {
//			//	System.out.println("y=" + y);
//				if (l.get(y) == ftwos.get(i).getForumid()) {
//
//				//	b = false;
//					break;
//				}
//			}
			//System.out.println("b=" + b);

			if (b) {
				fow.add(ftwos.get(i));
				fones.add(forumdao.getForumOne(ftwos.get(i).getForumid()));
				l.add(ftwos.get(i).getForumid());
			}
			b = true;

		}

		for (int i = 0; i < fones.size(); i++) {
			//System.out.println("id" + fones.get(i).getId());

			User uf = userDAO.byid(fones.get(i).getUserid());

			us.add(uf);

		}

		System.out.println("ftwos" + fow.size());
		System.out.println("用户" + us.size());
		System.out.println("论坛" + fones.size());
		request.setAttribute("fow", fow);
		request.setAttribute("us", us);
		request.setAttribute("fones", fones);
		pageSize=100;
		educl = eduCommentDAO.myComments(u, currentPage, pageSize);
		for (int i = 0; i < educl.size(); i++) {
			edusl.add(eduServicesDAO.findMerchantId(Integer.parseInt(educl.get(i).getMerchantId())));
		}
		request.setAttribute("educl", educl);
		request.setAttribute("edusl", edusl);
		System.out.println("edusl"+edusl.size()+"educl"+educl.size());
		return Action.SUCCESS;
	}

	public String userLookEduASBK() throws IOException {
		try {
			User u = (User) session.getAttribute("u");
			if (u == null) {

				((HttpServletResponse) OpeFunction.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
				return null;
			}
			fos = foldao.Allu(u.getId());

			for (int i = 0; i < fos.size(); i++) {

				fone = forumdao.getForumOne(fos.get(i).getForumid());

				User u1 = userDAO.byid(fone.getUserid());
				if (fone != null && u1 != null) {
					us.add(u1);
					fones.add(fone);
				} else {

				}

			}

			request.setAttribute("fones", fones);
			request.setAttribute("us", us);
			pageSize=100;
			attl = eduServicesDAO.byUserid(u.getId(), currentPage, pageSize, Attention.COME_EduServices);
			for (int i = 0; i < attl.size(); i++) {
				edusl.add(eduServicesDAO.findMerchantId(attl.get(i).getObjectid()));
			}

			request.setAttribute("attl", attl);
			request.setAttribute("edusl", edusl);

			for (Collect c : collectDAO.Allu(u.getId())) {
				News n = newsDAO.byid(c.getNewsid());
				if (n != null) {
					nl.add(n);
				}

			}
			request.setAttribute("nl", nl);
			System.out.println(u.getId() + "新闻" + nl.size() + "收藏多少教辅" + attl.size() + " ==" + edusl.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getLikeCommment() {
		pageSize = 100;
		if (!OpeFunction.isEmpty(content)) {
			educl = eduCommentDAO.likeFind(content, currentPage, pageSize);
			request.setAttribute("el", educl);
			for (EduComment eduComment : educl) {
				edusl.add(eduServicesDAO.findMerchantId(Integer.parseInt(eduComment.getMerchantId())));
			}
			request.setAttribute("edusl", edusl);
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("content", content);
		return Action.SUCCESS;
	}

	public String getAllCommments() {

		educl = eduCommentDAO.find(currentPage, pageSize);
		for (EduComment eduComment : educl) {
			edusl.add(eduServicesDAO.findMerchantId(Integer.parseInt(eduComment.getMerchantId())));
		}
		request.setAttribute("edusl", edusl);
		request.setAttribute("el", educl.size() > 0 ? educl : null);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);

		return Action.SUCCESS;
	}

	public String deleteCommment() throws IOException {
		EduComment Ed = eduCommentDAO.find(Integer.valueOf(replyId));
		if (Ed != null) {
			eduCommentDAO.delete(Ed);
		}
		if (content != null) {
			((HttpServletResponse) OpeFunction.response())
					.sendRedirect(request.getContextPath() + "/getLikeCommment?content=" + content);
			return null;
		}
		return Action.SUCCESS;
	}

	public static void main(String[] args) {
		User u = new User();
		u.setId(2);
		u.setUsername("dddd");
		u.setOnline(1);
		EduComment e = new EduComment();
		e.setId(6);
		e.setUser(u);
		List<EduComment> es = new ArrayList<>();
		es.add(e);
		es.add(e);
		System.out.println(JsonUtil.toJsonExpose(es));
	}

	/**
	 * @describe get merchant comments
	 * @param merchantId
	 *            necessary
	 * @param fatherId
	 *            choice
	 * @param currentPage
	 *            choice
	 * @param pageSize
	 *            choice
	 * @throws IOException
	 */
	public void getCommments() throws IOException {
		List<EduComment> eduComments = null;
		if (!StringUtils.isEmpty(fatherId) && StringUtils.isNumeric(fatherId)) {
			eduComments = eduCommentDAO.find(merchantId, Integer.valueOf(fatherId), currentPage, pageSize);
		} else {
			eduComments = eduCommentDAO.find(merchantId, EduComment.ROOT, currentPage, pageSize);
		}
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(JsonUtil.toJsonExpose(eduComments));
	}

	public String getWebCommments() throws IOException {
		try {
			System.out.println("merchantId:" + merchantId);
			EduServices edus = eduServicesDAO.findMerchantId(Integer.parseInt(merchantId));
			educl = eduCommentDAO.find(merchantId, currentPage, pageSize);
			request.setAttribute("educl", educl);
			request.setAttribute("edus", edus);
			session.setAttribute("merchantId", merchantId);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
		return Action.SUCCESS;
	}

	/**
	 * @describe add comment
	 * @param userId
	 *            necessary
	 * @param merchantId
	 *            necessary
	 * @param pictures
	 *            choice
	 * @param content
	 *            choice
	 * @param score
	 *            choice
	 * @param fatherId
	 *            choice
	 * @throws IOException
	 */
	public void addComments() throws IOException {
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8 ");
		boolean result = false;
		if (!StringUtils.isEmpty(userId) && StringUtils.isNumeric(userId) && !StringUtils.isEmpty(merchantId)) {
			User user = userDAO.byid(Integer.valueOf(userId));
			if (user != null) {
				EduComment eduComment = new EduComment();

				String picStr = "";
				if (pictures != null && pictures.length > 0) {
					System.out.println("upload");
					String path = "/file/" + OpeFunction.getNameDayTime() + "/" + user.getId() + "/EduComment/"
							+ merchantId + "/";
					String realPath = ServletActionContext.getServletContext().getRealPath(path).replace("Befriend",
							"");
					System.out.println("realPath:" + realPath);
					File saveDir = new File(realPath);
					if (!saveDir.exists()) {
						saveDir.mkdirs();
					}

					for (int i = 0; i < pictures.length; i++) {
						try {

							int index = picturesFileName[i].indexOf(".");
							String suffix = picturesFileName[i].substring(index);
							String fileName = String.valueOf(new Date().getTime()) + suffix;
							File saveFile = new File(saveDir, fileName);
							FileUtils.copyFile(pictures[i], saveFile);
							picStr += path + fileName + "!#";

						} catch (IOException e) {
							result = false;
							e.printStackTrace();
						}
					}

				}

				eduComment.setMerchantId(merchantId);
				eduComment.setUser(user);
				eduComment.setScore(score);
				eduComment.setContent(content);
				eduComment.setPictures(picStr);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				eduComment.setTime(sdf.format(date));
				if (!StringUtils.isEmpty(fatherId) && StringUtils.isNumeric(fatherId)) {
					eduComment.setFatherId(Integer.valueOf(fatherId));
				}
				if (!StringUtils.isEmpty(replyId) && StringUtils.isNumeric(replyId)) {
					User reply = userDAO.byid(Integer.valueOf(replyId));
					eduComment.setReply(reply);
				}
				eduCommentDAO.save(eduComment);
				result = true;
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		response.getWriter().println(result);
	}

	/**
	 * @describe add comment
	 * @param userId
	 *            necessary
	 * @param merchantId
	 *            necessary
	 * @param pictures
	 *            choice
	 * @param content
	 *            choice
	 * @param score
	 *            choice
	 * @param fatherId
	 *            choice
	 * @throws IOException
	 */
	public void addWebComments() throws IOException {
		String text = "";
		String loginPage = "";

		merchantId = (String) session.getAttribute("merchantId");
		System.out.println("merchantId:" + merchantId);
		System.out.println("score:" + score);
		System.out.println("content:" + content);
		System.out.println("fatherId:" + fatherId);
		System.out.println("path:" + path);

		if (!OpeFunction.isEmpty(merchantId) && !OpeFunction.isEmpty(content)) {
			User user = (User) session.getAttribute("u");
			if (user != null) {
				EduComment eduComment = new EduComment();

				String picStr = "";
				eduComment.setMerchantId(merchantId);
				eduComment.setUser(user);
				eduComment.setScore(score);// 12345 星级
				eduComment.setContent(content);// 评论内容

				eduComment.setPictures(path); // 图片地址

				eduComment.setTime(OpeFunction.getNowTime());
				if (!StringUtils.isEmpty(fatherId) && StringUtils.isNumeric(fatherId)) {
					eduComment.setFatherId(Integer.valueOf(fatherId));
				}
				if (!StringUtils.isEmpty(replyId) && StringUtils.isNumeric(replyId)) {
					User reply = userDAO.byid(Integer.valueOf(replyId));
					eduComment.setReply(reply);
				}
				eduCommentDAO.save(eduComment);
				text = "评论成功";
				System.out.println(true);
				loginPage = "/Befriend/getWebCommments?merchantId=" + merchantId;
			} else {
				System.out.println(false);
				text = "未登入";
				loginPage = "/Befriend/sample_lihu/htm/login.html";
			}
		} else {
			System.out.println(false);
			text = "评论失败";
			loginPage = "/Befriend/getWebCommments?merchantId=" + merchantId;
		}
		((HttpServletResponse) OpeFunction.response()).sendRedirect(loginPage);
//		HttpServletResponse response = ServletActionContext.getResponse();
//
//		response.setCharacterEncoding("GBK");
//		PrintWriter out = response.getWriter();
//
//		StringBuilder builder = new StringBuilder();
//		builder.append("<script type=\"text/javascript\">");
//		builder.append("alert('" + text + "');");
//		builder.append("window.top.location.href='");
//		builder.append(loginPage);
//		builder.append("';");
//		builder.append("</script>");
//		out.print(builder.toString());

	}

	public EduCommentAction(EduServicesDAO eduServicesDAO, EduCommentDAO eduCommentDAO, UserDAO userDAO,
			NewsDAO newsDAO, CollectDAO collectDAO, ForumDAO forumdao, FollectDAO foldao, ReviewDAO rdao) {
		super();
		this.eduServicesDAO = eduServicesDAO;
		this.eduCommentDAO = eduCommentDAO;
		this.userDAO = userDAO;
		this.newsDAO = newsDAO;
		this.collectDAO = collectDAO;
		this.forumdao = forumdao;
		this.foldao = foldao;
		this.rdao = rdao;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public File[] getPictures() {
		return pictures;
	}

	public void setPictures(File[] pictures) {
		this.pictures = pictures;
	}

	public String[] getPicturesFileName() {
		return picturesFileName;
	}

	public void setPicturesFileName(String[] picturesFileName) {
		this.picturesFileName = picturesFileName;
	}

	public String[] getPicturesContentType() {
		return picturesContentType;
	}

	public void setPicturesContentType(String[] picturesContentType) {
		this.picturesContentType = picturesContentType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
