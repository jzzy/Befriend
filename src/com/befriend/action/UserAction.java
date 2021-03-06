package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.befriend.dao.ApputilDAO;
import com.befriend.dao.GroupDAO;
import com.befriend.dao.RegistrationDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.Cis;
import com.befriend.entity.Password;
import com.befriend.entity.Profile;
import com.befriend.entity.Registrationsa;
import com.befriend.entity.Stas;
import com.befriend.entity.User;
import com.befriend.udp.UDPServer;
import com.befriend.util.OpeFunction;
import com.befriend.util.Vcom_3DES;
import com.befriend.wechat.RefreshAccessToken;
import com.befriend.wechat.WechatKit;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("all")
public class UserAction extends ActionSupport {
	private Log log = LogFactory.getLog(UserAction.class);
	private User u = new User();
	public OpeFunction util;
	private ApputilDAO adao;// ApputilDAO
	private UserDAO userdao;
	private GroupDAO gdao;
	private RegistrationDAO regdao;

	List<User> us = new ArrayList<User>();
	HttpServletRequest request = ServletActionContext.getRequest();
	private String code;// 验证码
	List<Cis> cis = new ArrayList<Cis>();// 聊天记录
	// String account = "cf_wcskdxyz";//用户名 cf_wcsk_jztd
	// String account = "cf_wcsk_jztd";// 用户名 cf_wcsk_jztd
	// String pwd = "wcsk1212";// 密码 wcsk1212
	// String postUrl =
	// "http://106.ihuyi.cn/webservice/sms.php?method=Submit";// 地址

	private String phone;// 手机号
	private String username;// 用户名
	private String password;// 密码
	private String newpwd;// 新密码密码
	private int id;// 用户id
	private String school;// 所在学校
	private String stage;// 阶段
	private String time = util.getNowTime();// 时间
	private String nickname;// 昵称
	private String address;// 地址 省级
	private String os = "web";// 系统
	private String addcity;// 地址 市级
	private HttpSession session = ServletActionContext.getRequest().getSession();

	private int competence2 = 0;// 用户id competence 用户权限 0-普通用户 1-市级论坛管理员
								// 2-省级论坛管理员
	private int gag = 0;// 0是正常 1 是 禁言
	private int pageSize = 50;// 每页显示 多少条数据
	private int currentPage = 1;// 这是第多少页

	private String timeq;// 查询开始时间

	private String timeh;// 查询结束时间
	private File file;// logo图片
	private String fileFileName;// 文件名
	private String fileContentType;// 文件类型

	private String key;// key
	private String accnumno;//
	private int port = 0;//
	private String ip;// 用户ip
	private String province;// 获取前端的 省级
	private String city;// 获取前端的 市级
	// 环信用户注册
	private final String URL = "https://a1.easemob.com/topLong/wcfriend/users";
	private Password pd = new Password();

	private String userName;// 对应nickname
	private String userPhone;// phone
	private String userId;// username

	/**
	 * 查看统计信息
	 * 
	 * @throws IOException
	 */
	public String ViewStatistics() throws IOException {

		String url = "http://127.0.0.1/Befriend/aStas";
		WechatKit.sendGet(url);
		List<Stas> ios = adao.StasTime("all", "ios");
		List<Stas> android = adao.StasTime("all", "android");
		List<Stas> web = adao.StasTime("all", "web");
		List<Stas> other = adao.StasTimeother("all");
		List<Stas> syn = adao.StasTime("all", "syn");
		List<Stas> bbt = adao.StasTime("all", "bbt");
		request.setAttribute("bbt", bbt);
		request.setAttribute("ios", ios);
		request.setAttribute("android", android);
		request.setAttribute("web", web);
		request.setAttribute("other", other);
		request.setAttribute("syn", syn);
		return Action.SUCCESS;

	}

	/**
	 * sha1 加密
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public void sha1() throws NoSuchAlgorithmException, IOException {
		int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
		System.out.println("排序之前：");
		// 插入排序
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		// 直接插入排序
		for (int i = 1; i < a.length; i++) {
			// 待插入元素
			int temp = a[i];
			int j;

			for (j = i - 1; j >= 0; j--) {
				// 将大于temp的往后移动一位
				if (a[j] > temp) {
					a[j + 1] = a[j];
				} else {
					break;
				}
			}
			a[j + 1] = temp;
			System.out.println("=========");
			for (int x = 0; x < a.length; x++) {
				if (x == a.length - 1) {
					System.out.println(a[x]);
				} else {
					System.out.print(a[x] + " ");
				}

			}
		}
		System.out.println();
		System.out.println("排序之后：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

		// 冒泡排序
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				// 这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		System.out.println();
		System.out.println("排序之后：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		System.out.println("进入sha1" + id);

	}

	/**
	 * 后台注销手机号
	 * 
	 * @throws IOException
	 */
	public void userLogout() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("你不是管理!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("你不是管理!");
			return;
		}
		u = userdao.byid(id);
		if (u == null) {
			util.Out().print("没有这个用户!");
			return;
		}
		u.setPhone(null);
		userdao.update(u);
		util.Out().print("注销成功!");

	}

	/**
	 * 一键注册 用户号
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void accnumnoczc() throws InterruptedException, IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("你不是管理!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("你不是管理!");
			return;
		}

		us = userdao.getUserAll();
		for (int i = 0; i < us.size(); i++) {
			u = us.get(i);
			if (u == null) {
				System.out.println("用户为空！");
				continue;

			}
			if (u.getAccnumno() != null) {
				System.out.println("用户有账号了！:" + u.getAccnumno() + "第" + i + "个！".toString());
				continue;
			}
			boolean b = true;
			while (b) {
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}
			u.setAccnumno(accnumno);
			userdao.update(u);
			System.out.println("生成账号成功！第 " + i + " 个！账号为:" + accnumno);
			util.Out().print("同步完成!");
			// Thread.sleep(1000);
		}

	}

	/**
	 * 注册环信 一键注册全部用户
	 * 
	 * @throws JSONException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public synchronized void huanxinzc() throws JSONException, InterruptedException, IOException {

		System.out.println("进入huanxinzc");
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("你不是管理!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("你不是管理!");
			return;
		}
		us = userdao.getUserAll();
		for (int i = 0; i < us.size(); i++) {
			if (us.get(i) == null) {
				continue;
			}
			Thread.sleep(40);
			JSONObject json = new JSONObject();
			json.put("username", us.get(i).getId()); // 用户id
			json.put("password", "123456"); // 用户密码
			if (RefreshAccessToken.access_token == null) {
				util.Out().print("没有获取到access_token!");
				System.out.println("没有获取到access_token!");
				return;
			}
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			util.Out().println("第" + (i + 1) + "用户id-" + us.get(i).getId() + "-注册 返回:" + w + "=400已注册");
			System.out.println("第" + (i + 1) + "用户id-" + us.get(i).getId() + "-注册 返回:" + w + "=400已注册");
		}
		util.Out().print("同步完成!");

	}

	/**
	 * 一键下线
	 * 
	 * @throws IOException
	 */
	public void updateAllXiaxian() throws IOException {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("你不是管理!");
			return;
		}
		if (admin.getLevel() != 1) {
			util.Out().print("你不是管理!");
			return;
		}
		userdao.updateAllXiaxian();
		System.out.println("一键下线成功!");
		((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/GetUserAll");

	}

	/**
	 * 发送方法
	 * 
	 * @throws InterruptedException
	 */
	public void onlineUserSend() throws IOException, InterruptedException {
		System.out.println("id:" + id);
		u = userdao.byid(id);
		if (u != null) {
			cis = gdao.Cisuid(u.getId());
			System.out.println("用户有:" + u.getUsername() + cis.size() + "个未读消息!" + "Online:" + u.getOnline());

			if (cis.size() == 0) {
				OpeFunction.Out().print(true);
				return;
			}

			for (int i = 0; i < cis.size(); i++) {
				if (cis.get(i).getOnline() == 1) {
					continue;
				}
				Profile profile = gdao.FindProfilebyid(cis.get(i).getGroupid(), cis.get(i).getSenduserid());
				User su = userdao.byid(cis.get(i).getSenduserid());

				String result = "{\"u\":" + util.ToJson(su) + ",\"cis\":" + util.ToJson(cis.get(i)) + ",\"profile\":"
						+ util.ToJson(profile) + "}";

				String ip = u.getIp();

				port = u.getPort();

				if (ip != null && port > 0) {

					try {

						UDPServer.Send(result, ip, port);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}

			util.Out().print(true);

		} else {// 判断连接的 用户是否存在!
			util.Out().print(false);

		} // 判断连接的 用户是否存在!

	}

	/**
	 * 接收用户发送的udp 获取ip port
	 * 
	 * @throws IOException
	 */
	public void uIpPort() throws IOException {

		System.out.println("ip:" + ip);
		System.out.println("port:" + port);

		u = userdao.byid(id);
		if (u != null) {

			u.setIp(ip);
			u.setPort(port);
			userdao.update(u);
			util.Out().print(true);
		} else {

			util.Out().print(false);
		}
		System.out.println("修改成功");
	}

	/**
	 * 更改用户在线状态
	 * 
	 * @throws InterruptedException
	 */
	public void onlineStatus() throws IOException, InterruptedException {
		System.out.println("id:" + id);
		u = userdao.byid(id);
		if (u == null) {
			u = userdao.byUsernameAccnumnoPhone(username);
		}
		if (u != null) {

			System.out.println("");

			u.setOnline(0);
			userdao.update(u);
			System.out.println(id + "下线了!");
			util.Out().print(true);
			return;

			/**
			 * int uid = u.getId();
			 * 
			 * u.setOnline(1); userdao.update(u);
			 * 
			 * cis = gdao.Cisuid(uid); System.out.println("用户有:" +
			 * u.getUsername() + cis.size() + "个未读消息!" + "Online:" +
			 * u.getOnline());
			 * 
			 * Thread.sleep(5000); u = userdao.byid(uid); if (cis.size() == 0) {
			 * OpeFunction.Out().print(true); return; }
			 * 
			 * for (int i = 0; i < cis.size(); i++) {
			 * 
			 * Profile profile = gdao.FindProfilebyid(cis.get(i).getGroupid(),
			 * cis.get(i).getSenduserid());
			 * 
			 * User su = userdao.byid(cis.get(i).getSenduserid());
			 * 
			 * String result = "{\"u\":" + util.ToJson(su) + ",\"cis\":" +
			 * util.ToJson(cis.get(i)) + ",\"profile\":" + util.ToJson(profile)
			 * + "}";
			 * 
			 * String ip = u.getIp();
			 * 
			 * port = u.getPort();
			 * 
			 * if (ip != null && port > 0) {
			 * 
			 * UDPServer.Send(result, ip, port); }
			 * 
			 * }
			 */

		} else {
			util.Out().print(false);

		}

	}

	/**
	 * web用户 注销 销毁session
	 * 
	 * @throws IOException
	 */
	public void websessionrom() throws IOException {
		// 销毁session
		session.removeAttribute("u");
		System.out.println(request.getContextPath() + "/sample_lihu/jsp/mypage.jsp");
		((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/sample_lihu/jsp/mypage.jsp");
	}

	/**
	 * web用户 修改密码
	 * 
	 * @throws IOException
	 */
	public void webpassMphone() throws IOException {
		try {
			
		
		phone = (String) session.getAttribute("phone_xxx");
		u = userdao.byUsernameAccnumnoPhone(phone);
		System.out.println("phone:"+phone);
		Password pd = userdao.select(u.getId());
		if (pd != null) {

			pd.setPassword(newpwd);
			System.out.println("修改了密码"+ newpwd);
			
			userdao.update(pd);
			session.removeAttribute("u");
			session.removeAttribute("phone_xxx");
//			((HttpServletResponse) util.response())
//					.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
			util.Out().print(true);
			return;
		} else {
			util.Out().print(false);
			return;
		}
		} catch (Exception e) {
			// TODO: handle exception
			
			util.Out().print(false);
			return;
		}

	}

	/**
	 * web用户 修改密码
	 * 
	 * @throws IOException
	 */
	public void webpassModification() throws IOException {

		User u = (User) session.getAttribute("u");
		if (u == null) {

			System.out.println("请重新登入!");
			((HttpServletResponse) util.response())
					.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");

			return;
		}
		Password pd = userdao.login(u.getId(), password);
		if (pd != null) {
			// 新密码进行判断
			String reg = "[A-Za-z0-9_]{6,18}";

			if (newpwd.matches(reg)) {
				pd.setPassword(newpwd);
				System.out.println("修改了密码");
				util.Out().print("密码修改成功!" + newpwd);
				userdao.update(pd);
				session.removeAttribute("u");
				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");
				return;
			} else {
				util.Out().print("没有获取到新密码!" + newpwd);
				return;
			}

		} else {
			util.Out().print("原密码错误!");
			return;
		}
	}

	/**
	 * web 用户 修改 & 完善 信息
	 * 
	 * @throws IOException
	 */
	public void webModification() throws IOException {
		try {

			User u = (User) session.getAttribute("u");
			if (u == null) {

				System.out.println("请重新登入!");
				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");

				return;
			}

			u = userdao.byid(u.getId());
			System.out.println("进入webModification");
			System.out.println("用户名是" + username);
			System.out.println("地址省级" + province);
			System.out.println("地址市级" + city);
			System.out.println("孩子阶段" + stage);
			System.out.println("手机是" + phone);
			System.out.println("学校是" + school);
			System.out.println("密码是" + password);
			System.out.println("昵称是" + nickname);

			if (u == null) {

				System.out.println("请重新登入!");
				((HttpServletResponse) util.response())
						.sendRedirect(request.getContextPath() + "/sample_lihu/htm/login.html");

				return;
			}

			// String newpassword = request.getParameter("newpassword");
			// System.out.println("密码为 " + password);
			pd = userdao.select(u.getId());
			if (password != null && password != " " && pd != null) {

				// 验证密码
				String reg = "[A-Za-z0-9_]{6,18}";
				if (password.matches(reg)) {
					pd.setPassword(password);
					userdao.update(pd);
					System.out.println("修改成功!新密码为:" + password);
					util.Out().print(true);
					// return;

				} else {
					System.out.println("密码不符合规范" + password);
					util.Out().print("false");
					return;
				}
			}

			String path = "";
			if (u.getImg() != null) {
				path = u.getImg();
			}
			if (file != null) {

				//util.fileRemove(path);
				path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				if(OpeFunction.imgNarrow(100, 100, ServletActionContext.getServletContext().getRealPath(
						pah), ServletActionContext.getServletContext().getRealPath(
								pah),".jpg")){
					u.setImg(pah.split(".jpg")[0]+".JPEG");
				}
				
				

			}
			if (nickname != null) {
				u.setNickname(nickname);
				System.out.println("修改了昵称");
			}
			if (phone != null) {
				// u.setPhone(phone);
				System.out.println("修改了手机号");
			}
			// =0 为普通用户可以随时修改地址 管理员需要先取消管理员 在进行修改

			if (u.getCompetence() == 0) {
				if (city != null && !city.equals("城市名") && city != "") {
					u.setAddcity(city);
					System.out.println("修改了地址市级");
				} else {

					Object Ocity = session.getAttribute("city");
					System.err.print("session中的市级为" + Ocity);
					if (Ocity != null) {
						city = Ocity.toString();
						u.setAddcity(city);
					}

				}
				if (province != null && !province.equals("省份名") && province != "") {
					u.setAddress(province);
					System.out.println("修改了地址省级");
				} else {

					Object Oprovince = session.getAttribute("province");
					if (Oprovince != null) {
						province = Oprovince.toString();
						u.setAddress(province);
					}

				}
			} else {
				System.out.println(u.getUsername() + "是管理员!");
			}

			// 验证用户名
			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			System.out.println(username);
			System.out.println(password);
			if (username != null) {
				if (!username.matches(reg)) {
					System.out.println("用户名格式不对！");
					util.Out().print("用户名格式不对！");
					return;
				}
			}
			if (u.getUsername() == null) {
				System.out.println("修改了用户名!");
				u.setUsername(username);
			}
			if (school != null) {
				u.setSchool(school);
				System.out.println("修改了孩子学校");
			}

			if (stage != null) {
				u.setStage(stage);
				System.out.println("修改了孩子阶段");
			}

			userdao.update(u);
			session.setAttribute("u", u);

			((HttpServletResponse) util.response())
					.sendRedirect(request.getContextPath() + "/sample_lihu/jsp/mypage.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * web 用户注册 只需要 手机号 用户名 密码 即可
	 * 
	 * @throws IOException
	 * @throws JSONException
	 */
	public  void webRegistration() throws IOException, JSONException {

		phone = request.getParameter("phone");
		password = request.getParameter("password");
		username = request.getParameter("username");

		if (password == null) {
			System.out.println("密码为空");
			OpeFunction.Out().print("null");
			return ;
		}
		if (phone == null) {
			System.out.println("手机号为空");
			OpeFunction.Out().print("null");
			return ;
		}
		// 验证用户名
		String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
		System.out.println(username);
		System.out.println(password);
		if (username != null) {
			if (!username.matches(reg)) {
				System.out.println("用户名格式不对！");
				//util.Out().print("用户名格式不对！");
				OpeFunction.Out().print("null");
				return ;
			}

		}
		// 验证密码
		reg = "[A-Za-z0-9_]{6,18}";
		if (!password.matches(reg)) {
			System.out.println("密码格式不对！");
			OpeFunction.Out().print("null");
			return ;
		}
		
		// 验证手机号
		String regp = "[0-9]{11}";
		if (!phone.matches(regp)) {
			System.out.println("密码格式不对！");
			OpeFunction.Out().print("null");
			return ;
		}
		System.out.println("进入web用户注册手机号为:" + phone);
		System.out.println("进入时间" + util.getNowTime());
		System.out.println("手机号" + phone);
		System.out.println("用户名" + username);
		boolean b = true;

		while (b) {

			accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
			if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
				b = false;
			}

		}
		// 判断手机号是否注册过
		if (userdao.byUsernameAccnumnoPhone(phone) != null) {
			System.out.println("此手机号  已经注册过");
			OpeFunction.Out().print(true);
			return ;
		}

		u.setAccnumno(accnumno);
		if (username != null) {
			u.setUsername(username);
		}
		u.setPhone(phone);
		u.setStage("未填写");
		u.setAddress("北京");
		u.setAddcity("海淀区");
		u.setNickname(username);// 没有设置过显示用户名
		u.setSchool("未填写");
		u.setTime(util.getNowTime());
		u.setCompetence(0);// 普通用户
		u.setGag(0);// 可以创建论坛
		u.setCome(User.OWN);// 来自家长之友
		u.setOs(User.WEB);
		u.setImg("/SimulationApp/images/logod.png");
		if (userdao.byUsernameAccnumnoPhone(username) != null) {
			System.out.println("此用户名  已经注册过");
			OpeFunction.Out().print(false);
			return ;
		}
		userdao.save(u);
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u == null) {
			OpeFunction.Out().print(false);
			return ;
		}
		pd.setUid(u.getId());
		pd.setPassword(password);
		userdao.save(pd);
		session.setAttribute("u", u);
		System.out.println("注册成功" + "username:" + username + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(phone);
		if (u != null) {
			// 封装个json
			// 获取token的
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456"); // 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
		}
		//OpeFunction.Out().print(true); 

		
	}

	/**
	 * 省级管理 模糊查询user信息
	 */
	public String getUsermhh() throws IOException {

		User ue = (User) session.getAttribute("useradmin");
		Map session = (Map) util.getsession();// 创建 map

		if (ue == null) {
			util.Out().print("用户管理为空");
			return null;
		}
		if (!OpeFunction.isEmpty(username)) {
			us = userdao.likeusername(username, ue.getAddress());
		}

		System.out.println("username:" + username);

		request.setAttribute("usaha", us);

		return Action.SUCCESS;

	}

	/**
	 * 给user赋予权限
	 * 
	 * @throws IOException
	 *             b
	 * @throws InterruptedException
	 */
	public void SetQU() throws IOException, InterruptedException {

		User ue = (User) session.getAttribute("useradmin");
		System.out.println("省级管理 管理用户");
		System.out.println("id是-" + id);
		u = userdao.byid(id);
		if (u == null || ue == null) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/Judge/No.html");
			return;
		}
		if (u.getId().equals(ue.getId())) {
			// util.Out().print("非法操作");
			System.out.println("自己不可以更改自己的权限!");
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/Judge/No.html");
			return;
		}
		if (!u.getAddress().equals(ue.getAddress()) || u.getAddress() == null) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/Judge/No.html");
			return;
		}
		System.out.println("competence2是-" + competence2);
		if (competence2 == 2) {

			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/Judge/No.html");

			return;
		}
		if (competence2 == 3) {
			// 取消授权
			u.setCompetence(0);
			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/getUadmin");
			return;
		}

		if (competence2 == 1) {

			if (u.getAddcity() == null) {
				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/Judge/No.html");

				return;
			}
			u.setCompetence(competence2);

			userdao.update(u);
			util.Out().print("成功赋予市级论坛管理员权限");
			// 等待3s
			// Thread.sleep(3000);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/getUsera");
			return;
		}

	}

	/**
	 * 查询user信息
	 */
	public String getUserine() throws IOException {
		User ue = (User) session.getAttribute("useradmin");

		if (ue == null) {
			util.Out().print("请重新登入");
			return null;
		}

		u = userdao.byUsernameAccnumnoPhone(username);
		if (!u.getAddress().equals(ue.getAddress())) {
			util.Out().print("没有权限");
			return null;
		}
		System.out.println("查询user信息username:" + username);

		request.setAttribute("u", u);
		return Action.SUCCESS;

	}

	/**
	 * 获取user信息
	 */
	public String getUsera() throws IOException {
		u = (User) session.getAttribute("useradmin");

		if (u == null) {
			util.Out().print("系统异常");
			return null;
		}

		competence2 = userdao.getUser(u.getAddress()).size();// 获取 共有多少条数据

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// 获取 共有多少页
		} else {
			competence2 = competence2 / pageSize + 1;// 获取 共有多少页
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUser(pageSize, currentPage, u.getAddress());
		request.setAttribute("us", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);

		return Action.SUCCESS;

	}

	/**
	 * 获取user管理员信息
	 */
	public String getUadmin() throws IOException {
		u = (User) session.getAttribute("useradmin");

		if (u == null) {
			util.Out().print("系统异常");
			return null;
		}

		competence2 = userdao.getUseradmin(u.getAddress()).size();// 获取 共有多少条数据

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// 获取 共有多少页
		} else {
			competence2 = competence2 / pageSize + 1;// 获取 共有多少页
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUseradmin(pageSize, currentPage, u.getAddress());

		request.setAttribute("usa", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);
		return Action.SUCCESS;

	}

	/**
	 * 按照时间查询 地区 用户数量
	 * 
	 * @throws IOException
	 */
	public String GetUsertimeares() throws IOException {

		System.out.println("进入GetUsertimeares方法！用户名为" + username);
		System.out.println("起始时间" + timeq);
		System.out.println("截止时间" + timeh);
		System.out.println("地区" + addcity);

		u = (User) session.getAttribute("useradmin");
		if (u != null) {
			System.out.println("地区是" + u.getAddress() + addcity);

			if (u.getCompetence() == 1) {
				System.out.println("市级");

				us = userdao.getUserAll(u.getAddress(), u.getAddcity(), timeq, timeh);

			}

			else if (u.getCompetence() == 2) {

				System.out.println("省级");

				if (!addcity.equals("nu")) {

					us = userdao.getUserAll(u.getAddress(), addcity, timeq, timeh);
				} else {

					us = userdao.getUserAll(u.getAddress(), timeq, timeh);

				}

			} else {

				System.out.println("请重新登入");
				util.Out().print("请重新登入");
				return null;
			}
			System.out.println("有" + us.size() + "个用户");

			request.setAttribute("GetUsertimeus", us);
			request.setAttribute("timeq", timeq);
			request.setAttribute("timeh", timeh);
		}

		return Action.SUCCESS;
	}

	/**
	 * 省级与市级 用户管理员 查询用户信息
	 */
	public String RegionalAdministrator() throws IOException {
		try {

			System.out.println(util.getNumTime(-1));

			System.out.print("username-" + username + "-password-" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("账号或密码错误");

				return null;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("密码不对!");
				util.Out().print("密码不对!");
				return null;
			}

			int cpe = 0;
			cpe = u.getCompetence();

			if (cpe == 0) {
				util.Out().print("您是普通用户！请升级您的权限");

				return null;
			}

			List<User> ul = userdao.getUserAll(u.getAddress());
			List a = new ArrayList();
			Boolean bool = true;
			for (int i = 0; i < ul.size(); i++) {

				for (int b = 0; b < a.size(); b++) {
					if (a.get(b).equals(ul.get(i).getAddcity())) {
						bool = false;
					}
				}

				if (bool) {
					a.add(ul.get(i).getAddcity());
				}
				//
				bool = true;

			}

			for (int c = 0; c < a.size(); c++) {

				System.out.println("省该的市为" + c + u.getAddress() + a.get(c));
			}

			// 今天
			String time0 = util.getNumTime(-1);
			// 七天前
			String time7 = util.getNumTime(7);
			// 一个月前
			String time30 = util.getNumTime(30);
			// 一年前
			String time365 = util.getNumTime(365);
			System.out.println("今天" + time0);
			System.out.println("7天前" + time7);
			System.out.println("30天前" + time30);
			System.out.println("365天前" + time365);

			for (int c = 0; c < a.size(); c++) {
				System.out.println("a" + a.get(c).toString() + c);
				// 查询30天以内
				int i30 = userdao.getUserAll(u.getAddress(), a.get(c).toString(), time30, time0).size();
				// 7
				int i7 = userdao.getUserAll(u.getAddress(), a.get(c).toString(), time7, time0).size();
				// 365
				int i365 = userdao.getUserAll(u.getAddress(), a.get(c).toString(), time365, time0).size();

				// 今天
				int i0 = userdao.getUserAll(u.getAddress(), a.get(c).toString(), util.getNumTime(0), time0).size();
				// 全部
				int all = userdao.getUserAll(u.getAddress(), a.get(c).toString()).size();
				System.out.println(u.getAddress() + a.get(c).toString() + i0 + i7 + i30 + i365 + all);
				// 查询是否有
				System.out.println("刷新地区为" + u.getAddress() + a.get(c).toString());
				Registrationsa rg = new Registrationsa();

				rg = regdao.addressty(u.getAddress(), a.get(c).toString());

				if (rg == null) {
					Registrationsa reg = new Registrationsa();
					reg.setAddress(u.getAddress());
					reg.setAddcity(a.get(c).toString());
					reg.setNote7(i7);
					reg.setNote30(i30);
					reg.setNote365(i365);
					reg.setNote(i0);
					reg.setNoteall(all);
					reg.setTime(time);
					regdao.save(reg);
				} else {
					rg.setNote7(i7);
					rg.setNote30(i30);
					rg.setNote365(i365);
					rg.setNote(i0);
					rg.setNoteall(all);
					rg.setTime(time);
					regdao.update(rg);
				}

			}
			session.setAttribute("useradmin", u);
			if (cpe == 1) {

				Registrationsa rga = new Registrationsa();
				System.out.println("查看的城市是" + u.getAddress() + u.getAddcity());

				rga = regdao.addressty(u.getAddress(), u.getAddcity());
				System.out.println("rg" + rga.getAddress());
				session.setAttribute("rg", rga);
				session.setAttribute("num", "1");
				return Action.SUCCESS;

			}
			if (cpe == 2) {

				List<Registrationsa> lg = regdao.address(u.getAddress());
				session.setAttribute("lg", lg);
				return Action.SUCCESS;
			}
			util.Out().print("没有权限!");
			return null;
		} catch (Exception e) {
			util.Out().print("异常" + e.getMessage());
		}
		return null;

	}

	/**
	 * 用户上传 头像
	 * 
	 * @throws IOException
	 */

	public void HeadPortrait() throws IOException {
		System.out.println("进入HeadPortrait" + username);
		if (!OpeFunction.isEmpty(username) && file != null) {

			u = userdao.byUsernameAccnumnoPhone(username);
			System.out.println("username=" + username);
			if (u != null) {
				String path = u.getImg();
				Boolean b = true;
				if (path != null) {
					//b = util.fileRemove(path);
				}
				System.out.println("头像删除是否成功？" + b);
				path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				path = util.ufileToServer(path, file, fileFileName, "jpg", true);
				System.out.println(path);
				u.setImg(path);
				userdao.update(u);
				System.out.println("头像上传成功");

				util.Out().print(path);
			} else {
				util.Out().print("nullu");
				System.out.println("没有获取到图片");
			}
		} else {
			util.Out().print("null");
			System.out.println("没有获取到用户名");
		}

	}

	/**
	 * 设置 禁止用户创建论坛
	 * 
	 * @throws IOException
	 */
	public void Gag() throws IOException {

		u = userdao.byUsernameAccnumnoPhone(username);
		System.out.println("gag==" + gag);
		if (gag == 1) {
			u.setGag(1);
			util.Out().print("对用户" + username + "禁言成功");
		} else {
			u.setGag(0);
			util.Out().print("对用户" + username + "取消禁言成功");
		}
		userdao.update(u);
	}

	/**
	 * 查询user信息
	 */
	public String getUserin() throws IOException {

		u = userdao.byid(id);
		if (u == null) {
			util.Out().print("没有这个用户");
			return null;

		}
		System.out.println("查询user信息userid:" + id);

		request.setAttribute("u", u);
		return Action.SUCCESS;

	}

	/**
	 * 模糊查询user信息
	 */
	public String getUsermh() throws IOException {
		System.out.println("username1:" + username);
		if (!OpeFunction.isEmpty(username)) {
			us = userdao.likeusername(username);
		}
		List<Password> pl = new ArrayList<Password>();
		for (int i = 0; i < us.size(); i++) {
			u = us.get(i);
			System.out.println("username2:" + u.getUsername());
			if (u != null) {
				pd = userdao.select(u.getId());
				pl.add(pd);
			} else {
				pl.add(new Password());

			}
		}

		request.setAttribute("usaha", us);
		request.setAttribute("pl", pl);
		return Action.SUCCESS;

	}

	/**
	 * 获取user管理员信息
	 */
	public String getUseradmin() throws IOException {

		competence2 = userdao.getUseradmin().size();// 获取 共有多少条数据

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// 获取 共有多少页
		} else {
			competence2 = competence2 / pageSize + 1;// 获取 共有多少页
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUseradmin(pageSize, currentPage);
		request.setAttribute("usa", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);

		return Action.SUCCESS;

	}

	/**
	 * 给user赋予权限
	 * 
	 * @throws IOException
	 */
	public void SetQ() throws IOException {

		System.out.println("id是-" + id);
		u = userdao.byid(id);

		System.out.println("competence2是-" + competence2);
		if (competence2 == 3) {
			util.Out().print("成功撤销论坛管理员权限");

			u.setCompetence(0);
			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/getUseradmin");
			return;
		}
		if (competence2 == 2) {
			if (u.getAddress() == null) {
				util.Out().print("该用户省级为空！");
				return;
			}
			u.setCompetence(competence2);
			util.Out().print("成功赋予省级论坛管理员权限");

			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/getUser");
			return;
		}
		if (competence2 == 1) {
			if (u.getAddcity() == null) {
				util.Out().print("该用户市级为空!");
				return;
			}
			u.setCompetence(competence2);
			util.Out().print("成功赋予市级论坛管理员权限");

			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/getUser");
			return;
		}
		if (competence2 == 4) {

			u.setCompetence(competence2);
			util.Out().print("成功赋予教授");

			userdao.update(u);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/getUser");
			return;
		}

	}

	/**
	 * 获取user信息
	 */
	public String getUser() throws IOException {
		/**
		 * competence2 = userdao.getUser().size();// 获取 共有多少条数据
		 * 
		 * if (competence2 % pageSize == 0) { competence2 = competence2 /
		 * pageSize;// 获取 共有多少页 } else { competence2 = competence2 / pageSize +
		 * 1;// 获取 共有多少页 } if (currentPage >= competence2) { currentPage =
		 * competence2; }
		 */
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUser(pageSize, currentPage);
		request.setAttribute("us", us);
		// request.setAttribute("competence2", competence2);
		request.setAttribute("currentPage", currentPage);

		return Action.SUCCESS;

	}

	/**
	 * 用户论坛管理员户登入 验证
	 * 
	 * @throws IOException
	 */

	public void AdminLogin() throws IOException {
		try {
			request.setAttribute("luntan", "UserAdmin.jsp");

			System.out.print("username-" + username + "-password-" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				util.Out().print("账号或密码错误");

				return;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("密码不对!");
				util.Out().print(false);
				return;
			}

			int cpe = 0;
			cpe = u.getCompetence();

			if (cpe == 0) {
				util.Out().print("您是普通用户！请升级您的权限");

				return;
			}
			session.setAttribute("useradmin", u);

			if (cpe == 1) {

				session.setAttribute("home", request.getContextPath() + "/ForumLookuser");
				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/ForumLookuser");
				return;
			}

			if (cpe == 2) {

				session.setAttribute("home", request.getContextPath() + "/ForumLookuser");
				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/ForumLookuser");
				return;
			}

			if (cpe == 4) {

				session.setAttribute("home", request.getContextPath() + "/ForumLookuser");

				((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/ForumLookuser");
				return;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 用户登入 验证
	 * 
	 * @throws IOException
	 */
	public void Login() throws IOException {
		try {

			System.out.println("进入Login");

			System.out.println("用户名" + username);
			System.out.println("密码" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				System.out.println("账户为空");
				util.Out().print("null");
				return;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("密码不对!");
				util.Out().print(false);
				return;
			}

			System.out.println("登入成功！");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			if (os.equals(User.ANDROID)) {
				u.setOs(User.ANDROID);
			}
			if (os.equals(User.IOS)) {
				u.setOs(User.IOS);
			}
			u.setFinaltime(time);
			u.setOnline(1);
			userdao.update(u);
			u.setPassword(pd.getPassword());
			util.Out().print(util.ToJson(u));
			// 更新在线用户数量
			time = util.getNumTime(0);
			String url = "";
			if (province != null) {

			} else {
				url = "http://127.0.0.1/Befriend/aStas?os=" + os;
			}
			url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 用户登入 验证
	 * 
	 * @throws IOException
	 */
	public void newLogin() throws IOException {
		try {

			System.out.println("进入newLogin");

			System.out.println("用户名" + username);
			System.out.println("密码" + password);

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				System.out.println("账户为空");
				util.Out().print("null");
				return;
			}
			Password pd = userdao.login(u.getId(), password);
			if (pd == null) {
				System.out.println("密码不对!");
				util.Out().print("false");
				return;
			}

			System.out.println("登入成功！");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}

			u.setFinaltime(time);

			userdao.update(u);
			String result = "{\"user\":" + util.ToJson(u) + ",\"pwd\":" + util.ToJson(pd) + "}";
			util.Out().print(result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * syn 用户登入
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public void synLogin() throws IOException, NoSuchAlgorithmException {

		System.out.println("进入synLogin");

		System.out.println("用户名" + username);
		System.out.println("密码" + password);

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u == null) {
			System.out.println("账户为空");
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}
		Password pd = userdao.login(u.getId(), password);
		if (pd == null) {
			System.out.println("密码不对!");
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}

		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setOnline(1);
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			// 更新在线用户数量
			time = util.getNumTime(0);
			String url = "";
			url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			session.setAttribute("u", u);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");

		}

	}

	/**
	 * WEB 用户登入 验证
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public void webLogin() throws IOException, NoSuchAlgorithmException {

		System.out.println("进入webLogin");

		System.out.println("用户名" + username);
		System.out.println("密码" + password);

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u == null) {
			System.out.println("账户为空");
			util.Out().print(false);
			return;
		}
		Password pd = userdao.login(u.getId(), password);
		if (pd == null) {
			System.out.println("密码不对!");
			util.Out().print(false);
			return;
		}

		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setOnline(1);
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			// u.setPassword(pd.getPassword());
			// 更新在线用户数量
			time = util.getNumTime(0);
			u.setOs(User.WEB);
			session.setAttribute("u", u);
			// ((HttpServletResponse) util.response()).sendRedirect(request
			// .getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			String url = "";
			if (u.getAddress() != null) {
				url = "http://127.0.0.1/Befriend/aStas?os=" + os + "&province=" + u.getAddress();
			} else {
				url = "http://127.0.0.1/Befriend/aStas?os=" + os;
			}
			url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			util.Out().print(true);
		}

	}

	/**
	 * 通过手机号找回密码找回密码
	 */
	public void appSendCodePasswrod() throws IOException {
		try {
			System.out.println("进入appSendCodePasswrod" + phone);
			//
			String regp = "[0-9]{11}";
			if (phone == null) {
				System.out.println("手机号空！");
				util.Out().print(false);
				return;
			}
			if (!phone.matches(regp)) {
				System.out.println("手机号格式不对！");
				util.Out().print(false);
				return;
			}

			System.out.println("进入appSendCode，手机号为:" + phone);

			System.out.println("进入时间" + util.getNowTime());

			System.out.println("手机号" + phone);
			if (userdao.byUsernameAccnumnoPhone(phone) == null) {
				System.out.println("此手机号没有注册过");

				util.Out().print(false);

				return;
			}

			int num = (int) ((Math.random() * 9 + 1) * 100000);

			session.setAttribute("app_code", String.valueOf(num));
			
			session.setMaxInactiveInterval(90);
			session.setAttribute("phone_xxx", String.valueOf(phone));
			System.out.println("1正确验证码为" + num);

			String content = new String("您的验证码是：" + num + "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！");
			util.setphone(phone, content);
			util.Out().print(true);

		} catch (Exception e) {
			// TODO: handle exception密码是
			e.printStackTrace();
		}

	}

	/**
	 * 向 手机号 发送验证 phone
	 * 
	 * @throws IOException
	 */
	public void appSendCode() throws IOException {
		try {
			System.out.println("进入appSendCode");
			System.out.println("phone:" + phone);
			//
			String regp = "[0-9]{11}";

			if (phone == null) {

				util.Out().print("null");
				return;
			}

			if (!phone.matches(regp)) {
				System.out.println("手机号格式不对！");
				util.Out().print("null");
				return;
			}

			System.out.println("进入appSendCode，手机号为:" + phone);

			System.out.println("进入时间" + util.getNowTime());

			System.out.println("手机号" + phone);
			if (userdao.byUsernameAccnumnoPhone(phone) != null) {
				System.out.println("此手机号  已经注册过");

				util.Out().print(false);

				return;
			}

			int num = (int) ((Math.random() * 9 + 1) * 100000);

			session.setAttribute("app_code", String.valueOf(num));
			session.setMaxInactiveInterval(90);
			System.out.println("1正确验证码为" + num);

			String content = new String("您的验证码是：" + num + "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！");
			util.setphone(phone, content);
			util.Out().print(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 判断验证码 是否正确
	 * 
	 * @throws IOException
	 */
	public void appCheckCode() throws IOException {
		try {

			System.out.println("判断时间" + util.getNowTime());

			String codes = (String) session.getAttribute("app_code");

			System.out.println("session中!正确验证码为" + codes);
			System.out.println("用户输入的验证码code为" + code);

			if (codes == null) {
				System.out.println("session已过期");
				util.Out().print("null");
				return;
			}
			if (codes.equalsIgnoreCase(code)) {
				System.out.println("验证码" + code + "正确");
				util.Out().print(true);
				session.removeAttribute("app_code");
			} else {
				System.out.println("验证码" + code + "错误");
				util.Out().print(false);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * 新用户注册 username password phone 不可以为null
	 * 
	 * @throws IOException
	 */
	public synchronized void save() throws IOException {

		try {
			// 验证用户名
			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			if (username != null) {
				if (!username.matches(reg)) {
					System.out.println("用户名格式不对！");
					util.Out().print(false);
					return;
				}
			}

			if (password == null) {
				util.Out().print(false);
				return;

			}
			// 验证密码
			reg = "[A-Za-z0-9_]{6,18}";
			if (!password.matches(reg)) {
				System.out.println("密码格式不对！");
				util.Out().print(false);
				return;
			}

			if (phone == null) {
				util.Out().print(false);
				return;
			}
			String regp = "[0-9]{11}";
			if (!phone.matches(regp)) {
				System.out.println("手机号格式不对！");
				util.Out().print(false);
				return;
			}

			System.out.println("用户名:" + username);
			System.out.println("密码:" + password);
			System.out.println("用户号:" + accnumno);
			if (nickname != null) {
				u.setNickname(nickname);
			} else {
				u.setNickname("未设置");
			}
			if (username != null) {
				u.setUsername(username);
				u.setNickname(username);// 没有设置过显示用户名
			}

			u.setAccnumno(accnumno);
			u.setPhone(phone);
			u.setStage("未填写");
			if (address == null) {
				u.setAddress("北京");
				u.setAddcity("海淀区");
			} else {
				u.setAddress(address);
				u.setAddcity("未设置");
			}

			u.setFinaltime(time);
			u.setSchool("未填写");
			if (os == null || os.equals("web")) {
				u.setOs("android");
			} else {
				u.setOs(os);
			}
			u.setOnline(1);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			u.setCome("own");// 来自家长之友
			u.setImg("/SimulationApp/images/logod.png");
			if (userdao.byUsernameAccnumnoPhone(phone) != null) {
				util.Out().print(false);
				return;

			}
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
			System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

			// 注册环信
			u = userdao.byUsernameAccnumnoPhone(phone);
			if (u != null) {
				if (file != null) {

					String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
					String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
					u.setImg(pah);
					userdao.update(u);
				} else {
					System.out.println("没有获取到头像!");
				}
				pd.setUid(u.getId());
				pd.setPassword(password);
				userdao.save(pd);
				JSONObject json = new JSONObject();
				json.put("username", u.getId());
				// 用户id
				json.put("password", "123456");
				// 用户密码
				String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
				System.out.println(w);
				u.setPassword(password);

				util.Out().print(util.ToJson(u));
				String url = "http://127.0.0.1/Befriend/aStas?province=" + address + "&os=" + os;
				url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
				WechatKit.sendGet(url);
				return;
			} else {
				util.Out().print("null");
				return;
			}

		} catch (Exception e) {
		}

	}

	/**
	 * 新用户注册 username password phone 不可以为null
	 * 
	 * @throws IOException
	 */
	public synchronized void synSave() throws IOException {

		try {
			String key = request.getParameter("key");
			System.out.println("key==" + key);
			if (key == null) {
				util.Out().print("keynull");
				return;
			}
			if (!key.equals("tryunk")) {
				util.Out().print("keynull");
				return;
			}
			u = userdao.byUsernameAccnumnoPhone(username);
			if (u != null) {

				try {
					String newpassword = request.getParameter("newpassword");
					if (!u.getCome().equals("syn")) {
						System.out.println("是家长之友用户");
						util.Out().print("null");
						return;
					}
					pd = userdao.select(u.getId());
					if (pd == null) {
						System.out.println("密码不对!");
						util.Out().print(false);
						return;
					}
					if (newpassword != null && newpassword != " ") {
						pd.setPassword(newpassword);
						userdao.update(pd);
						System.out.println("修改成功!新密码为:" + newpassword);
					}

					if (u.getCompetence() == 0) {

						if (addcity != null) {
							u.setAddcity(addcity);
						}
						if (address != null) {
							u.setAddress(address);
							System.out.println("修改了地址");
						}
					}

					if (nickname != null) {
						u.setNickname(nickname);
						System.out.println("修改了昵称!");

					}
					if (school != null) {
						u.setSchool(school);
						System.out.println("修改了学校!");
					}
					if (stage != null) {
						u.setStage(stage);
						System.out.println("修改了孩子阶段");
					}
					userdao.update(u);
					util.Out().print(true);
					System.out.println("修改成功!");
					return;
				} catch (Exception e) {
					util.Out().print("异常" + e.getMessage());
					return;
				}

			} else {
				u = new User();
				// 验证用户名
				// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
				boolean b = true;
				// 判断生成群号 会不会和以前冲突
				while (b) {
					// 随机生成8位随机数 作为 群号
					accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
					if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
						b = false;
					}

				}

				if (username == null || username == "") {
					util.Out().print("ufalse");
					return;
				}

				if (password == null) {
					util.Out().print("pfalse");
					return;

				}

				System.out.println("用户名:" + username);
				System.out.println("密码:" + password);
				System.out.println("用户号:" + accnumno);

				u.setUsername(username);
				u.setNickname(username);// 没有设置过显示用户名

				if (nickname != null) {
					u.setNickname(nickname);
				}

				u.setCome(User.SYN);
				u.setImg("/SimulationApp/images/logod.png");
				u.setOs(User.SYN);
				u.setAccnumno(accnumno);
				u.setStage("未填写");
				if (OpeFunction.isEmpty(address) || address.length() < 2) {
					u.setAddress("湖南");
				} else {
					u.setAddress(address);

				}

				u.setAddcity(addcity);
				u.setFinaltime(time);
				u.setSchool("未填写");
				u.setTime(time);
				u.setCompetence(0);// 普通用户
				u.setGag(0);// 可以创建论坛
				if (userdao.byUsernameAccnumnoPhone(username) != null) {
					util.Out().print(false);
					return;

				}
				userdao.save(u);
				System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

				// 注册环信
				u = userdao.byUsernameAccnumnoPhone(accnumno);
				if (u != null) {
					if (file != null) {

						String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
						String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
						u.setImg(pah);
						userdao.update(u);
					} else {
						System.out.println("没有获取到头像!");
					}
					pd.setUid(u.getId());
					pd.setPassword(password);
					userdao.save(pd);
					JSONObject json = new JSONObject();
					json.put("username", u.getId());
					// 用户id
					json.put("password", "123456");
					// 用户密码
					String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
					System.out.println(w);
					u.setPassword(password);
					String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
					WechatKit.sendGet(url);
					util.Out().print(true);
					return;
				} else {
					util.Out().print("null");
					return;
				}

			}

		} catch (Exception e) {
			util.Out().print("异常" + e.getMessage());
			return;
		}
	}

	/**
	 * 新用户注册 username 不可以为null
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 */

	public void zHzhSave() throws IOException, JSONException {
		nickname = userName;
		username = userId;
		phone = userPhone;
		if (OpeFunction.isEmpty(username)) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}
		username = "zcom" + username;

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			return;
		}
		if (nickname == null) {
			nickname = username.substring(0, (username.length() > 20 ? 20 : username.length()));
		}
		nickname = nickname.substring(0, (nickname.length() > 20 ? 20 : nickname.length()));
		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);

			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(nickname);// 没有设置过显示用户名
			u.setCome(User.ZHZH);
			u.setOs(User.ZHZH);
			u.setImg("/SimulationApp/images/logod.png");
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}

			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	public void thirdPartyHqSave() throws IOException, JSONException {
		// nickname = userName;
		// username = userId;
		// phone = userPhone;
		if (OpeFunction.isEmpty(username)) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}
		os = User.HCOM;
		username = os + username;

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			return;
		}
		if (nickname == null) {
			nickname = username.substring(0, (username.length() > 20 ? 20 : username.length()));
		}
		nickname = nickname.substring(0, (nickname.length() > 20 ? 20 : nickname.length()));
		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);

			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(nickname);// 没有设置过显示用户名
			u.setCome(os);
			u.setImg("/SimulationApp/images/logod.png");
			u.setOs(os);
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}

			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	public void thirdPartyNlSave() throws IOException, JSONException {
		// nickname = userName;
		// username = userId;
		// phone = userPhone;
		if (OpeFunction.isEmpty(username)) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}
		os = User.NLCOM;
		username = os + username;

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			return;
		}
		if (nickname == null) {
			nickname = username.substring(0, (username.length() > 20 ? 20 : username.length()));
		}
		nickname = nickname.substring(0, (nickname.length() > 20 ? 20 : nickname.length()));
		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);

			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(nickname);// 没有设置过显示用户名
			u.setCome(os);
			u.setImg("/SimulationApp/images/logod.png");
			u.setOs(os);
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}

			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	public void thirdPartyKdSave() throws IOException, JSONException {
		// kdSave
		// nickname = userName;
		// username = userId;
		// phone = userPhone;
		if (OpeFunction.isEmpty(username)) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}

		os = User.KDCOM;

		username = os + username;

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			return;
		}
		if (nickname == null) {
			nickname = username.substring(0, (username.length() > 20 ? 20 : username.length()));
		}
		nickname = nickname.substring(0, (nickname.length() > 20 ? 20 : nickname.length()));
		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);

			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(nickname);// 没有设置过显示用户名

			u.setCome(os);
			u.setOs(os);
			u.setImg("/SimulationApp/images/logod.png");
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}

			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);

			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	public void thirdPartyYgSave() throws IOException, JSONException {
		// nickname = userName;
		// username = userId;
		// phone = userPhone;
		if (OpeFunction.isEmpty(username)) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}

		os = User.YGCOM;

		username = os + username;

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了" + u.getUsername());
			return;
		}
		if (nickname == null) {
			nickname = username.substring(0, (username.length() > 20 ? 20 : username.length()));
		}
		nickname = nickname.substring(0, (nickname.length() > 20 ? 20 : nickname.length()));
		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);

			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(nickname);// 没有设置过显示用户名

			u.setCome(os);
			u.setOs(os);
			u.setImg("/SimulationApp/images/logod.png");
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}

			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);

			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	public void xddSave() throws IOException, JSONException {
		// nickname=userName;
		// username=userId;
		// phone=userPhone;
		if (OpeFunction.isEmpty(username)) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}
		username = "xcom" + username;

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			return;
		}
		if (nickname == null) {
			nickname = username.substring(0, (username.length() > 20 ? 20 : username.length()));
		}
		nickname = nickname.substring(0, (nickname.length() > 20 ? 20 : nickname.length()));

		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);

			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(nickname);// 没有设置过显示用户名
			u.setCome(User.XDD);
			u.setOs(User.XDD);
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}u.setImg("/SimulationApp/images/logod.png");

			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	public void kuxSave() throws IOException, JSONException {
		// nickname=userName;
		// username=userId;
		// phone=userPhone;
		if (OpeFunction.isEmpty(username)) {
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			return;
		}
		username = "kcom" + username;

		u = userdao.byUsernameAccnumnoPhone(username);

		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			return;
		}
		if (nickname == null) {
			nickname = username.substring(0, (username.length() > 20 ? 20 : username.length()));
		}
		nickname = nickname.substring(0, (nickname.length() > 20 ? 20 : nickname.length()));

		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);

			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(nickname);// 没有设置过显示用户名
			u.setCome(User.KUX);
			u.setOs(User.KUX);
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}
			u.setImg("/SimulationApp/images/logod.png");
			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	/**
	 * 新用户注册 username password phone 不可以为null
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws NoSuchAlgorithmException
	 */
	public void bbtSave() throws IOException, JSONException, NoSuchAlgorithmException {

		Vcom_3DES vcom3DES = new Vcom_3DES();
		vcom3DES.setKeyStr("jiazhangzhiyouforvcomyyy");

		System.out.println("key==" + key + ":" + util.sha1("tryunk"));
		if (key == null) {
			util.Out().print("keynull");
			return;
		}
		if (!key.equals(util.sha1("tryunk"))) {
			util.Out().print("keyfalse");
			return;
		}
		if (username == null || username == "") {
			util.Out().print("ufalse");
			return;
		}
		if (phone != null || phone != "") {
			// 解密
			vcom3DES.setIsEncrypt(0);

			vcom3DES.setMessage(phone);
			phone = vcom3DES.Vcom3DESChiper();
		}
		if (nickname != null || nickname != "") {
			// 解密
			vcom3DES.setIsEncrypt(0);

			vcom3DES.setMessage(nickname);
			nickname = vcom3DES.Vcom3DESChiper();
		}

		// 解密
		vcom3DES.setIsEncrypt(0);

		vcom3DES.setMessage(username);
		username = vcom3DES.Vcom3DESChiper();

		username = "vcom" + username;

		u = userdao.byUsernameAccnumnoPhone(username);
		if (u != null) {
			System.out.println("ok");

			int ut = u.getLoginnum();
			if (ut > 0) {
				ut = ++ut;
				u.setLoginnum(ut);
			} else {
				u.setLoginnum(1);
			}
			u.setFinaltime(time);
			u.setIp(request.getRemoteAddr());
			userdao.update(u);
			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
			return;
		}
		synchronized (this) {

			u = new User();
			// 验证用户名
			// String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			boolean b = true;
			// 判断生成群号 会不会和以前冲突
			while (b) {
				// 随机生成8位随机数 作为 群号
				accnumno = String.valueOf((int) ((Math.random() * 9 + 1) * 10000000));
				if (userdao.byUsernameAccnumnoPhone(accnumno) == null) {
					b = false;
				}

			}

			System.out.println("用户名:" + username);
			System.out.println("密码:" + password);
			System.out.println("用户号:" + accnumno);

			u.setUsername(username);
			u.setNickname(username);// 没有设置过显示用户名

			if (nickname != null) {
				u.setNickname(nickname);
			}

			u.setCome(User.BBT);
			u.setOs(User.BBT);
			u.setAccnumno(accnumno);
			u.setStage("未填写");
			if (!util.isEmpty(address)) {
				u.setAddress(address);
			}
			if (!util.isEmpty(addcity)) {
				u.setAddcity(addcity);
			}
			u.setImg("/SimulationApp/images/logod.png");
			u.setFinaltime(time);
			u.setSchool("未填写");
			u.setLookphone(phone);
			u.setTime(time);
			u.setCompetence(0);// 普通用户
			u.setGag(0);// 可以创建论坛
			if (userdao.byUsernameAccnumnoPhone(username) != null) {
				util.Out().print(false);
				return;

			}
			userdao.save(u);
		}
		System.out.println("注册成功 phone" + phone + "accnumno:" + accnumno + ",pw:" + password);

		// 注册环信
		u = userdao.byUsernameAccnumnoPhone(accnumno);
		if (u != null) {
			if (file != null) {

				String path = "/IMG/Userimg/" + OpeFunction.getNameDayTime();
				String pah = util.ufileToServer(path, file, fileFileName, "jpg", true);
				u.setImg(pah);
				userdao.update(u);
			} else {
				System.out.println("没有获取到头像!");
			}
			pd.setUid(u.getId());
			pd.setPassword("123456");
			userdao.save(pd);
			JSONObject json = new JSONObject();
			json.put("username", u.getId());
			// 用户id
			json.put("password", "123456");
			// 用户密码
			String w = WechatKit.post(URL, json, RefreshAccessToken.access_token);
			System.out.println(w);

			session.setAttribute("u", u);
			String url = "http://127.0.0.1/Befriend/staLoginCount?id=" + u.getId();
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response()).sendRedirect(request.getContextPath() + "/webNewsA10");
			System.out.println("转发走了");
		}
	}

	/**
	 * 用户通过手机号修改密码
	 * 
	 * @throws IOException
	 */
	public void Modtionphone() throws IOException {
		/**
		 * String codes = (String) session.getAttribute("app_code"); if (codes
		 * == null) { System.out.println("session已过期");
		 * util.Out().print("cnull"); return; } if
		 * (!codes.equalsIgnoreCase(code)) { util.Out().print("cnull"); return;
		 * } session.invalidate();
		 */
		u = userdao.byUsernameAccnumnoPhone(phone);
		if (u == null) {
			System.out.println("账户不存在!");
			util.Out().print(false);
			return;
		}
		pd = userdao.select(u.getId());
		if (pd == null) {
			System.out.println("账户不存在!");
			util.Out().print(false);
			return;
		}
		if (password == null) {
			System.out.println("密码为空!");
			util.Out().print("null");
			return;
		}
		// 验证密码
		String reg = "[A-Za-z0-9_]{6,18}";
		if (password.matches(reg)) {
			pd.setPassword(password);
			userdao.update(pd);
			System.out.println("密码修改成功!");
			util.Out().print(true);
			return;
		} else {
			System.out.println("密码不合法!");
			util.Out().print("pfalse");
			return;
		}

	}

	/**
	 * 用户修改 信息
	 * 
	 * @throws IOException
	 */
	public void Modification() throws IOException {
		try {
			// 6-18 位 字母数子 下划线 [A-Za-z] 字母开头
			String reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
			String newpassword = "";
			System.out.println("进入Modification");
			nickname = request.getParameter("nickname");

			addcity = request.getParameter("addcity");

			stage = request.getParameter("stage");
			address = request.getParameter("address");
			password = request.getParameter("password");

			newpassword = request.getParameter("newpassword");
			username = request.getParameter("username");
			// 用于修改 密码的
			String p = request.getParameter("p");
			System.out.println("用户号或者用户名或者手机号是" + username);
			System.out.println("地址省级" + address);
			System.out.println("地址市级" + addcity);
			System.out.println("孩子阶段" + stage);
			System.out.println("手机是" + phone);
			System.out.println("学校是" + school);
			System.out.println("密码是" + password);
			System.out.println("用户名 要修改的!:" + accnumno);
			System.out.println("新密码是" + newpassword);
			System.out.println("昵称是" + nickname);
			System.out.println("p是空 就是  修改个人资料 不是空 就是 修改密码+" + p);
			/**
			 * if (accnumno != null) {
			 * 
			 * if (accnumno.matches(reg)) { util.Out().print("ok"); return; }
			 * else { util.Out().print("no"); return; }
			 * 
			 * }
			 */

			u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				System.out.println("没有该用户!");
				util.Out().print("null");
				return;
			}

			if (p != null) {
				u = userdao.byUsernameAccnumnoPhone(username);
				if (u == null) {
					System.out.println("账户为空");
					util.Out().print("null");
					return;
				}
				pd = userdao.login(u.getId(), password);
				if (pd == null) {
					System.out.println("密码不对!");
					util.Out().print(false);
					return;
				}

				if (newpassword != null && newpassword != " ") {
					// 验证密码
					reg = "[A-Za-z0-9_]{6,18}";
					if (newpassword.matches(reg)) {
						pd.setPassword(newpassword);
						userdao.update(pd);
						System.out.println("修改成功!新密码为:" + newpassword);
						util.Out().print(true);
						return;

					} else {
						System.out.println("密码不符合规范" + newpassword);
						util.Out().print("nfalse");
						return;
					}
				}
			}

			if (u.getCompetence() == 0) {
				if (addcity != null) {
					u.setAddcity(addcity);
				}
				if (address != null) {
					u.setAddress(address);
					System.out.println("修改了地址");
				}
			}

			if (u.getUsername() == null) {
				if (accnumno != null) {
					reg = "^[A-Za-z_][A-Za-z0-9]{5,17}";
					if (accnumno.matches(reg)) {
						if (userdao.byUsernameAccnumnoPhone(accnumno) != null) {
							System.out.println("1用户名被占用！");
							util.Out().print("uaccnumnofalse");
							return;
						}
						u.setUsername(accnumno);
						userdao.update(u);
						System.out.println("成功修改了用户名!");
					} else {
						System.out.println("用户名不符合规范");
						System.out.println(accnumno);
						util.Out().print("ufalse");
						return;
					}
				}

			} else {
				System.out.println("已经修改过用户名!");
			}

			if (nickname != null) {
				u.setNickname(nickname);
				System.out.println("修改了昵称!");
			}
			/**
			 * if (phone != null) { u.setPhone(phone); }
			 */

			if (school != null) {
				u.setSchool(school);
				System.out.println("修改了学校!");
			}

			if (stage != null) {
				u.setStage(stage);
				System.out.println("修改了孩子阶段");
			}
			System.out.println(util.ToJson(u));
			userdao.update(u);
			util.Out().print(util.ToJson(u));
			System.out.println("修改成功!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向这个手机号发送信息
	 * 
	 * @throws IOException
	 */
	public void phonetext() throws IOException {
		// phone="13555717521";
		// int num=123456789;
		if (phone != null) {
			System.out.println(phone);
			String content = new String("尊敬的用户：您的号码" + phone + "已验证成功。感谢您注册家长之友！");

			util.setphone(phone, content);
		} else {
			util.Out().print("phone,null");
		}

	}

	/**
	 * 按照时间查询 用户 和 用户数量
	 * 
	 * @throws IOException
	 */
	public String GetUsertime() throws IOException {
		// Map session = (Map) util.getsession();// 创建 map

		System.out.println(timeq);
		System.out.println(timeh);
		List<Stas> lios = adao.StasTime("all", User.IOS, timeq, timeh);
		Stas sta = new Stas();
		for (int i = 0; i < lios.size(); i++) {
			System.out.println("日期" + lios.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lios.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lios.get(i).getUsersaved());
			sta.setDownloaded(sta.getDownloaded() + lios.get(i).getDownloaded());
			if (lios.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lios.get(i).getUsersyned());
			}
			sta.setPv(sta.getPv() + lios.get(i).getPv());
			sta.setIp(sta.getIp() + lios.get(i).getIp());
			sta.setUv(sta.getUv() + lios.get(i).getUv());
			sta.setVored(sta.getVored() + lios.get(i).getVored());

			sta.setHome1(sta.getHome1() + lios.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lios.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lios.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lios.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lios.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lios.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lios.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lios.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lios.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lios.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lios.get(i).getHome334());
		}
		request.setAttribute("iossta", sta);
		System.out.println("ios统计天数" + lios.size());
		System.out.println("ios登入总和  " + sta.getUserlogined());
		System.out.println("ios注册总和  " + sta.getUsersaved());
		System.out.println("ios下载总和  " + sta.getDownloaded());
		System.out.println("ios最高在线总和  " + sta.getUsersyned());
		System.out.println("ios新增游客总和  " + sta.getVored());
		List<Stas> landroid = adao.StasTime("all", User.ANDROID, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < landroid.size(); i++) {
			System.out.println("日期" + landroid.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + landroid.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + landroid.get(i).getUsersaved());
			sta.setDownloaded(sta.getDownloaded() + landroid.get(i).getDownloaded());
			if (landroid.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(landroid.get(i).getUsersyned());
			}
			sta.setUv(sta.getUv() + landroid.get(i).getUv());
			sta.setPv(sta.getPv() + landroid.get(i).getPv());
			sta.setIp(sta.getIp() + landroid.get(i).getIp());
			sta.setVored(sta.getVored() + landroid.get(i).getVored());

			sta.setHome1(sta.getHome1() + landroid.get(i).getHome1());
			sta.setHome2(sta.getHome2() + landroid.get(i).getHome2());
			sta.setHome3(sta.getHome3() + landroid.get(i).getHome3());
			sta.setHome31(sta.getHome31() + landroid.get(i).getHome31());
			sta.setHome32(sta.getHome32() + landroid.get(i).getHome32());
			sta.setHome33(sta.getHome33() + landroid.get(i).getHome33());
			sta.setHome34(sta.getHome34() + landroid.get(i).getHome34());
			sta.setHome331(sta.getHome331() + landroid.get(i).getHome331());
			sta.setHome332(sta.getHome332() + landroid.get(i).getHome332());
			sta.setHome333(sta.getHome333() + landroid.get(i).getHome333());
			sta.setHome334(sta.getHome334() + landroid.get(i).getHome334());
		}
		request.setAttribute("androidsta", sta);
		System.out.println("android统计天数" + landroid.size());
		System.out.println("android登入总和  " + sta.getUserlogined());
		System.out.println("android注册总和  " + sta.getUsersaved());
		System.out.println("android下载总和  " + sta.getDownloaded());
		System.out.println("android最高在线总和  " + sta.getUsersyned());
		System.out.println("android新增游客总和  " + sta.getVored());
		List<Stas> lweb = adao.StasTime("all", User.WEB, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lweb.size(); i++) {
			System.out.println("日期" + lweb.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lweb.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lweb.get(i).getUsersaved());
			sta.setDownloaded(sta.getDownloaded() + lweb.get(i).getDownloaded());
			if (lweb.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lweb.get(i).getUsersyned());
			}
			sta.setVored(sta.getVored() + lweb.get(i).getVored());
			sta.setPv(sta.getPv() + lweb.get(i).getPv());
			sta.setIp(sta.getIp() + lweb.get(i).getIp());
			sta.setUv(sta.getUv() + lweb.get(i).getUv());

			sta.setHome1(sta.getHome1() + lweb.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lweb.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lweb.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lweb.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lweb.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lweb.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lweb.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lweb.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lweb.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lweb.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lweb.get(i).getHome334());
		}
		request.setAttribute("websta", sta);
		System.out.println("web统计天数" + lweb.size());
		System.out.println("web登入总和  " + sta.getUserlogined());
		System.out.println("web注册总和  " + sta.getUsersaved());
		System.out.println("web下载总和  " + sta.getDownloaded());
		System.out.println("web最高在线总和  " + sta.getUsersyned());
		System.out.println("web新增游客总和  " + sta.getVored());
		List<Stas> lsyn = adao.StasTime("all", User.SYN, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lsyn.size(); i++) {
			System.out.println("日期" + lsyn.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lsyn.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lsyn.get(i).getUsersaved());
			// sta.setDownloaded(sta.getDownloaded()+lsyn.get(i).getDownloaded());
			if (lsyn.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lsyn.get(i).getUsersyned());
			}
			sta.setPv(sta.getPv() + lsyn.get(i).getPv());
			sta.setIp(sta.getIp() + lsyn.get(i).getIp());
			// sta.setVored(sta.getVored()+lsyn.get(i).getVored());
			sta.setUv(sta.getUv() + lsyn.get(i).getUv());
			sta.setHome1(sta.getHome1() + lsyn.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lsyn.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lsyn.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lsyn.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lsyn.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lsyn.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lsyn.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lsyn.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lsyn.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lsyn.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lsyn.get(i).getHome334());
		}
		request.setAttribute("synsta", sta);
		System.out.println("优教通统计天数" + lsyn.size());
		System.out.println("优教通登入总和  " + sta.getUserlogined());
		System.out.println("优教通注册总和  " + sta.getUsersaved());
		System.out.println("优教通下载总和  " + sta.getDownloaded());
		System.out.println("优教通最高在线总和  " + sta.getUsersyned());
		System.out.println("优教通新增游客总和  " + sta.getVored());
		List<Stas> lbbt = adao.StasTime("all", User.BBT, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lbbt.size(); i++) {
			System.out.println("日期" + lbbt.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lbbt.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lbbt.get(i).getUsersaved());
			// sta.setDownloaded(sta.getDownloaded()+lbbt.get(i).getDownloaded());
			if (lbbt.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lbbt.get(i).getUsersyned());
			}
			sta.setPv(sta.getPv() + lbbt.get(i).getPv());
			sta.setIp(sta.getIp() + lbbt.get(i).getIp());
			// sta.setVored(sta.getVored()+lbbt.get(i).getVored());
			sta.setUv(sta.getUv() + lbbt.get(i).getUv());
			sta.setHome1(sta.getHome1() + lbbt.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lbbt.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lbbt.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lbbt.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lbbt.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lbbt.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lbbt.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lbbt.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lbbt.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lbbt.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lbbt.get(i).getHome334());
		}
		request.setAttribute("bbtsta", sta);
		System.out.println("斑斑通统计天数" + lbbt.size());
		System.out.println("斑斑通登入总和  " + sta.getUserlogined());
		System.out.println("斑斑通注册总和  " + sta.getUsersaved());
		System.out.println("斑斑通下载总和  " + sta.getDownloaded());
		System.out.println("斑斑通最高在线总和  " + sta.getUsersyned());
		System.out.println("斑斑通新增游客总和  " + sta.getVored());
		List<Stas> lxdd = adao.StasTime("all", User.XDD, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lxdd.size(); i++) {
			System.out.println("日期" + lxdd.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lxdd.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lxdd.get(i).getUsersaved());
			// sta.setDownloaded(sta.getDownloaded()+lbbt.get(i).getDownloaded());
			if (lxdd.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lxdd.get(i).getUsersyned());
			}
			sta.setPv(sta.getPv() + lxdd.get(i).getPv());
			sta.setIp(sta.getIp() + lxdd.get(i).getIp());
			// sta.setVored(sta.getVored()+lbbt.get(i).getVored());
			sta.setUv(sta.getUv() + lxdd.get(i).getUv());
			sta.setHome1(sta.getHome1() + lxdd.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lxdd.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lxdd.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lxdd.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lxdd.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lxdd.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lxdd.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lxdd.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lxdd.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lxdd.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lxdd.get(i).getHome334());
		}
		request.setAttribute("xddsta", sta);
		System.out.println("学多多统计天数" + lxdd.size());
		System.out.println("学多多登入总和  " + sta.getUserlogined());
		System.out.println("学多多注册总和  " + sta.getUsersaved());
		System.out.println("学多多下载总和  " + sta.getDownloaded());
		System.out.println("学多多最高在线总和  " + sta.getUsersyned());
		System.out.println("学多多新增游客总和  " + sta.getVored());
		List<Stas> lkux = adao.StasTime("all", User.KUX, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lkux.size(); i++) {
			System.out.println("日期" + lkux.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lkux.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lkux.get(i).getUsersaved());
			// sta.setDownloaded(sta.getDownloaded()+lbbt.get(i).getDownloaded());
			if (lkux.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lkux.get(i).getUsersyned());
			}
			sta.setPv(sta.getPv() + lkux.get(i).getPv());
			sta.setIp(sta.getIp() + lkux.get(i).getIp());
			// sta.setVored(sta.getVored()+lbbt.get(i).getVored());
			sta.setUv(sta.getUv() + lkux.get(i).getUv());
			sta.setHome1(sta.getHome1() + lkux.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lkux.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lkux.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lkux.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lkux.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lkux.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lkux.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lkux.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lkux.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lkux.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lkux.get(i).getHome334());
		}
		request.setAttribute("kuxsta", sta);
		System.out.println("酷校统计天数" + lkux.size());
		System.out.println("酷校登入总和  " + sta.getUserlogined());
		System.out.println("酷校注册总和  " + sta.getUsersaved());
		System.out.println("酷校下载总和  " + sta.getDownloaded());
		System.out.println("酷校最高在线总和  " + sta.getUsersyned());
		System.out.println("酷校新增游客总和  " + sta.getVored());
		List<Stas> lwechat = adao.StasTime("all", User.WECHAT, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lwechat.size(); i++) {
			System.out.println("日期" + lwechat.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lwechat.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lwechat.get(i).getUsersaved());

			if (lwechat.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lwechat.get(i).getUsersyned());
			}
			sta.setPv(sta.getPv() + lwechat.get(i).getPv());
			sta.setIp(sta.getIp() + lwechat.get(i).getIp());
			// sta.setVored(sta.getVored()+lbbt.get(i).getVored());
			sta.setUv(sta.getUv() + lwechat.get(i).getUv());
			sta.setHome1(sta.getHome1() + lwechat.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lwechat.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lwechat.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lwechat.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lwechat.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lwechat.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lwechat.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lwechat.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lwechat.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lwechat.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lwechat.get(i).getHome334());
		}
		request.setAttribute("wechatsta", sta);
		System.out.println("lwechat统计天数" + lwechat.size());
		System.out.println("lwechat登入总和  " + sta.getUserlogined());
		System.out.println("lwechat注册总和  " + sta.getUsersaved());
		System.out.println("lwechat下载总和  " + sta.getDownloaded());
		System.out.println("lwechat最高在线总和  " + sta.getUsersyned());
		System.out.println("lwechat新增游客总和  " + sta.getVored());
		List<Stas> lzhzh = adao.StasTime("all", User.ZHZH, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lzhzh.size(); i++) {
			System.out.println("日期" + lzhzh.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lzhzh.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lzhzh.get(i).getUsersaved());
			sta.setDownloaded(sta.getDownloaded() + lzhzh.get(i).getDownloaded());
			if (lzhzh.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lzhzh.get(i).getUsersyned());
			}
			sta.setUv(sta.getUv() + lzhzh.get(i).getUv());
			sta.setPv(sta.getPv() + lzhzh.get(i).getPv());
			sta.setIp(sta.getIp() + lzhzh.get(i).getIp());
			sta.setVored(sta.getVored() + lzhzh.get(i).getVored());

			sta.setHome1(sta.getHome1() + lzhzh.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lzhzh.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lzhzh.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lzhzh.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lzhzh.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lzhzh.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lzhzh.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lzhzh.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lzhzh.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lzhzh.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lzhzh.get(i).getHome334());
		}
		request.setAttribute("zhzhsta", sta);
		List<Stas> lh = adao.StasTime("all", User.HCOM, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lh.size(); i++) {
			System.out.println("日期" + lh.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lh.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lh.get(i).getUsersaved());
			sta.setDownloaded(sta.getDownloaded() + lh.get(i).getDownloaded());
			if (lh.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lh.get(i).getUsersyned());
			}
			sta.setUv(sta.getUv() + lh.get(i).getUv());
			sta.setPv(sta.getPv() + lh.get(i).getPv());
			sta.setIp(sta.getIp() + lh.get(i).getIp());
			sta.setVored(sta.getVored() + lh.get(i).getVored());

			sta.setHome1(sta.getHome1() + lh.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lh.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lh.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lh.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lh.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lh.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lh.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lh.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lh.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lh.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lh.get(i).getHome334());
		}
		request.setAttribute("hcomsta", sta);
		List<Stas> lkdcom = adao.StasTime("all", User.KDCOM, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lkdcom.size(); i++) {
			System.out.println("日期" + lkdcom.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lkdcom.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lkdcom.get(i).getUsersaved());
			sta.setDownloaded(sta.getDownloaded() + lkdcom.get(i).getDownloaded());
			if (lkdcom.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lkdcom.get(i).getUsersyned());
			}
			sta.setUv(sta.getUv() + lkdcom.get(i).getUv());
			sta.setPv(sta.getPv() + lkdcom.get(i).getPv());
			sta.setIp(sta.getIp() + lkdcom.get(i).getIp());
			sta.setVored(sta.getVored() + lkdcom.get(i).getVored());

			sta.setHome1(sta.getHome1() + lkdcom.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lkdcom.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lkdcom.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lkdcom.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lkdcom.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lkdcom.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lkdcom.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lkdcom.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lkdcom.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lkdcom.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lkdcom.get(i).getHome334());
		}
		request.setAttribute("kdcomsta", sta);
		List<Stas> lygcom = adao.StasTime("all", User.YGCOM, timeq, timeh);
		sta = new Stas();
		for (int i = 0; i < lygcom.size(); i++) {
			System.out.println("日期" + lygcom.get(i).getTime());
			sta.setUserlogined(sta.getUserlogined() + lygcom.get(i).getUserlogined());
			sta.setUsersaved(sta.getUsersaved() + lygcom.get(i).getUsersaved());
			sta.setDownloaded(sta.getDownloaded() + lygcom.get(i).getDownloaded());
			if (lygcom.get(i).getUsersyned() > sta.getUsersyned()) {
				sta.setUsersyned(lygcom.get(i).getUsersyned());
			}
			sta.setUv(sta.getUv() + lygcom.get(i).getUv());
			sta.setPv(sta.getPv() + lygcom.get(i).getPv());
			sta.setIp(sta.getIp() + lygcom.get(i).getIp());
			sta.setVored(sta.getVored() + lygcom.get(i).getVored());

			sta.setHome1(sta.getHome1() + lygcom.get(i).getHome1());
			sta.setHome2(sta.getHome2() + lygcom.get(i).getHome2());
			sta.setHome3(sta.getHome3() + lygcom.get(i).getHome3());
			sta.setHome31(sta.getHome31() + lygcom.get(i).getHome31());
			sta.setHome32(sta.getHome32() + lygcom.get(i).getHome32());
			sta.setHome33(sta.getHome33() + lygcom.get(i).getHome33());
			sta.setHome34(sta.getHome34() + lygcom.get(i).getHome34());
			sta.setHome331(sta.getHome331() + lygcom.get(i).getHome331());
			sta.setHome332(sta.getHome332() + lygcom.get(i).getHome332());
			sta.setHome333(sta.getHome333() + lygcom.get(i).getHome333());
			sta.setHome334(sta.getHome334() + lygcom.get(i).getHome334());
		}
		request.setAttribute("ygcomsta", sta);
		int own = userdao.getUsertimeCount(timeq, timeh, User.OWN);
		int bbt = userdao.getUsertimeCount(timeq, timeh, User.BBT);
		int syn = userdao.getUsertimeCount(timeq, timeh, User.SYN);
		int zhzh = userdao.getUsertimeCount(timeq, timeh, User.ZHZH);
		int xdd = userdao.getUsertimeCount(timeq, timeh, User.XDD);
		int kux = userdao.getUsertimeCount(timeq, timeh, User.KUX);
		int kd = userdao.getUsertimeCount(timeq, timeh, User.KDCOM);
		int yg = userdao.getUsertimeCount(timeq, timeh, User.YGCOM);
		int hc = userdao.getUsertimeCount(timeq, timeh, User.HCOM);
		// us = userdao.getUsertime(timeq, timeh);
		// for (int i = 0; i < us.size(); i++) {
		// System.out.println("yonghuming:" + us.get(i).getUsername() + ":"
		// + us.get(i).getCome() + ":" + us.get(i).getOs());
		// }
		System.out.println("own有" + own + "个用户");
		System.out.println("bbt有" + bbt + "个用户");
		System.out.println("syn有" + syn + "个用户");
		System.out.println("zhzh有" + zhzh + "个用户");
		System.out.println("xdd有" + xdd + "个用户");
		System.out.println("kux有" + kux + "个用户");
		System.out.println("kd有" + kd + "个用户");
		System.out.println("yg有" + yg + "个用户");
		System.out.println("hc有" + hc + "个用户");
		request.setAttribute("timeh", timeh);
		request.setAttribute("GetUsertimeus", us);
		request.setAttribute("timeq", timeq);
		request.setAttribute("own", own);
		request.setAttribute("bbt", bbt);
		request.setAttribute("syn", syn);
		request.setAttribute("zhzh", zhzh);
		request.setAttribute("xdd", xdd);
		request.setAttribute("kux", kux);

		request.setAttribute("kd", kd);
		request.setAttribute("yg", yg);
		request.setAttribute("hc", hc);
		return Action.SUCCESS;
	}

	/**
	 * 查询全部用户
	 * 
	 * @throws IOException
	 */
	public String GetUserAll() throws IOException {
		competence2 = userdao.getCount();// 全部用户数量
		int syn = userdao.getCount(User.SYN);// 同步的用户数量
		int bbt = userdao.getCount(User.BBT);// 同步的用户数量
		int zhzh = userdao.getCount(User.ZHZH);// 同步的用户数量
		int xdd = userdao.getCount(User.XDD);// 同步的用户数量
		int own = userdao.getCount(User.OWN);// 同步的用户数量
		int kux = userdao.getCount(User.KUX);// 同步的用户数量

		int kd = userdao.getCount(User.KDCOM);// 同步的用户数量
		int yg = userdao.getCount(User.YGCOM);// 同步的用户数量
		int hc = userdao.getCount(User.HCOM);// 同步的用户数量
		List<User> ul = userdao.getOnline();// 查询在线用户
		int all = competence2;
		System.out.println("用户数量" + competence2);
		System.out.println("有" + competence2 + "个用户");

		if (competence2 % pageSize == 0) {
			competence2 = competence2 / pageSize;// 获取 共有多少页
		} else {
			competence2 = competence2 / pageSize + 1;// 获取 共有多少页
		}
		if (currentPage >= competence2) {
			currentPage = competence2;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}

		us = userdao.getUserAll(pageSize, currentPage);

		request.setAttribute("GetUserAllus", us);
		request.setAttribute("competence2", competence2);
		request.setAttribute("all", all);
		request.setAttribute("ul", ul);
		request.setAttribute("syn", syn);
		request.setAttribute("bbt", bbt);
		request.setAttribute("zhzh", zhzh);
		request.setAttribute("xdd", xdd);
		request.setAttribute("own", own);
		request.setAttribute("kux", kux);

		request.setAttribute("kd", kd);
		request.setAttribute("yg", yg);
		request.setAttribute("hc", hc);

		return Action.SUCCESS;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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

	public String getAddcity() {
		return addcity;
	}

	public void setAddcity(String addcity) {
		this.addcity = addcity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCompetence2() {
		return competence2;
	}

	public void setCompetence2(int competence2) {
		this.competence2 = competence2;
	}

	public int getGag() {
		return gag;
	}

	public void setGag(int gag) {
		this.gag = gag;
	}

	public String getTimeq() {
		return timeq;
	}

	public void setTimeq(String timeq) {
		this.timeq = timeq;
	}

	public String getTimeh() {
		return timeh;
	}

	public void setTimeh(String timeh) {
		this.timeh = timeh;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAccnumno() {
		return accnumno;
	}

	public void setAccnumno(String accnumno) {
		this.accnumno = accnumno;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserAction(ApputilDAO adao, UserDAO userdao, GroupDAO gdao, RegistrationDAO regdao) {
		super();
		this.adao = adao;
		this.userdao = userdao;
		this.gdao = gdao;
		this.regdao = regdao;
	}

}
