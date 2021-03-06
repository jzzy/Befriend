package com.befriend.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.befriend.dao.CollectDAO;
import com.befriend.dao.NewsDAO;
import com.befriend.dao.ReviewDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.Book;
import com.befriend.entity.Collect;
import com.befriend.entity.News;
import com.befriend.entity.Review;
import com.befriend.entity.User;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;

@SuppressWarnings("all")
public class NewsAction implements ServletRequestAware, ServletResponseAware {
	private OpeFunction util;// 自建的工具类

	private UserDAO userdao;// 用户dao
	private NewsDAO ndao; // 新闻dao
	private CollectDAO cdao;// 收藏dao
	private ReviewDAO rdao;// 评论dao

	private int num = 0;// 需要多少新闻
	private List<News> nl = new ArrayList<News>();
	private News n = new News();// 新闻类
	private Collect c = new Collect();// 收藏类
	private Review r = new Review();// 评论类
	private List<Review> rl = new ArrayList<Review>();// 评论类
	private List<Collect> cl = new ArrayList<Collect>();// 收藏 List
	private List<User> ul = new ArrayList<User>();// 用户 List
	private int userid;// 用户id
	private int newsid;// 新闻id
	private String review;// 评论内容
	private int reviewid;// 评论的id
	private int expert = 0;// 获取新闻是否为 专家 0不是 1是
	private String types;// 4小类
	private String type;// 8大类
	private String summary;// 简介
	private String title;// 标题
	private String timet;// 时间
	private String area;// 省级
	private String areas;// 市级

	private File imgFile;// 小图文件
	private File imgFilemax;// 大图文件
	private String imgFileFileName;// 小图文件名

	private String imgFileContentType;// 小图文件类型
	private String imgFilemaxFileName;// 大图文件名
	private String imgFilemaxContentType;// 大图文件类型

	private String savePath;// 目录
	private int tp;// 选择 八大类新闻
	private String A;// 一个 标示 用于判断 往哪跳转 和 判断 是 八大类新闻 还是 本地新闻
	private int pageSize = 10;// 每页显示 多少条数据
	private int currentPage = 1;// 这是第多少页
	private int id;// id
	private String username;// 评论的用户名
	private HttpServletRequest request;
	private HttpServletResponse response;

	private HttpSession session = ServletActionContext.getRequest().getSession();

	private String province = null;// 省级

	private String city;// 市级
	private String author;
	private String time = OpeFunction.getNowTime();
	Book book = new Book();
	private List<Book> bookl;
	private int itype = 0;
	private String house;
	private String content1;// 新闻主要内容
	private File xlsxFile;

	public String searchNews() throws IOException {
		if (!util.isEmpty(title)) {
			nl = ndao.likeNews(title);
		}
		request.setAttribute("l", nl);
		return Action.SUCCESS;

	}

	private int hits;

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String upAdminNews() throws IOException, ParseException {
		n = ndao.byid(newsid);
		if (n != null) {
			n.setTitle(title);
			n.setSummary(summary);
			n.setContent(content1);
			if (hits > 0) {
				n.setHits(hits);
			}

			if (num > 0) {
				n.setCollectnum(num);
			}
			try {

				// 确定时间格式正确
				n.setTime(OpeFunction.setTime(time));
			} catch (Exception e) {
				OpeFunction.Out().print("时间格式错误!");
				return null;
			}
			ndao.Upnews(n);

		}
		// request.setAttribute("n", n);
		return Action.SUCCESS;

	}

	public String adminNewsById() throws IOException {
		System.out.println("adminNewsById is ok!");
		n = ndao.byid(newsid);
		request.setAttribute("n", n);
		return Action.SUCCESS;

	}

	public void LookBookById() throws IOException {
		book = ndao.byIdBook(id);
		if (book != null) {
			book.setHits(book.getHits() + 1);
			ndao.Upnews(book);
		}
		util.Out().print(util.ToJson(book));

	}

	public String webLookBookById() throws IOException {
		book = ndao.byIdBook(id);
		if (book != null) {
			book.setHits(book.getHits() + 1);
			ndao.Upnews(book);
		}
		request.setAttribute("book", book);
		return Action.SUCCESS;

	}

	public void LookBook() throws IOException {
		int count = ndao.countBools(itype);
		int max = count;

		if (count % pageSize == 0) {
			count = count / pageSize;
		} else {
			count = count / pageSize + 1;
		}
		if (currentPage > count) {
			currentPage = count;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		bookl = ndao.paging(pageSize, currentPage, itype);
		String result = "{\"bookl\":" + util.ToJson(bookl) + ",\"max\":" + max + ",\"currentPage\":" + currentPage
				+ "}";
		util.Out().print(result);

	}

	public String webLookBook() throws IOException {
		int count = ndao.countBools(itype);
		int max = count;

		if (count % pageSize == 0) {
			count = count / pageSize;
		} else {
			count = count / pageSize + 1;
		}
		if (currentPage > count) {
			currentPage = count;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		bookl = ndao.paging(pageSize, currentPage, itype);
		request.setAttribute("bookl", bookl);
		request.setAttribute("type", itype);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("max", max);
		return Action.SUCCESS;

	}

	public void upBookxmls() throws IOException, InvalidFormatException, ParseException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (xlsxFile == null || admin == null) {
			OpeFunction.outjS(request.getContextPath() + "/SuperAdmin/AdminNews/kindeditor/jsp/upBook.jsp", "null");
			return;
		}
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(xlsxFile);

		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}

			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {

				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				if (xssfRow.getCell(0) != null) {

					try {
						title = xssfRow.getCell(0) == null ? null : xssfRow.getCell(0).toString();
						author = xssfRow.getCell(1) == null ? null : xssfRow.getCell(1).toString();
						house = xssfRow.getCell(2) == null ? null : xssfRow.getCell(2).toString();
						summary = xssfRow.getCell(3) == null ? null : xssfRow.getCell(3).toString();
						review = xssfRow.getCell(4) == null ? null : xssfRow.getCell(4).toString();
						double bou = Double
								.parseDouble(xssfRow.getCell(5) == null ? null : xssfRow.getCell(5).toString());
						itype = (int) bou;

					} catch (Exception e) {
						e.printStackTrace();

						continue;
					}
					System.out.println(title + itype);
					book = new Book();
					if (ndao.byTitleBook(title) != null || itype <= 0) {
						continue;
					} else {
						book.setTitle(title);
						book.setAuthor(author);
						book.setSummary(summary);
						book.setReview(review);

						book.setTime(OpeFunction.setTime(time));
						book.setType(itype);
						book.setHouse(house);
						book.setAdmin(admin.getAdmin());
						ndao.Save(book);

					}
				}
			}
		}

		OpeFunction.outjS(request.getContextPath() + "/SuperAdmin/AdminNews/kindeditor/jsp/upBook.jsp", "ok");

	}

	public String upBook() throws IOException, InvalidFormatException, ParseException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin != null && title != null && author != null && summary != null && review != null && itype > 0
				&& house != null) {
			book.setTitle(title);
			book.setAuthor(author);
			book.setSummary(summary);
			book.setReview(review);
			book.setTime(OpeFunction.setTime(time));
			book.setType(itype);
			book.setHouse(house);
			book.setAdmin(admin.getAdmin());
			ndao.Save(book);
			return Action.SUCCESS;
		} else {
			OpeFunction.outjS(request.getContextPath() + "/SuperAdmin/AdminNews/kindeditor/jsp/upBook.jsp", "no");
			return null;
		}

	}

	/**
	 * 通过id查询新闻 看是否有更新
	 * 
	 * @throws IOException
	 */
	public void newNewsId() throws IOException {

		util.Out().print(ndao.maxNewsCount());

	}

	/**
	 * 微信分页 查询 本地新闻
	 * 
	 * @throws IOException
	 */
	public String webWeiXinHotarea() throws IOException {
		if (pageSize <= 0) {
			pageSize = 10;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}

		int a = 0;
		if (!OpeFunction.isEmpty(province)) {
			session.setAttribute("province", province);
		}
		Object Oprovince = session.getAttribute("province");
		if (Oprovince != null) {
			province = Oprovince.toString();

		} else {
			province = "湖南";
		}
		System.out.println(province);
		a = ndao.Hotarea(province, OpeFunction.getNowTime());

		if (a % pageSize == 0) {
			a = a / pageSize;
		} else {
			a = a / pageSize + 1;
		}
		if (currentPage > a) {
			currentPage = a;
		}

		nl = ndao.Hotarea(province, pageSize, currentPage, OpeFunction.getNowTime());
		System.out.println(" -有" + a + "页");
		System.out.println("每页多少条-" + pageSize);
		System.out.println("第-" + currentPage + "-页");

		request.setAttribute("currentPage", currentPage);

		request.setAttribute("nl", nl);

		request.setAttribute("a", a);
		return Action.SUCCESS;
	}

	/**
	 * 通过username查询评论 新闻和对每个新闻的最新 评论
	 * 
	 * @throws IOException
	 */
	public String webReviewsusername() throws IOException {
		try {

			User u = (User) session.getAttribute("u");

			if (u == null) {

				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
				return null;
			}

			username = u.getUsername();

			List<Integer> rn = new ArrayList<Integer>();// 收藏 List
			for (Review r1 : rdao.Allu(username)) {

				Boolean b = true;

				for (int i = 0; i < rn.size(); i++) {

					if (rn.get(i) == r1.getNewsid()) {

						b = false;
					}

				}

				if (b) {
					System.out.println("我评论过的新闻  用户名和新闻id" + username + "+" + r1.getNewsid() + "时间是：" + r1.getTime());
					rl.add(rdao.unid(username, r1.getNewsid()).get(0));
					nl.add(ndao.byid(r1.getNewsid()));
				}

				rn.add(r1.getNewsid());

			}

			request.setAttribute("nl", nl);
			request.setAttribute("rl", rl);
		} catch (Exception e) {
			System.out.println("异常是" + e.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * 查询收藏 通过用户id进行查询
	 */
	public String webSearchBookmark() throws IOException {

		User u = (User) session.getAttribute("u");

		if (u == null) {

			((HttpServletResponse) util.response())
					.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
			return null;
		}
		userid = u.getId();
		System.out.println("进入了webSearchBookmark");

		for (Collect c : cdao.Allu(userid)) {
			n = ndao.byid(c.getNewsid());
			if (n != null) {
				nl.add(n);
			}

		}
		request.setAttribute("nl", nl);
		System.out.println("用户" + u.getUsername() + "收藏了" + nl.size() + "个文章");
		return Action.SUCCESS;

	}

	/**
	 * 添加收藏 通过 用户id 新闻id
	 * 
	 * @throws IOException
	 */
	public void webCsave() throws IOException {
		try {

			System.out.println("webCsave");

			User u = (User) session.getAttribute("u");

			if (u == null) {

				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
				System.out.println("你还没有登入!");
				return;
			}
			userid = u.getId();
			System.out.println("userid" + u.getId());
			if (cdao.unid(userid, newsid) != null) {
				util.Out().print(false);
				return;
			} else {
				c.setNewsid(newsid);
				c.setUserid(userid);
				c.setTime(util.getNowTime());
				cdao.save(c);

				n = ndao.byid(newsid);

				System.out.println("newsid是" + n.getId());
				System.out.println("1该文章被收藏" + n.getCollectnum());
				// 收藏数
				n.setCollectnum((n.getCollectnum() == null ? 0 : n.getCollectnum()) + 1);
				// 收藏数*2+点击数
				n.setCah((n.getCollectnum() == null ? 0 : n.getCollectnum()) * 2
						+ (n.getHits() == null ? 0 : n.getHits()));

				ndao.Upnews(n);
				util.Out().print(true);

			}

		} catch (Exception e) {
			util.Out().print(false);
		}

	}

	/**
	 * web添加评论 通过 用户username 新闻newsid
	 * 
	 * @throws IOException
	 */

	public void webRsave() throws IOException {
		try {

			User u = (User) session.getAttribute("u");

			if (u == null) {

				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
				System.out.println("你还没登入!");
				return;
			}
			System.out.println("进入添加评论Rsave");

			if (newsid <= 0) {
				util.Out().print("null");
				return;

			}
			if (OpeFunction.isEmpty(review)) {
				util.Out().print("请填写评论内容");
				return;
			}
			if (u.getUsername() == null) {
				util.Out().print("请设置用户名!");
				return;
			}
			r.setNewsid(newsid);
			r.setUsername(u.getUsername());
			r.setTime(util.getNowTime());
			r.setReview(review);
			rdao.save(r);

			n = ndao.byid(newsid);
			//
			if (n == null) {
				util.Out().print("没有该新闻");
				return;

			}
			int a = rdao.Alln(newsid).size();
			System.out.println("是否有新闻" + n != null);
			System.out.println("评论内容" + review);
			System.out.println("newsid是" + n.getId());
			System.out.println("该文章被评论" + a);
			n.setReviews(a);
			id = newsid;
			ndao.Upnews(n);
			System.out.println("web 通过id查询新闻" + id);
			if (id <= 0) {
				util.Out().print("没有获取到新闻id");
				System.out.println("没有获取到新闻id");
				return;
			}
			// 获取评论信息
			rl = rdao.Alln(id);
			if (rl.size() != 0) {
				System.out.println("有评论");
				for (int i = 0; i < rl.size(); i++) {

					ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
				}
				System.out.println("评论" + rl.size());

				request.setAttribute("ul", ul);
				request.setAttribute("rl", rl);
			}

			n = ndao.byid(id);
			System.out.println("xinwne " + n.getTitle());
			if (n != null) {
				// 点击数+1
				n.setHits(n.getHits() == null ? 0 : n.getHits() + 1);

				// 点击数+收藏数 +1
				n.setCah((n.getCah() == null ? 0 : n.getCah()) + 1);

				System.out.println("点击数:" + n.getHits() + "收藏数+点击数:" + n.getCah() + "收藏数:" + n.getCollectnum());

				ndao.Upnews(n);
			}
			request.setAttribute("n", n);
			String result = "{\"ul\":" + util.ToJson(ul) + ",\"rl\":" + util.ToJson(rl) + "}";

			OpeFunction.Out().print(result);
			// ((HttpServletResponse) util.response()).sendRedirect(request
			// .getContextPath() + "/webNewsIdTojson?id=" + newsid + "");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 分页 查询 本地新闻
	 * 
	 * @throws IOException
	 */
	public String webHotareaf() throws IOException {

		User u = (User) session.getAttribute("u");
		// if (province!=null) {
		// session.setAttribute("province",province);
		// }
		//
		// if (u == null) {
		//
		// ((HttpServletResponse) util.response()).sendRedirect(request
		// .getContextPath() + "/sample_lihu/htm/login.html");
		// System.out.println("你还没登入!");
		//
		// return null;
		// }
		if (pageSize <= 0) {
			pageSize = 10;
		}

		int a = 0;
		// if (u != null) {
		// System.out.println("用户来自：" + u.getAddress());
		// a = ndao.Hotarea(u.getAddress(), OpeFunction.getNowTime());
		// if (a % pageSize == 0) {
		// a = a / pageSize;
		// } else {
		// a = a / pageSize + 1;
		// }
		// if (currentPage > a) {
		// currentPage = a;
		// }
		// if (currentPage <= 0) {
		// currentPage = 1;
		// }
		// session.setAttribute("province", u.getAddress());
		// nl = ndao.Hotarea(u.getAddress(), pageSize, currentPage,
		// OpeFunction.getNowTime());
		// }

		if (!OpeFunction.isEmpty(province)) {
			session.setAttribute("province", province);
			a = ndao.Hotarea(province, OpeFunction.getNowTime());
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}
			nl = ndao.Hotarea(province, pageSize, currentPage, OpeFunction.getNowTime());
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("type", "bd");
			request.setAttribute("nl", nl);
			request.setAttribute("tp", tp);
			request.setAttribute("province", province);
			request.setAttribute("path", "webHotareafToJson");
			request.setAttribute("a", a);
			System.out.println(" 有-" + a + "-页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");
			System.out.println("用户来自：" + province);
			return Action.SUCCESS;
		}
		if (session.getAttribute("province") != null) {
			province = session.getAttribute("province").toString();
			a = ndao.Hotarea(province, OpeFunction.getNowTime());
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}
			nl = ndao.Hotarea(province, pageSize, currentPage, OpeFunction.getNowTime());
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("tp", tp);
			request.setAttribute("nl", nl);
			request.setAttribute("province", province);
			request.setAttribute("a", a);
			request.setAttribute("type", "bd");
			request.setAttribute("path", "webHotareafToJson");
			System.out.println(" 有-" + a + "-页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");
			System.out.println("用户来自：" + province);
			return Action.SUCCESS;
		}
		if (nl.size() == 0) {
			Object obj = session.getAttribute("province");
			System.out.println("百度定位 :" + obj);
			if (obj != null) {
				province = obj.toString();
			}
			a = ndao.Hotarea(province, OpeFunction.getNowTime());
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			nl = ndao.Hotarea(province, pageSize, currentPage, OpeFunction.getNowTime());

			// if (nl.size() == 0) {
			// a = ndao.area("北京", 0).size();
			// nl = ndao.Hotarea("北京", pageSize, currentPage);
			//
			// }
		}
		System.out.println(" 有-" + a + "-页");
		System.out.println("每页多少条-" + pageSize);
		System.out.println("第-" + currentPage + "-页");
		System.out.println("用户来自：" + province);
		request.setAttribute("type", "bd");
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("path", "webHotareafToJson");
		request.setAttribute("nl", nl);
		request.setAttribute("a", a);
		request.setAttribute("tp", tp);
		return Action.SUCCESS;
	}

	/**
	 * 分页 查询 本地新闻
	 * 
	 * @throws IOException
	 */
	public void webHotareafToJson() throws IOException {
		System.out.println("进入webHotareafToJson");
		User u = (User) session.getAttribute("u");
		// if (province!=null) {
		// session.setAttribute("province",province);
		// }
		//
		// if (u == null) {
		//
		// ((HttpServletResponse) util.response()).sendRedirect(request
		// .getContextPath() + "/sample_lihu/htm/login.html");
		// System.out.println("你还没登入!");
		//
		// return null;
		// }
		if (pageSize <= 0) {
			pageSize = 10;
		}

		int a = 0;
		// if (u != null) {
		// System.out.println("用户来自：" + u.getAddress());
		// a = ndao.Hotarea(u.getAddress(), OpeFunction.getNowTime());
		// if (a % pageSize == 0) {
		// a = a / pageSize;
		// } else {
		// a = a / pageSize + 1;
		// }
		// if (currentPage > a) {
		// currentPage = a;
		// }
		// if (currentPage <= 0) {
		// currentPage = 1;
		// }
		// session.setAttribute("province", u.getAddress());
		// nl = ndao.Hotarea(u.getAddress(), pageSize, currentPage,
		// OpeFunction.getNowTime());
		// }

		if (!OpeFunction.isEmpty(province)) {
			session.setAttribute("province", province);
			a = ndao.Hotarea(province, OpeFunction.getNowTime());
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}
			nl = ndao.Hotarea(province, pageSize, currentPage, OpeFunction.getNowTime());
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("type", "bd");
			request.setAttribute("nl", nl);
			request.setAttribute("province", province);
			request.setAttribute("path", "webHotareaf");
			request.setAttribute("a", a);
			System.out.println(" 有-" + a + "-页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");
			System.out.println("用户来自1：" + province);
			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";
			System.out.println(result);
			util.Out().print(result);
			return;
		}
		if (session.getAttribute("province") != null) {
			province = session.getAttribute("province").toString();
			a = ndao.Hotarea(province, OpeFunction.getNowTime());
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}
			nl = ndao.Hotarea(province, pageSize, currentPage, OpeFunction.getNowTime());
			request.setAttribute("currentPage", currentPage);

			request.setAttribute("nl", nl);
			request.setAttribute("province", province);
			request.setAttribute("a", a);
			request.setAttribute("type", "bd");
			System.out.println(" 有-" + a + "-页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");
			System.out.println("用户来自2：" + province);
			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";
			System.out.println(result);
			util.Out().print(result);
			return;
		}
		if (nl.size() == 0) {
			Object obj = session.getAttribute("province");
			System.out.println("百度定位 :" + obj);
			if (obj != null) {
				province = obj.toString();
			}
			a = ndao.Hotarea(province, OpeFunction.getNowTime());
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			nl = ndao.Hotarea(province, pageSize, currentPage, OpeFunction.getNowTime());

			// if (nl.size() == 0) {
			// a = ndao.area("北京", 0).size();
			// nl = ndao.Hotarea("北京", pageSize, currentPage);
			//
			// }
		}
		System.out.println(" 有-" + a + "-页");
		System.out.println("每页多少条-" + pageSize);
		System.out.println("第-" + currentPage + "-页");
		System.out.println("用户来自3：" + province);
		request.setAttribute("type", "bd");
		request.setAttribute("currentPage", currentPage);

		request.setAttribute("nl", nl);
		request.setAttribute("a", a);
		String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";
		System.out.println(result);
		util.Out().print(result);
	}

	/**
	 * 全国最热新闻 分页查询
	 */
	public String webHottest() {
		try {
			int a = 0;

			a = ndao.Hottest(0, OpeFunction.getNowTime()).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println("最热每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");

			nl = ndao.Hottest(pageSize, currentPage, OpeFunction.getNowTime());

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("type", "zr");
			request.setAttribute("nl", nl);
			request.setAttribute("a", a);
			request.setAttribute("tp", tp);
			request.setAttribute("path", "webHottestToJson");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * 全国最热新闻 分页查询
	 */
	public void webHottestToJson() {
		try {
			int a = 0;

			a = ndao.Hottest(0, OpeFunction.getNowTime()).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");

			nl = ndao.Hottest(pageSize, currentPage, OpeFunction.getNowTime());

			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";
			System.out.println(result);
			util.Out().print(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 八大类 的4小类 查询 type
	 * 
	 * @throws IOException
	 */
	public String webTypes() throws IOException {
		try {
			switch (tp) {
			case 1:
				type = "幼升小";

				break;
			case 2:
				type = "小升初";
				break;
			case 3:
				type = "中考";
				break;
			case 4:
				type = "高考";
				break;

			default:
				System.out.println("请输入正确代码tp");
				type = "No";
				break;
			}
			System.out.println("传的是" + tp + "类别是" + type);
			int a = 0;

			a = ndao.types(type, OpeFunction.getNowTime()).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}
			System.out.println(type + " -有" + a + "页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");

			nl = ndao.types(type, pageSize, currentPage, OpeFunction.getNowTime());

			request.setAttribute("currentPage", currentPage);

			request.setAttribute("type", type);

			request.setAttribute("nl", nl);

			request.setAttribute("tp", tp);

			request.setAttribute("a", a);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;

	}

	/**
	 * web分页 查询 八大类type
	 * 
	 * @throws IOException
	 */
	public String webSbType() throws IOException {
		request.setAttribute("tp", tp);
		return Action.SUCCESS;

	}

	/**
	 * web分页 查询 八大类type
	 * 
	 * @throws IOException
	 */
	public String webNewtype() throws IOException {
		try {
			if (pageSize <= 0) {

				pageSize = 10;
			}
			if (tp <= 0) {
				util.Out().print("tp为空！");
				return null;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println("进入webnewtype");
			System.out.println("传的" + tp);
			switch (tp) {

			case 1:
				type = "升学指南";

				break;
			case 2:
				type = "教子经验";

				break;
			case 3:
				type = "成长路上";
				break;
			case 4:
				type = "出国留学";
				break;
			case 5:
				type = "兴趣特长";
				break;
			case 6:
				type = "名人教子";
				break;
			case 7:
				type = "健康导航";
				break;
			case 8:
				type = "轻松驿站";
				break;
			case 9:
				type = "社会广角";
				// 全国最热webHottest

				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webHottest?tp=9");
				return null;
			case 10:
				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webHotareaf?tp=10");
				return null;
			// 本地

			default:
				System.out.println("请输入正确代码tp");
				type = "No";
				return null;
			}
			int a = 0;

			a = ndao.type(0, type, OpeFunction.getNowTime()).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}

			System.out.println(type + " -有" + a + "页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");

			System.out.println(type);

			nl = ndao.type(0, type, pageSize, currentPage, OpeFunction.getNowTime());

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("nl", nl);
			request.setAttribute("type", type);

			request.setAttribute("tp", tp);

			request.setAttribute("a", a);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * web分页 查询 八大类type
	 * 
	 * @throws IOException
	 */
	public void webNewtypeToJson() throws IOException {
		try {
			if (pageSize <= 0) {

				pageSize = 10;
			}
			if (tp <= 0) {
				util.Out().print("tp为空！");
				return;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println("进入webnewtype");
			System.out.println("传的" + tp);
			switch (tp) {

			case 1:
				type = "升学指南";

				break;
			case 2:
				type = "教子经验";

				break;
			case 3:
				type = "成长路上";
				break;
			case 4:
				type = "出国留学";
				break;
			case 5:
				type = "兴趣特长";
				break;
			case 6:
				type = "名人教子";
				break;
			case 7:
				type = "健康导航";
				break;
			case 8:
				type = "轻松驿站";
				break;
			case 9:
				type = "社会广角";
				break;

			default:
				System.out.println("请输入正确代码tp");
				type = "No";
				return;
			}
			int a = 0;

			a = ndao.type(0, type, OpeFunction.getNowTime()).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage >= a) {
				currentPage = a;
			}

			System.out.println(type + " -有" + a + "页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");

			System.out.println(type);

			nl = ndao.type(0, type, pageSize, currentPage, OpeFunction.getNowTime());

			// request.setAttribute("a", a);

			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";
			System.out.println(result);
			util.Out().print(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String webNewsIdcomm() throws IOException, ParseException {
		n = ndao.byid(id);
		request.setAttribute("n", n);
		return Action.SUCCESS;
	}

	/**
	 * web 通过id查询新闻
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public String webNewsId() throws IOException, ParseException {
		System.out.println("web 通过id查询新闻" + id);
		if (id <= 0) {
			util.Out().print("没有获取到新闻id");
			System.out.println("没有获取到新闻id");
			return null;
		}
		// 获取评论信息
		rl = rdao.Alln(id);
		if (rl.size() != 0) {
			System.out.println("有评论");
			for (int i = 0; i < rl.size(); i++) {

				ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
			}
			System.out.println("评论" + rl.size());

			request.setAttribute("ul", ul);
			request.setAttribute("rl", rl);
		}

		n = ndao.byid(id);
		System.out.println("xinwne " + n.getTitle());
		if (n != null) {
			// 点击数+1
			n.setHits(n.getHits() == null ? 0 : n.getHits() + 1);

			// 点击数+收藏数 +1
			n.setCah((n.getCah() == null ? 0 : n.getCah()) + 1);

			System.out.println("点击数:" + n.getHits() + "收藏数+点击数:" + n.getCah() + "收藏数:" + n.getCollectnum());

			ndao.Upnews(n);
		}
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * web 通过id查询新闻
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void countNewsId() throws IOException, ParseException {

		try {

			n = ndao.byid(id);

			// 点击数+1
			n.setHits(n.getHits() == null ? 0 : n.getHits() + 1);

			// 点击数+收藏数 +1
			n.setCah((n.getCah() == null ? 0 : n.getCah()) + 1);

			System.out.println("点击数:" + n.getHits() + "收藏数+点击数:" + n.getCah() + "收藏数:" + n.getCollectnum());

			ndao.Upnews(n);
			OpeFunction.Out().print(true);
		} catch (Exception e) {
			// TODO: 错误时返回 false
			OpeFunction.Out().print(false);
		}

	}

	/**
	 * web 通过id查询新闻
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void webNewsIdTojson() throws IOException, ParseException {
		System.out.println("web 通过id查询新闻" + id);
		if (id <= 0) {
			util.Out().print("没有获取到新闻id");
			System.out.println("没有获取到新闻id");
			return;
		}
		// 获取评论信息
		rl = rdao.Alln(id);
		if (rl.size() != 0) {
			System.out.println("有评论");
			for (int i = 0; i < rl.size(); i++) {

				ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
			}
			System.out.println("评论" + rl.size());

			request.setAttribute("ul", ul);
			request.setAttribute("rl", rl);
		}

		n = ndao.byid(id);
		System.out.println("xinwne " + n.getTitle());
		if (n != null) {
			// 点击数+1
			n.setHits(n.getHits() == null ? 0 : n.getHits() + 1);

			// 点击数+收藏数 +1
			n.setCah((n.getCah() == null ? 0 : n.getCah()) + 1);

			System.out.println("点击数:" + n.getHits() + "收藏数+点击数:" + n.getCah() + "收藏数:" + n.getCollectnum());

			ndao.Upnews(n);
		}
		request.setAttribute("n", n);
		String result = "{\"ul\":" + util.ToJson(ul) + ",\"rl\":" + util.ToJson(rl) + "}";

		OpeFunction.Out().print(result);
	}

	/**
	 * 微信公众号 通过id查询新闻
	 * 
	 * @throws IOException
	 */
	public String weiXniNewsId() throws IOException {

		System.out.println("微信公众号 通过id查询新闻" + id);
		if (id <= 0) {
			util.Out().print("没有获取到新闻id");
			System.out.println("没有获取到新闻id");
			return null;
		}

		rl = rdao.Alln(id);
		if (rl.size() != 0) {
			System.out.println("有评论");
			for (int i = 0; i < rl.size(); i++) {

				ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
			}
			System.out.println("评论" + rl.size());

			request.setAttribute("ul", ul);
			request.setAttribute("rl", rl);
		}

		n = ndao.byid(id);

		n.setHits(n.getHits() + 1);

		n.setCah(n.getCah() + 1);
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * 微信公众号 本地新闻 按时间排序
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	public String weiXniBDN() throws IOException, InterruptedException {
		if (!OpeFunction.isEmpty(province)) {
			session.setAttribute("province", province);
		} else {
			Object pro = session.getAttribute("province");
			if (pro == null) {
				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/weixin/byip.html");
			} else {
				province = pro.toString();
			}

		}

		nl = ndao.Hotarea(province, pageSize, currentPage, time);
		request.setAttribute("nl", nl);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("province", province);
		request.setAttribute("city", city);
		System.out.println("" + province + nl.size() + "新闻");
		return Action.SUCCESS;
	}

	/**
	 * 微信公众号 本地新闻 按时间排序
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	public String weiXniProvince() throws IOException, InterruptedException {
		if (!OpeFunction.isEmpty(province)) {
			session.setAttribute("province", province);
		} else {
			Object pro = session.getAttribute("province");
			if (pro == null) {
				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/weixin/byip.html");
			} else {
				province = pro.toString();
			}

		}
		int all = ndao.Hotarea(province, time);
		int Max = 0;
		if (all % pageSize == 0) {
			Max = all / pageSize;
		} else {
			Max = all / pageSize + 1;
		}
		System.out.println("****" + province + nl.size() + "新闻 Max:" + Max + "currentPage:" + currentPage);
		if (currentPage <= Max) {
			nl = ndao.Hotarea(province, pageSize, currentPage, time);

			request.setAttribute("nl", nl);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("province", province);
			System.out.println("****" + province + nl.size() + "新闻 Max:" + Max + "currentPage:" + currentPage);

		} else {
			System.out.println("下一页没有新闻了");

		}
		return Action.SUCCESS;
	}

	/**
	 * 查询本地新闻 和八大类里 为专家的
	 * 
	 * @throws IOException
	 */
	public void Newsexpert() throws IOException {

		nl = ndao.area(area);
		int ext = nl.size();
		System.out.println(area + " 专家新闻有" + ext);
		for (int i = 0; i < ext; i++) {
			System.out.println(nl.get(i).getArea() + nl.get(i).getTime());
		}
		if (ext >= 1) {
			util.Out().print(util.ToJson(nl));
		} else {
			util.Out().print("null");
		}
	}

	/**
	 * 分页 查询 本地新闻
	 * 
	 * @throws IOException
	 */
	public void Hotareaf() throws IOException {

		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (area == null) {
			util.Out().print("省级为空！");
			return;
		}

		int a = 0;
		nl = ndao.Hotarea(0, area);
		a = nl.size();
		if (a % pageSize == 0) {
			a = a / pageSize;
		} else {
			a = a / pageSize + 1;
		}
		if (currentPage > a) {
			currentPage = a;
		}

		System.out.println(" -有" + a + "页");
		System.out.println("每页多少条-" + pageSize);
		System.out.println("第-" + currentPage + "-页");
		nl = ndao.Hotarea(area, pageSize, currentPage, OpeFunction.getNowTime());
		String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";

		if (nl.size() > 0) {
			util.Out().print(result);
		} else {
			util.Out().print("null");
		}
	}

	/**
	 * 分页 查询 最热新闻
	 * 
	 * @throws IOException
	 */
	public void cah() throws IOException {

		if (pageSize <= 0) {
			pageSize = 10;
		}

		if (currentPage <= 0) {
			currentPage = 10;
		}
		nl = ndao.cah(0);
		int a = 0;

		a = nl.size();
		if (a % pageSize == 0) {
			a = a / pageSize;
		} else {
			a = a / pageSize + 1;
		}
		if (currentPage > a) {
			currentPage = a;
		}

		System.out.println(" -有" + a + "页");
		System.out.println("每页多少条-" + pageSize);
		System.out.println("第-" + currentPage + "-页");
		nl = ndao.cah(pageSize, currentPage);
		String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";

		if (nl.size() > 0) {
			util.Out().print(result);
		} else {
			util.Out().print("null");
		}
	}

	/**
	 * 分页 查询 八大类type
	 * 
	 * @throws IOException
	 */
	public void newtype() throws IOException {
		try {
			if (pageSize <= 0) {
				pageSize = 10;

			}
			if (tp <= 0) {
				util.Out().print("tp==null！");
				return;
			}

			System.out.println("进入newtype");
			System.out.println("传的" + tp);
			switch (tp) {

			case 1:
				type = "升学指南";

				break;
			case 2:
				type = "教子经验";

				break;
			case 3:
				type = "成长路上";
				break;
			case 4:
				type = "出国留学";
				break;
			case 5:
				type = "兴趣特长";
				break;
			case 6:
				type = "名人教子";
				break;
			case 7:
				type = "健康导航";
				break;
			case 8:
				type = "轻松驿站";
				break;
			case 9:
				type = "社会广角";
				break;
			default:
				System.out.println("请输入正确代码tp");
				type = "No";
				break;
			}
			int a = ndao.type(0, type, OpeFunction.getNowTime()).size();

			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println(type + " -有" + a + "页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");
			nl = ndao.type(0, type, pageSize, currentPage, OpeFunction.getNowTime());
			System.out.println(type);
			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";

			if (nl.size() > 0) {
				util.Out().print(result);
			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 通过id删除新闻
	 * 
	 * @throw
	 * @throws IOException
	 * @throws ServletException
	 */
	public void NewsRM() throws IOException {

		n = ndao.byid(newsid);

		if (n != null) {

			cl = cdao.Alln(newsid);

			rl = rdao.Alln(newsid);

			for (int i = 0; i < cl.size(); i++) {

				cdao.remove(cl.get(i));
			}

			for (int i = 0; i < rl.size(); i++) {

				rdao.remove(rl.get(i));
			}
			boolean b = true;
			if (n.getImg() != null) {
				File file = new File(ServletActionContext.getServletContext().getRealPath(n.getImg()));
				b = file.delete();
				System.out.println("新闻小图删除是否成功？" + b);
			}
			if (n.getImgmax() != null) {
				File filemax = new File(ServletActionContext.getServletContext().getRealPath(n.getImgmax()));
				b = filemax.delete();
				System.out.println("新闻大图删除是否成功？" + b);
			}
			ndao.rm(n);

			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/Newsget");
		} else {
			util.Out().print("删除失败 没有要删除的新闻！");

		}

	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String Newsget() throws UnsupportedEncodingException {
		pageSize = 20;

		System.out.println("currentPage:" + currentPage);
		if (currentPage < 1) {
			currentPage = 1;
		}
		int n = 0;
		List<News> l;
		if (tp == 0) {
			n = ndao.HottimesCountLarge(OpeFunction.getNowTime(), 0);
			if (n % 20 == 0) {
				n = n / 20;
			} else {
				n = n / 20 + 1;
			}
			if (currentPage >= n) {
				currentPage = n;
			}
			l = ndao.Pagination(OpeFunction.getNowTime(), pageSize, currentPage);
		} else {
			n = ndao.HottimesCountSmall(OpeFunction.getNowTime(), 0);
			System.out.println("n=" + n);
			if (n % 20 == 0) {
				n = n / 20;
			} else {
				n = n / 20 + 1;
			}
			if (currentPage >= n) {
				currentPage = n;
			}
			l = ndao.Pagination(pageSize, currentPage, OpeFunction.getNowTime());
			for (int i = 0; i < l.size(); i++) {
				System.out.println("时间:" + l.get(i).getTime());
			}
		}
		request.setAttribute("l", l);
		request.setAttribute("n", n);
		request.setAttribute("tp", tp);
		request.setAttribute("currentPage", currentPage);
		return Action.SUCCESS;
	}

	/**
	 * 查看主页新闻 12/10
	 * 
	 * @throws IOException
	 */
	public void NewsA10() throws IOException {
		try {
			System.out.println("时间是" + util.getNowTime());
			System.out.println("进入了NewsA10!!");
			int day = 14;
			System.out.println(util.getNumTime(day) + "前" + day + "天？");
			List<News> nt = new ArrayList<News>();
			num = 11;

			nt = ndao.Hottime(num, OpeFunction.getNowTime());

			List<News> nh = new ArrayList<News>();
			num = 3;

			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("上周1是" + htime + "上周日是" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);

							if (ndao.cah(num, time, htime).size() != 3) {
								htime = util.getNumTime(720);
							}
						}
					}
				}
			}
			nh = ndao.cah(num, time, htime);

			List<News> n4 = new ArrayList<News>();

			num = 2;
			// 本省 新闻数量 小于2
			if (ndao.Hotarea(area, OpeFunction.getNowTime()) < 2) {
				System.out.println("本省新闻小于2条");
				n4 = ndao.type(2, "轻松驿站", OpeFunction.getNowTime());

			} else {
				num = 2;
				System.out.println("本省新闻");
				n4 = ndao.area(area, num, OpeFunction.getNowTime());

			}

			System.out.println("省：" + area);
			System.out.println("市：" + areas);

			ndao.type(1, "健康导航", OpeFunction.getNowTime());
			ndao.type(1, "轻松驿站", OpeFunction.getNowTime());
			List<News> n5 = new ArrayList<News>();
			n5 = ndao.type(1, "轻松驿站", OpeFunction.getNowTime());

			List<News> n8 = new ArrayList<News>();
			n8 = ndao.type(1, "健康导航", OpeFunction.getNowTime());

			List<News> n9 = new ArrayList<News>();
			n9 = ndao.type(1, "社会广角", OpeFunction.getNowTime());
			System.out.println("全国最新有-" + nt.size() + ",全国最热-" + nh.size() + "本地新闻-" + n4.size() + "轻松驿站" + n5.size()
					+ "健康导航" + n8.size() + "社会广角" + n9.size());
			String result = "{\"Hottime\":" + util.ToJson(nt) + ",\"Hottest\":" + util.ToJson(nh) + ",\"Hotarea\":"
					+ util.ToJson(n4) + ",\"typeqs\":" + util.ToJson(n5) + ",\"typejk\":" + util.ToJson(n8)
					+ ",\"typegj\":" + util.ToJson(n9) + "}";
			util.Out().print(result);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 查看主页新闻 2014/12/10
	 * 
	 * @throws IOException
	 */
	public String webNewsA10() throws IOException {
		try {
			User u = (User) session.getAttribute("u");
			if (!OpeFunction.isEmpty(province)) {
				session.setAttribute("province", province);
			}

			String pro = session.getAttribute("province") == null ? null : session.getAttribute("province").toString();
			System.out.println("百度定位 :" + pro);
			if (pro != null) {
				area = pro;
				province = area;
			}
			if (u != null) {

				System.out.println("获取到用户信息了" + u.getAddress());
				if (u.getAddress() != null) {
					// area = u.getAddress();
					// session.setAttribute("province", u.getAddress());
				}
				if (area == null || pro.equals("null")) {
					((HttpServletResponse) util.response())
							.sendRedirect(request.getContextPath() + "/SimulationApp/byIp.jsp");

				}

			} else if (OpeFunction.isEmpty(pro) || pro.equals("null")) {
				System.out.println("12222222222222222222");
				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/SimulationApp/byIp.jsp");

			}

			System.out.println("时间是" + util.getNowTime());
			System.out.println("进入了webNewsA10!!");
			int day = 14;
			System.out.println(util.getNumTime(day) + "前" + day + "天？");
			List<News> nt = new ArrayList<News>();
			num = 11;

			// 全国最新
			nt = ndao.Hottime(num, OpeFunction.getNowTime());

			for (int i = 0; i < nt.size(); i++) {
				System.out.println("新闻时间" + nt.get(i).getTime() + "现在时间" + OpeFunction.getNowTime());
			}
			List<News> nh = new ArrayList<News>();
			num = 3;

			// 最热
			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("上周1是" + htime + "上周日是" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);

							if (ndao.cah(num, time, htime).size() != 3) {
								htime = util.getNumTime(720);
							}
						}
					}
				}
			}
			nh = ndao.cah(num, time, htime);

			List<News> n4 = new ArrayList<News>();

			num = 2;

			if (ndao.Hotarea(area, OpeFunction.getNowTime()) < 2) {

				System.out.println("本省新闻小于2条");
				n4 = ndao.area("北京", num, OpeFunction.getNowTime());

			} else {
				num = 2;
				System.out.println("本省新闻");
				n4 = ndao.area(area, num, OpeFunction.getNowTime());

			}

			System.out.println("省：" + area);
			System.out.println("市：" + areas);

			System.out.println("全国最新有-" + nt.size() + ",全国最热-" + nh.size() + "本地新闻-" + n4.size());
			ndao.type(1, "健康导航", OpeFunction.getNowTime());
			ndao.type(1, "轻松驿站", OpeFunction.getNowTime());
			List<News> n5 = new ArrayList<News>();
			n5 = ndao.type(1, "轻松驿站", OpeFunction.getNowTime());

			List<News> n8 = new ArrayList<News>();
			n8 = ndao.type(1, "健康导航", OpeFunction.getNowTime());

			System.out.println("时间排序" + nt.size() + "热门新闻" + nh.size() + "本地新闻" + n4.size() + "轻松导航是1--"
					+ n5.get(0).getType() + "--健康导航是1---" + n8.get(0).getType());

			request.setAttribute("Hottime", nt);

			request.setAttribute("Hottest", nh);

			request.setAttribute("Hotarea", n4);

			request.setAttribute("typeqs", n5);

			request.setAttribute("typejk", n8);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return Action.SUCCESS;

	}

	/**
	 * 查询全国最热新闻 12/09
	 */

	public void Cah() {
		try {

			num = 3;
			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("上周1是" + htime + "上周日是" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);
						}
					}
				}
			}
			util.Out().print(util.ToJson(ndao.cah(0, time, htime)));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 查看主页新闻 12/9
	 * 
	 * @throws IOException
	 */
	public void NewsA() throws IOException {
		try {
			System.out.println("时间是" + util.getNowTime());
			System.out.println("进入了NewsA");
			List<News> nt = new ArrayList<News>();
			num = 4;

			nt = ndao.Hottime(num, OpeFunction.getNowTime());
			List<News> nh = new ArrayList<News>();
			num = 3;

			String htime = util.getMondayOfWeek1();
			String time = util.getMondayOfWeek7();
			System.out.println("上周1是" + htime + "上周日是" + time);

			if (ndao.cah(num, time, htime).size() != 3) {
				htime = util.getNumTime(10);

				if (ndao.cah(num, time, htime).size() != 3) {
					htime = util.getNumTime(15);

					if (ndao.cah(num, time, htime).size() != 3) {
						htime = util.getNumTime(30);

						if (ndao.cah(num, time, htime).size() != 3) {
							htime = util.getNumTime(365);
						}
					}
				}
			}
			System.out.println("确定的日期" + htime);
			nh = ndao.cah(num, time, htime);

			List<News> n4 = new ArrayList<News>();
			num = 2;

			if (ndao.Hotarea(area, OpeFunction.getNowTime()) < 2) {
				System.out.println("本省新闻小于2条");
				n4 = ndao.type(2, "轻松驿站", OpeFunction.getNowTime());

			} else {
				num = 2;
				System.out.println("本省新闻");
				n4 = ndao.area(area, num, OpeFunction.getNowTime());

			}

			System.out.println("省：" + area);
			System.out.println("市：" + areas);

			System.out.println("全国最新有-" + nt.size() + ",全国最热-" + nh.size() + "本地新闻-" + n4.size());
			// 1是全国最热
			String result = "{\"Hottime\":" + util.ToJson(nt) + ",\"Hottest\":" + util.ToJson(nh) + ",\"Hotarea\":"
					+ util.ToJson(n4) + "}";
			util.Out().print(result);
			System.out.println("时间排序" + nt.size() + "热门新闻" + nh.size() + "本地新闻" + n4.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

	/**
	 * 查询本地新闻
	 */
	public void Hotarea() {
		try {

			nl = ndao.Hotarea(0, area);
			if (nl.size() == 0) {
				util.Out().print("null");
			}
			util.Out().print(util.ToJson(nl));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 全国最热新闻
	 */
	public void Hottest() {
		try {

			nl = ndao.Hottest(0, OpeFunction.getNowTime());

			util.Out().print(util.ToJson(nl));
			;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 八大类 查询 type
	 * 
	 * @throws IOException
	 */
	public void type() throws IOException {
		try {
			System.out.println("进入type");
			System.out.println("传的" + tp);
			switch (tp) {

			case 1:
				type = "升学指南";

				break;
			case 2:
				type = "教子经验";

				break;
			case 3:
				type = "成长路上";
				break;
			case 4:
				type = "出国留学";
				break;
			case 5:
				type = "兴趣特长";
				break;
			case 6:
				type = "名人教子";
				break;
			case 7:
				type = "健康导航";
				break;
			case 8:
				type = "轻松驿站";
				break;
			case 9:
				type = "社会广角";
				break;

			default:
				System.out.println("请输入正确代码tp");
				type = "No";
				break;
			}

			System.out.println(type);

			nl = ndao.type(0, type, OpeFunction.getNowTime());

			if (nl.size() > 0) {
				util.Out().print(util.ToJson(nl));
			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 八大类 的4小类 查询 type
	 * 
	 * @throws IOException
	 */
	public void types() throws IOException {
		try {
			switch (tp) {
			case 1:
				type = "幼升小";

				break;
			case 2:
				type = "小升初";
				break;
			case 3:
				type = "中考";
				break;
			case 4:
				type = "高考";
				break;

			default:
				System.out.println("请输入正确代码tp");
				util.Out().print("not tp");
				return;
			}

			System.out.println("传的是" + tp + "类别是" + type);

			int a = ndao.types(type, OpeFunction.getNowTime()).size();

			if (pageSize <= 0) {
				pageSize = 10;
			}
			if (a % pageSize == 0) {
				a = a / pageSize;
			} else {
				a = a / pageSize + 1;
			}
			if (currentPage > a) {
				currentPage = a;
			}
			if (currentPage <= 0) {
				currentPage = 1;
			}

			System.out.println(type + " -有" + a + "页");
			System.out.println("每页多少条-" + pageSize);
			System.out.println("第-" + currentPage + "-页");
			nl = ndao.types(type, pageSize, currentPage, OpeFunction.getNowTime());
			System.out.println(type);
			String result = "{\"nl\":" + util.ToJson(nl) + ",\"cpe\":" + a + ",\"currentPage\":" + currentPage + "}";

			if (nl.size() > 0) {
				util.Out().print(result);
			} else {
				util.Out().print("null");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 上传新闻
	 * 
	 * @return
	 * @throws IOException
	 */

	public String UPtext() throws IOException {
		try {
			// HttpSession Httpsession = request.getSession();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			System.out.println("文章内容" + content1);
			if (content1 == null) {
				util.Out().print("文章内容没有获取到" + content1);
				return null;
			}
			System.out.println("进入了上传新闻UPtext");
			savePath = "/IMG/Newsimg/" + OpeFunction.getNameDayTime();

			Admin admin = (Admin) session.getAttribute("admin");
			if (admin == null) {
				util.Out().print("系统检测您没有登入！请重新登入重试！");
				return null;
			}
			if (admin.getLevel() > 2) {
				util.Out().print("系统检测您没有登入！请重新登入重试！");
				return null;
			}

			if (imgFile == null) {
				util.Out().print("您没有上传小图 ！请您返回重试！");
				return null;
			}
			if (imgFilemax == null) {
				util.Out().print("您没有上传大图！请您返回重试！");
				return null;
			}

			BufferedImage sourceImg = ImageIO.read(new FileInputStream(imgFile));
			BufferedImage sourceImgmax = ImageIO.read(new FileInputStream(imgFilemax));

			float fimg = util.fileSize(imgFile);
			float fimgmax = util.fileSize(imgFilemax);

			if (sourceImgmax.getWidth() != 720 || sourceImgmax.getHeight() != 360) {
				util.Out().print("大图尺寸为 720*360 请您重新检查下！请您返回重试！");
				return null;
			}
			if (sourceImg.getWidth() != 180 || sourceImg.getHeight() != 140) {
				util.Out().print("小图尺寸为 180*140 请您重新检查下！请您返回重试！");
				return null;
			}

			// if (fimg > 512.00) {
			// util.Out().print("小图大小为 0.5MB 以下！请您重新检查下！请您返回重试！");
			// return null;
			// }
			// if (fimgmax > 1024.00) {
			// util.Out().print("大图大小为 1MB 以下！请您重新检查下！请您返回重试！");
			// return null;
			// }
			System.out.println("宽" + sourceImg.getWidth());
			System.out.println("高" + sourceImg.getHeight());
			System.out.println("是否是专家 0不是 1是 :" + expert);
			String imgmax = util.fileToServer(savePath, imgFilemax, imgFilemaxFileName, imgFilemaxContentType, true);
			String img = util.fileToServer(savePath, imgFile, imgFileFileName, imgFileContentType, true);
			News n = new News();
			n.setTitle(title);
			n.setSummary(summary);
			n.setContent(content1);
			n.setImg(img);
			if (timet.length() == 0) {
				timet = util.getNowTime();
			}
			type = (String) request.getParameter("type");
			types = (String) request.getParameter("types");
			n.setImgmax(imgmax);
			try {

				// 确定时间格式正确
				n.setTime(OpeFunction.setTime(timet));
			} catch (Exception e) {
				OpeFunction.Out().print("时间格式错误!");
				return null;
			}

			// A不是null就是 八大类 上传 界面
			if (A != null) {

				n.setType(type);
				System.out.println("types=" + types);

				if (type.equals("升学指南")) {

					System.out.println("type是" + type);

					if (types != null) {
						System.out.println("types是" + types);
						n.setTypes(types);
					} else {
						System.out.println("types是" + types);
						util.Out().print("请您填写 升学指南的  4小类");
						return null;
					}

				}

			} else {

				// A是null就是 本地 上传 界面
				n.setArea(province);
				n.setAreas(city);

			}

			n.setCollectnum(0);
			n.setCah(0);
			n.setHits(0);
			n.setReviews(0);
			n.setExpert(expert);
			n.setAdmin(admin.getAdmin());
			ndao.Save(n);

		} catch (Exception e) {
			System.out.println("异常问题是" + e.getMessage());
			util.Out().print("异常问题是" + e.getMessage());
			return null;
		}
		// 判断A 来 确定 返回 的界面 A!=bull是 八大类 反之 本地新闻
		if (A != null) {
			String B1 = "A";
			return B1;
		} else {
			String B = "B";
			return B;
		}

		// return Action.SUCCESS;
	}

	/**
	 * 通过id查看新闻
	 * 
	 * @throws IOException
	 */
	public void NewsId() {
		try {
			System.out.println("进入NewsId：" + newsid);
			if (newsid > 0) {
				System.out.println("新闻id" + newsid);
				n = ndao.byid(newsid);
				int nn;
				System.out.println("新闻标题" + n.getTitle());
				// 点击数+!
				n.setHits(n.getHits() == null ? 0 : n.getHits() + 1);
				// 点击数+收藏数*2 +1
				n.setCah(n.getCah() == null ? 0 : n.getCah() + 1);
				ndao.Upnews(n);
				util.Out().print(util.ToJson(ndao.byid(newsid)));
			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 查看主页新闻
	 * 
	 * @throws IOException
	 */
	public void NewsAll() throws IOException {
		try {
			System.out.println("时间是" + util.getNowTime());
			System.out.println("进入了NewsAll");
			List<News> nt = new ArrayList<News>();
			num = 3;

			for (News n2 : ndao.Hottime(num, OpeFunction.getNowTime())) {
				nt.add(n2);
				System.out.println("全国最新新闻id-" + n2.getId());
			}
			List<News> nn = new ArrayList<News>();
			num = 2;

			for (News n : ndao.Hottest(num, OpeFunction.getNowTime())) {
				System.out.println("最热新闻id-" + n.getId());
				nn.add(n);
			}
			List<News> n4 = new ArrayList<News>();
			num = 2;
			if (ndao.Hotarea(num, area).size() < 2) {
				area = "北京";
				areas = "海淀区";
			}
			for (News ns : ndao.Hotarea(num, area)) {
				n4.add(ns);
			}
			System.out.println("省：" + area);
			System.out.println("市：" + areas);

			System.out.println("全国最新有-" + nt.size() + ",全国最热-" + nn.size() + "本地新闻-" + n4.size());

			String result = "{\"Hottime\":" + util.ToJson(nt) + ",\"Hottest\":" + util.ToJson(nn) + ",\"Hotarea\":"
					+ util.ToJson(n4) + "}";
			util.Out().print(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

	/**
	 * 添加收藏 通过 用户id 新闻id
	 * 
	 * @throws IOException
	 */
	public void Csave() throws IOException {
		try {
			System.out.println("进入新闻收藏userid:" + userid + "newsid:" + newsid);
			if (cdao.unid(userid, newsid) != null) {
				System.out.println("已经收藏过!");
				util.Out().print(false);
			} else {
				User u = userdao.byid(userid);
				if (u == null || newsid <= 0) {
					util.Out().print(false);
					return;
				}
				c.setNewsid(newsid);
				c.setUserid(userid);
				c.setTime(util.getNowTime());
				cdao.save(c);

				n = ndao.byid(newsid);
				// 收藏数
				n.setCollectnum((n.getCollectnum() == null ? 0 : n.getCollectnum()) + 1);
				// 收藏数*2+点击数
				n.setCah((n.getCollectnum() == null ? 0 : n.getCollectnum()) * 2
						+ (n.getHits() == null ? 0 : n.getHits()));
				ndao.Upnews(n);
				System.out.println("收藏成功!");
				util.Out().print(true);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 后台管理查看新闻详情
	 * 
	 * @throws IOException
	 */
	public String adminNewsId() throws IOException {

		System.out.println("后台管理查看新闻详情通过id查询新闻" + id);
		if (id <= 0) {
			util.Out().print("没有获取到新闻id");
			System.out.println("没有获取到新闻id");
			return null;
		}

		rl = rdao.Alln(id);
		if (rl.size() != 0) {
			System.out.println("有评论");
			for (int i = 0; i < rl.size(); i++) {

				ul.add(userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername()));
			}
			System.out.println("评论" + rl.size());

			request.setAttribute("ul", ul);
			request.setAttribute("rl", rl);
		}

		n = ndao.byid(id);

		// 收藏数
		n.setCollectnum((n.getCollectnum() == null ? 0 : n.getCollectnum()) + 1);
		// 收藏数*2+点击数
		n.setCah((n.getCollectnum() == null ? 0 : n.getCollectnum()) * 2 + (n.getHits() == null ? 0 : n.getHits()));

		System.out.println("点击数:" + n.getHits() + "收藏数+点击数:" + n.getCah() + "收藏数:" + n.getCollectnum());

		ndao.Upnews(n);
		request.setAttribute("n", n);

		return Action.SUCCESS;
	}

	/**
	 * 删除收藏 通过 用户id 新闻id
	 * 
	 * @throws IOException
	 */
	public void RemoveC() throws IOException {
		try {

			String[] st = summary.split(",");
			for (int i = 0; i < st.length; i++) {

				System.out.println("summary:" + st[i]);
			}

			if (cdao.unid(userid, newsid) != null) {
				cdao.remove(cdao.unid(userid, newsid));
				n = ndao.byid(newsid);
				// 收藏数
				n.setCollectnum((n.getCollectnum() == null ? 0 : n.getCollectnum()) - 1);
				// 收藏数*2+点击数
				n.setCah((n.getCollectnum() == null ? 0 : n.getCollectnum()) * 2
						+ (n.getHits() == null ? 0 : n.getHits()));
				ndao.Upnews(n);
				util.Out().print(true);
			} else {
				util.Out().print(false);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 删除收藏 通过 用户id 新闻id
	 * 
	 * @throws IOException
	 */
	public void RemoveCWeb() throws IOException {
		try {
			User u = (User) session.getAttribute("u");
			if (u == null) {

				System.out.println("请重新登入!");
				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");

				return;
			}
			userid = u.getId();
			String[] st = summary.split(",");
			for (int i = 0; i < st.length; i++) {
				if (OpeFunction.isEmpty(st[i])) {
					continue;
				}
				newsid = Integer.parseInt(st[i].toString());
				System.out.println("获取到底 summary:" + st[i]);

				if (cdao.unid(userid, newsid) != null) {
					cdao.remove(cdao.unid(userid, newsid));
					n = ndao.byid(newsid);
					// 收藏数
					n.setCollectnum((n.getCollectnum() == null ? 0 : n.getCollectnum()) - 1);
					// 收藏数*2+点击数
					n.setCah((n.getCollectnum() == null ? 0 : n.getCollectnum()) * 2
							+ (n.getHits() == null ? 0 : n.getHits()));
					ndao.Upnews(n);
					util.Out().print(true);
				} else {
					util.Out().print(false);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 判断是否收藏
	 */
	public void Whether() {
		try {

			if (cdao.unid(userid, newsid) == null) {
				util.Out().print(false);
			} else {
				util.Out().print(true);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 查询收藏 通过用户id进行查询
	 */
	public void SearchBookmark() throws IOException {
		try {
			System.out.println("进入了SearchBookmark");
			User u = userdao.byid(userid);
			if (u == null) {
				util.Out().print("null");
				return;
			}
			for (Collect c : cdao.Allu(userid)) {
				n = ndao.byid(c.getNewsid());
				if (n != null) {
					nl.add(n);
				}

			}
			System.out.println("用户" + u.getUsername() + "收藏了" + nl.size() + "个文章");
			if (nl.size() == 0) {
				util.Out().print("null");
			} else {

				util.Out().print(util.ToJson(nl));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 删除评论 通过 评论的reviewid 用户 username
	 * 
	 * @throws IOException
	 */
	public void RemoveR() throws IOException {
		try {
			System.out.println("删除评论方法RemoveR");
			System.out.println("reviewid+" + reviewid);
			System.out.println("username+" + username);
			if (rdao.byid(reviewid, username) == null) {
				util.Out().print("null");
				System.out.println("评论  为空");
				return;
			}
			newsid = rdao.byid(reviewid, username).getNewsid();
			rdao.remove(rdao.byid(reviewid, username));

			n = ndao.byid(newsid);
			System.out.println("修改代码");
			System.out.println("newsid是" + n.getId());
			System.out.println("该文章被评论" + rdao.Alln(newsid).size());
			n.setReviews(rdao.Alln(newsid).size());
			ndao.Upnews(n);

			rl = rdao.unid(username, newsid);
			if (rl.size() > 0) {
				System.out.println("有评论");
				util.Out().print(util.ToJson(rl));
				return;
			} else {
				System.out.println("没有评论");
				util.Out().print("null");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 添加评论 通过 用户username 新闻newsid
	 * 
	 * @throws IOException
	 */
	public void Rsave() throws IOException {
		try {
			// username="gyn781369549";
			System.out.println("进入添加评论Rsave" + username);

			if (newsid <= 0 || username == null) {
				util.Out().print("null");
				return;

			}

			if (username == null) {
				util.Out().print("null");
				return;
			}
			User u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("null");
				return;
			}
			r.setNewsid(newsid);
			r.setUsername(username);
			r.setTime(util.getNowTime());
			r.setReview(review);
			rdao.save(r);
			// 查询该文章被评论多少次 重新写入
			n = ndao.byid(newsid);
			if (n == null) {
				util.Out().print("null");
				return;

			}
			rl = rdao.Alln(newsid);
			int a = rl.size();
			System.out.println("是否有新闻" + n != null);
			System.out.println("评论内容" + review);
			System.out.println("newsid是" + n.getId());
			System.out.println("该文章被评论" + a);
			n.setReviews(a);
			ndao.Upnews(n);

			if (a > 0) {
				System.out.println("有评论");
				util.Out().print(util.ToJson(rl));

			} else {
				System.out.println("没有评论");
				util.Out().print("null");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 通过newsid查询 评论
	 * 
	 * @throws IOException
	 */
	public void ReviewsInquiry() throws IOException {
		System.out.println("newsid是-" + newsid);
		rl = rdao.Alln(newsid);
		if (rl.size() > 0) {
			System.out.println("有评论");
			util.Out().print(util.ToJson(rl));

		} else {
			System.out.println("没有评论");
			util.Out().print("null");
		}

	}

	/**
	 * 通过newsid查询 评论
	 * 
	 * @throws IOException
	 */
	public void ReviewsInquiry513() throws IOException {
		System.out.println("newsid是-" + newsid);
		rl = rdao.Alln(newsid);
		if (rl.size() > 0) {
			System.out.println("有评论");
			for (int i = 0; i < rl.size(); i++) {
				User u1 = userdao.byUsernameAccnumnoPhone(rl.get(i).getUsername());

				ul.add(u1);
			}
			System.out.println("有评论");
			String result = "{\"rl\":" + util.ToJson(rl) + ",\"ul\":" + util.ToJson(ul) + "}";
			util.Out().print(result);

		} else {
			System.out.println("没有评论");
			util.Out().print("null");
		}

	}

	/**
	 * 通过username查询评论 新闻和对每个新闻的最新 评论
	 * 
	 * @throws IOException
	 */
	public void Reviewsusername() throws IOException {
		try {

			List<Integer> rn = new ArrayList<Integer>();// 收藏 List
			for (Review r1 : rdao.Allu(username)) {

				Boolean b = true;

				for (int i = 0; i < rn.size(); i++) {

					if (rn.get(i) == r1.getNewsid()) {

						b = false;
						break;
					}

				}

				if (b) {
					System.out.println("我评论过的新闻  用户名和新闻id" + username + "+" + r1.getNewsid() + "时间是：" + r1.getTime());
					rl.add(rdao.unid(username, r1.getNewsid()).get(0));
					nl.add(ndao.byid(r1.getNewsid()));

					rn.add(r1.getNewsid());
				}

			}
			String result = "{\"news\":" + util.ToJson(nl) + ",\"review\":" + util.ToJson(rl) + "}";
			if (nl.size() > 0 && rl.size() > 0) {
				util.Out().print(result);

			} else {
				util.Out().print("null");
			}
		} catch (Exception e) {
			System.out.println("异常是" + e.getMessage());
		}
	}

	/**
	 * 通过用户名 username newsid 查询评论
	 * 
	 * @param ndao
	 * @param cdao
	 * @param rdao
	 * @throws IOException
	 */
	public void getRy() throws IOException {

		rl = rdao.unid(username, newsid);
		if (rl.size() <= 0) {
			util.Out().print("null");
		} else {
			util.Out().print(util.ToJson(rl));
		}
	}

	public NewsAction(UserDAO userdao, NewsDAO ndao, CollectDAO cdao, ReviewDAO rdao) {
		super();
		this.userdao = userdao;
		this.ndao = ndao;
		this.cdao = cdao;
		this.rdao = rdao;
	}

	public int getReviewid() {
		return reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Collect getC() {
		return c;
	}

	public void setC(Collect c) {
		this.c = c;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public String getTimet() {
		return timet;
	}

	public void setTimet(String timet) {
		this.timet = timet;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getTp() {
		return tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public File getImgFilemax() {
		return imgFilemax;
	}

	public void setImgFilemax(File imgFilemax) {
		this.imgFilemax = imgFilemax;
	}

	public String getImgFilemaxFileName() {
		return imgFilemaxFileName;
	}

	public void setImgFilemaxFileName(String imgFilemaxFileName) {
		this.imgFilemaxFileName = imgFilemaxFileName;
	}

	public String getImgFilemaxContentType() {
		return imgFilemaxContentType;
	}

	public void setImgFilemaxContentType(String imgFilemaxContentType) {
		this.imgFilemaxContentType = imgFilemaxContentType;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getExpert() {
		return expert;
	}

	public void setExpert(int expert) {
		this.expert = expert;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public File getXlsxFile() {
		return xlsxFile;
	}

	public void setXlsxFile(File xlsxFile) {
		this.xlsxFile = xlsxFile;
	}

}
