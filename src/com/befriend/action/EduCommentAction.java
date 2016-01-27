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
import com.befriend.dao.NewsDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Attention;
import com.befriend.entity.Collect;
import com.befriend.entity.EduActivity;
import com.befriend.entity.EduComment;
import com.befriend.entity.EduServices;
import com.befriend.entity.News;
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
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String merchantId;
	private int score = 0;
	private String userId;
	private String replyId;
	private String content;
	private String fatherId;

	private File[] pictures;
	private String[] picturesFileName;
	private String[] picturesContentType;
	private HttpSession session = ServletActionContext.getRequest()
			.getSession();
	private int currentPage = 1;
	private int pageSize = 10;

	List<EduActivity> eaal = new ArrayList<EduActivity>();
	List<EduServices> edusl = new ArrayList<EduServices>();
	List<EduComment> educl = new ArrayList<EduComment>();
	List<Attention> attl = new ArrayList<Attention>();
	List<News> nl = new ArrayList<News>();

	public String userLookEduASBK() throws IOException {
		try {
			User u = (User) session.getAttribute("u");
			attl = eduServicesDAO.byUserid(u.getId(), currentPage, pageSize,
					Attention.COME_EduServices);
			for (int i = 0; i < attl.size(); i++) {
				edusl.add(eduServicesDAO.findMerchantId(attl.get(i)
						.getObjectid()));
			}
			request.setAttribute("edusl", edusl);

			for (Collect c : collectDAO.Allu(u.getId())) {
				News n = newsDAO.byid(c.getNewsid());
				if (n != null) {
					nl.add(n);
				}

			}
			request.setAttribute("nl", nl);

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
				edusl.add(eduServicesDAO.findMerchantId(Integer
						.parseInt(eduComment.getMerchantId())));
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
			edusl.add(eduServicesDAO.findMerchantId(Integer.parseInt(eduComment
					.getMerchantId())));
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
			((HttpServletResponse) OpeFunction.response()).sendRedirect(request
					.getContextPath() + "/getLikeCommment?content=" + content);
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
			eduComments = eduCommentDAO.find(merchantId,
					Integer.valueOf(fatherId), currentPage, pageSize);
		} else {
			eduComments = eduCommentDAO.find(merchantId, EduComment.ROOT,
					currentPage, pageSize);
		}
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(JsonUtil.toJsonExpose(eduComments));
	}

	public String getWebCommments() throws IOException {
		try {
			System.out.println("merchantId:" + merchantId);
			EduServices edus = eduServicesDAO.findMerchantId(Integer
					.parseInt(merchantId));
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
		if (!StringUtils.isEmpty(userId) && StringUtils.isNumeric(userId)
				&& !StringUtils.isEmpty(merchantId)) {
			User user = userDAO.byid(Integer.valueOf(userId));
			if (user != null) {
				EduComment eduComment = new EduComment();

				String picStr = "";
				if (pictures != null && pictures.length > 0) {
					System.out.println("upload");
					String path = "/file/" +OpeFunction.getNameDayTime()+"/"+ user.getId() + "/EduComment/"
							+ merchantId + "/";
					String realPath = ServletActionContext.getServletContext()
							.getRealPath(path).replace("Befriend", "");
					System.out.println("realPath:" + realPath);
					File saveDir = new File(realPath);
					if (!saveDir.exists()) {
						saveDir.mkdirs();
					}

					for (int i = 0; i < pictures.length; i++) {
						try {

							int index = picturesFileName[i].indexOf(".");
							String suffix = picturesFileName[i]
									.substring(index);
							String fileName = String.valueOf(new Date()
									.getTime()) + suffix;
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
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				eduComment.setTime(sdf.format(date));
				if (!StringUtils.isEmpty(fatherId)
						&& StringUtils.isNumeric(fatherId)) {
					eduComment.setFatherId(Integer.valueOf(fatherId));
				}
				if (!StringUtils.isEmpty(replyId)
						&& StringUtils.isNumeric(replyId)) {
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
		String text="";
		String loginPage="";
		
		merchantId = (String) session.getAttribute("merchantId");
		System.out.println("merchantId:" + merchantId);
		System.out.println("score:" + score);
		System.out.println("content:" + content);
		if (!OpeFunction.isEmpty(merchantId)&&!OpeFunction.isEmpty(content)) {
			User user = (User) session.getAttribute("u");
			if (user != null) {
				EduComment eduComment = new EduComment();

				String picStr = "";
				if (pictures != null && pictures.length > 0) {
					System.out.println("upload");
					String path = "/file/" +OpeFunction.getNameDayTime()+"/"+ user.getId() + "/EduComment/"
							+ merchantId + "/";
					String realPath = ServletActionContext.getServletContext()
							.getRealPath(path).replace("Befriend", "");
					System.out.println("realPath:" + realPath);
					File saveDir = new File(realPath);
					if (!saveDir.exists()) {
						saveDir.mkdirs();
					}

					for (int i = 0; i < pictures.length; i++) {
						try {

							int index = picturesFileName[i].indexOf(".");
							String suffix = picturesFileName[i]
									.substring(index);
							String fileName = String.valueOf(new Date()
									.getTime()) + suffix;
							File saveFile = new File(saveDir, fileName);
							FileUtils.copyFile(pictures[i], saveFile);
							picStr += path + fileName + "!#";

						} catch (IOException e) {

							e.printStackTrace();
						}
					}

				}

				eduComment.setMerchantId(merchantId);
				eduComment.setUser(user);
				eduComment.setScore(score);// 12345 星级
				eduComment.setContent(content);// 评论内容
				eduComment.setPictures(picStr); // 图片地址
				eduComment.setTime(OpeFunction.getNowTime());
				if (!StringUtils.isEmpty(fatherId)
						&& StringUtils.isNumeric(fatherId)) {
					eduComment.setFatherId(Integer.valueOf(fatherId));
				}
				if (!StringUtils.isEmpty(replyId)
						&& StringUtils.isNumeric(replyId)) {
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
				loginPage = "/Befriend/SimulationApp/login.html";
			}
		} else {
			System.out.println(false);
			text = "评论失败";
			loginPage = "/Befriend/getWebCommments?merchantId=" + merchantId;
		}
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		 
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\">");
		builder.append("alert('" + text + "');");
		builder.append("window.top.location.href='");
		builder.append(loginPage);
		builder.append("';");
		builder.append("</script>");
		out.print(builder.toString());

	}

	public EduCommentAction(EduCommentDAO eduCommentDAO,
			EduServicesDAO eduServicesDAO, UserDAO userDAO, NewsDAO newsDAO,
			CollectDAO collectDAO) {
		super();
		this.eduCommentDAO = eduCommentDAO;
		this.eduServicesDAO = eduServicesDAO;
		this.userDAO = userDAO;
		this.newsDAO = newsDAO;
		this.collectDAO = collectDAO;
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

}
