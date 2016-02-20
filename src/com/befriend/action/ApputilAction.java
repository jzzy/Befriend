package com.befriend.action;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.befriend.dao.AppDAO;
import com.befriend.dao.ApputilDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.Adv;
import com.befriend.entity.App;
import com.befriend.entity.AppUp;
import com.befriend.entity.Areas;
import com.befriend.entity.Cities;
import com.befriend.entity.City;
import com.befriend.entity.Feedback;
import com.befriend.entity.House;
import com.befriend.entity.ParentsLetter;
import com.befriend.entity.Provinces;
import com.befriend.entity.SetIp;
import com.befriend.entity.Stas;
import com.befriend.entity.User;
import com.befriend.entity.Visitor;
import com.befriend.util.Message;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("all")
public class ApputilAction {
	public OpeFunction util;
	private ApputilDAO audao;
	private UserDAO userdao;
	private AppDAO adao;
	private String username;
	private String information;
	private File imgFile;
	private File xlsxFile;

	private String xlsxFileFileName;
	private String imgFileFileName;
	private String imgFileContentType;

	private File imgFile1;
	private String imgFile1FileName;
	private String imgFile1ContentType;

	private File imgFile2;
	private String imgFile2FileName;
	private String imgFile2ContentType;

	private File imgFile3;
	private String imgFile3FileName;
	private String imgFile3ContentType;

	private String savePath;
	private String Keywords;

	private File appFile;
	private String appFileFileName;
	private String appFileContentType;

	private String vnum;
	private String type;
	private String dpt;
	private String summary;

	private String name;
	private int num = 0;

	private int currentPage = 1;
	private int id;
	private int pageSize = 4;

	private int downloads;

	private int apptv;
	private String updates;

	AppUp au = new AppUp();
	App app = new App();
	Visitor vor = new Visitor();
	public HttpServletRequest request = ServletActionContext.getRequest();
	private String Mac;
	private String os;
	private String time = util.getNowTime();
	private String province = "all";
	private int synlogin;
	private int downloaded;
	private int usersyned;
	private int vored;
	private int usersaved;
	private String schoolname;
	private String address;
	private String scope;
	private String property;
	private String content;
	private String img;
	private String title;
	HttpSession session = ServletActionContext.getRequest().getSession();
	// private final static String IP="http://123.56.45.164/";//测试服
	private final static String IP = "http://182.92.100.235/";// 正式服

	// private final static String IP="http://192.168.1.240/";//本地
	public void obtainCity() throws IOException {
		// List<City> cl=audao.cityFindAll();
		// String so="";
		// for (int i=0;i<cl.size();i++) {
		//
		// if(i+1<cl.size()&&!so.equals(cl.get(i).getSortmodel())){
		// System.out.println("<dt id=key"+cl.get(i).getSortmodel()+">"+cl.get(i).getSortmodel()+"</dt>");
		//
		// }
		//
		// System.out.println("<dd><a href=getEduWeb?city="+cl.get(i).getName().substring(0,
		// (cl.get(i).getName().length()-1))+">"+cl.get(i).getName()+"</a></dd>");
		// so=cl.get(i).getSortmodel();
		// }
		// request.setAttribute("cl", cl);
		String name = title;
		List<Provinces> lpro = audao.ProvincesName(name);

		for (int i = 0; i < lpro.size(); i++) {
			System.out.println(lpro.get(i).getProvince() + "区域代码:"
					+ lpro.get(i).getProvinceid());
			List<Cities> lcit = audao.CitiesName(lpro.get(i).getProvinceid());
			if (lcit.size() > 0 && lcit.size() <= 2) {
				List<Areas> lar = audao.AreasName(lcit.get(0).getCityid());
				for (int i2 = 0; i2 < lar.size(); i2++) {
					System.out.println(lpro.get(i).getProvince() + "下的区:"
							+ lar.get(i2).getArea());
				}
			} else {
				for (int i2 = 0; i2 < lcit.size(); i2++) {
					System.out.println(lpro.get(i).getProvince() + "下的市:"
							+ lcit.get(i2).getCity());
				}
			}
		}
	}

	public void RemoveParentsLetterone() throws IOException {
		ParentsLetter p = audao.Plbyid(id);
		if (p != null) {
			audao.Remove(p);
		}
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/selectParentsLetter");

	}

	public String selectParentsLetterone() throws IOException {
		ParentsLetter p = audao.Plbyid(id);
		if (p != null) {
			p.setHits(p.getHits() + 1);
			audao.Update(p);
		}
		request.setAttribute("p", p);
		return Action.SUCCESS;

	}

	public String selectParentsLetter() throws IOException {
		List<ParentsLetter> pl = audao.PlAll();
		request.setAttribute("pl", pl);
		return Action.SUCCESS;

	}

	public String SaveParentsLetter() throws IOException {
		System.out.println("SaveParentsLetter");
		ParentsLetter p = new ParentsLetter();
		p.setContent(content);
		String path = "/IMG/ParentsLetter/" + OpeFunction.getNameDayTime();
		if (imgFile != null) {
			img = util.ufileToServer(path, imgFile, "", "jpg", true);
			p.setImg(img);
		}

		p.setTitle(title);
		p.setTime(time);
		audao.Save(p);
		return Action.SUCCESS;
	}

	public void appSavePL() throws IOException {
		ParentsLetter p = new ParentsLetter();
		Message mge = new Message();
		if (util.isEmpty(content) || util.isEmpty(title)) {
			mge.setCode(mge.NULL);
			mge.setStatement("content||title is null");
			mge.setContent("false");
			util.Out().print(util.ToJson(mge));
			return;
		}
		p.setContent(content);
		String path = "/IMG/ParentsLetter/" + OpeFunction.getNameDayTime();
		System.out.println("imgFile-" + imgFile);
		if (imgFile != null) {
			img = util.fileToServer(path, imgFile, imgFileFileName,
					imgFileContentType, true);
			p.setImg(img);
		} else {
			System.out.println("imgFile is null");
			mge.setStatement("imgFile is null");
		}

		p.setTitle(title);
		p.setTime(time);
		audao.Save(p);
		mge.setCode(mge.SUCCESS);
		mge.setContent("true");
		util.Out().print(util.ToJson(mge));

	}

	public void SaveParentsL() throws IOException {
		ParentsLetter p = new ParentsLetter();
		p.setContent(content);
		String path = "/IMG/ParentsLetter/" + OpeFunction.getNameDayTime();
		if (imgFile != null) {

			// BufferedImage sourceImg = ImageIO
			// .read(new FileInputStream(imgFile));
			// if (sourceImg.getWidth() < 720 || sourceImg.getHeight() < 360) {
			// util.Out().print("");
			// return;
			// }
			// float fimg = util.fileSize(imgFile);
			// if (fimg > 2048.00) {
			// util.Out().print("");
			// return;
			// }

			img = util.fileToServer(path, imgFile, imgFileFileName,
					imgFileContentType, true);
			p.setImg(img);
		}

		p.setTitle(title);
		p.setTime(time);
		audao.Save(p);
		// util.outjS("/Befriend/webNewsA10","Thank you!");
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/webNewsA10");
		return;
	}

	public void Removejzfkid() throws IOException {

		Feedback f = audao.FeedbackByid(id);
		if (f != null) {
			audao.Remove(f);
		}
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/selectjzfk");

	}

	public String selectjzfkid() throws IOException {
		Feedback f = audao.FeedbackByid(id);
		request.setAttribute("f", f);
		return Action.SUCCESS;

	}

	public String selectjzfk() throws IOException {
		List<Feedback> fl = audao.FeedbackAll();
		request.setAttribute("fl", fl);
		return Action.SUCCESS;

	}

	public String selectwxHouseallzh() throws IOException {
		System.out.println("selectwxHouseallzhh");

		List<House> hl = audao.HouseZh(6);

		for (int i = 0; i < hl.size(); i++) {
			House h = hl.get(i);
			h.setCount((h.getCount() + 1));
			audao.Update(h);

		}
		System.out.println("閿熸枻鎷�:" + hl.size());
		request.setAttribute("hl", hl);

		return Action.SUCCESS;

	}

	public void appSelectHouseallZh() throws IOException {
		System.out.println("appSelectHouseallZh" + num);
		if (num <= 0) {
			num = 6;
		}
		List<House> hl = audao.HouseZh(num);

		for (int i = 0; i < hl.size(); i++) {
			House h = hl.get(i);
			h.setCount((h.getCount() + 1));
			audao.Update(h);

		}
		util.Out().print(util.ToJson(hl));

	}

	public String selectwxHouseall() throws IOException {

		if (util.isEmpty(schoolname)) {
			System.out.println("schoolname閿熻绌虹鎷�");
		}
		List<House> hl = audao.bylikeshoolname(schoolname);

		for (int i = 0; i < hl.size(); i++) {
			House h = hl.get(i);
			h.setCount((h.getCount() + 1));
			h.setHits(h.getHits() + 1);
			audao.Update(h);

		}
		request.setAttribute("hl", hl);
		request.setAttribute("name", schoolname);
		return Action.SUCCESS;

	}

	public void appselectHouseall() throws IOException {
		System.out.println("schoolname閿熸枻鎷�:" + schoolname);
		Message mge = new Message();
		if (util.isEmpty(schoolname)) {
			mge.setCode(mge.NULL);
			mge.setStatement("schoolname Is Null");
			util.Out().print(util.ToJson(mge));
			return;
		}
		List<House> hl = audao.bylikeshoolname(schoolname);

		for (int i = 0; i < hl.size(); i++) {
			House h = hl.get(i);
			h.setCount((h.getCount() + 1));
			h.setHits(h.getHits() + 1);
			audao.Update(h);

		}
		mge.setCode(mge.SUCCESS);
		mge.setContent(hl);
		mge.setStatement("Good");
		util.Out().print(util.ToJson(mge));

	}

	public String selectHouseall() throws IOException {
		System.out.println("schoolname閿熸枻鎷�:" + schoolname);
		if (util.isEmpty(schoolname)) {
			System.out.println("schoolname閿熺Ц纰夋嫹");
			return Action.SUCCESS;
		}
		List<House> hl = audao.bylikeshoolname(schoolname);

		for (int i = 0; i < hl.size(); i++) {
			House h = hl.get(i);
			h.setCount((h.getCount() + 1));
			audao.Update(h);

		}
		request.setAttribute("hl", hl);
		return Action.SUCCESS;

	}

	public String upHouse() throws IOException {
		House house = audao.byid(id);

		if (house != null) {
			house.setAddress(address);
			house.setProperty(property);
			house.setSchoolname(schoolname);
			house.setScope(scope);
			house.setTime(time);
			house.setKeywords(Keywords);
			audao.Update(house);
			request.setAttribute("h", house);
			request.setAttribute("hh", "ok!");
			return Action.SUCCESS;
		}

		return null;
	}

	public String selectHousenoe() throws IOException {
		House house = audao.byid(id);

		if (house != null) {
			request.setAttribute("h", house);
			return Action.SUCCESS;
		}
		return null;
	}

	public void RemovetHouse() throws IOException {
		House house = audao.byid(id);

		if (house != null) {
			audao.Remove(house);

		}
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/selectHouse?currentPage=" + currentPage);

	}

	public String selectHouse() {
		pageSize = 20;
		if (currentPage <= 0) {
			currentPage = 1;
		}

		int n = audao.HouseAll();
		if (n % 20 == 0) {
			n = n / 20;
		} else {
			n = n / 20 + 1;
		}
		if (currentPage > n) {
			currentPage = n;
		}
		List<House> hl = audao.HouseAll(currentPage, pageSize);
		request.setAttribute("n", n);
		request.setAttribute("hl", hl);
		request.setAttribute("currentPage", currentPage);
		return Action.SUCCESS;

	}

	public String Addschooldistrict() throws IOException,
			InvalidFormatException {

		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/SuperAdmin/SuperAdmin.jsp");
			return null;
		}

		if (xlsxFile != null) {

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(xlsxFile);

			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}

				for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow == null) {
						continue;
					}

					if (rowNum < 1) {

					}

					if (xssfRow.getCell(1) != null) {
						schoolname = xssfRow.getCell(1).toString();
					}
					System.out.println("1:" + xssfRow.getCell(1) + "2:"
							+ xssfRow.getCell(2) + "3:" + xssfRow.getCell(3)
							+ "4:" + xssfRow.getCell(4) + "5:"
							+ xssfRow.getCell(5));
					if (schoolname == null) {
						continue;
					}
					House h = audao.byshoolname(schoolname);

					// 更新
					if (h != null) {
						if (xssfRow.getCell(2) != null) {
							h.setAddress(xssfRow.getCell(2).toString());
						}
						if (xssfRow.getCell(4) != null) {
							h.setProperty(xssfRow.getCell(4).toString());
						}
						if (xssfRow.getCell(3) != null) {
							h.setScope(xssfRow.getCell(3).toString());
						}
						if (xssfRow.getCell(5) != null) {
							h.setKeywords(xssfRow.getCell(5).toString());
						}
						audao.Update(h);

						continue;
					}

					h = new House();
					h.setSchoolname(schoolname);
					if (xssfRow.getCell(2) != null) {
						h.setAddress(xssfRow.getCell(2).toString());
					}
					if (xssfRow.getCell(4) != null) {
						h.setProperty(xssfRow.getCell(4).toString());
					}
					if (xssfRow.getCell(3) != null) {
						h.setScope(xssfRow.getCell(3).toString());
					}
					if (xssfRow.getCell(5) != null) {
						h.setKeywords(xssfRow.getCell(5).toString());
					}

					h.setTime(time);
					h.setAdmin(admin.getAdmin());
					audao.Save(h);

				}
			}

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/selectHouse");
			return null;
		}
		House h = new House();
		h.setAddress(address);
		h.setProperty(property);
		h.setScope(scope);
		h.setSchoolname(schoolname);
		h.setTime(time);
		h.setKeywords(Keywords);
		h.setAdmin(admin.getAdmin());
		audao.Save(h);

		return Action.SUCCESS;

	}

	public String getXlsxFileFileName() {
		return xlsxFileFileName;
	}

	public void setXlsxFileFileName(String xlsxFileFileName) {
		this.xlsxFileFileName = xlsxFileFileName;
	}

	public File getXlsxFile() {
		return xlsxFile;
	}

	public void setXlsxFile(File xlsxFile) {
		this.xlsxFile = xlsxFile;
	}

	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void staLoginCount() {
		time = util.getNumTime(0);
		System.out.println("进入staLoginCount id=" + id);
		User u = (User) session.getAttribute("u");
		if (u == null) {
			u = userdao.byid(id);
		}

		if (u != null) {
			Stas sta = audao.StasTimeDay(time, u.getOs(), User.ALL);
			if (sta == null) {
				sta = new Stas();
				sta.setTime(time);
				sta.setProvince(User.ALL);
				sta.setOs(u.getOs());

				sta.setUserlogined(sta.getUserlogined() + 1);
				audao.Save(sta);
				return;
			}
			sta.setUserlogined(sta.getUserlogined() + 1);
			audao.Update(sta);
		}
	}

	public void statisticsPVIP() {

		time = util.getNumTime(0);
		User u = (User) session.getAttribute("u");
		String ip = request.getRemoteAddr();

		if (u == null) {
			u = userdao.byid(userid);
			if (u == null) {
				u = new User();
				u.setOs(User.WECHAT);
			}
		}
		System.out.println("进入statisticsPVIP" + util.getNowTime() + "用户来自:"
				+ u.getOs() + " id来自:" + id + " ip是:" + ip);

		SetIp sp = audao.byTimeIp(time, ip, u.getOs());
		if (sp == null) {
			sp = new SetIp();
			sp.setIp(ip);
			sp.setOs(u.getOs());
			sp.setTime(time);
			audao.Save(sp);
		}
		Stas sta = audao.StasTimeDay(time, u.getOs(), User.ALL);
		if (sta == null) {
			sta = new Stas();
			sta.setTime(time);
			sta.setProvince(User.ALL);
			sta.setOs(u.getOs());

			sta.setPv(sta.getPv() + 1);
			sta.setIp(audao.byTimeConut(time, u.getOs()));

			audao.Save(sta);
			return;
		}
		sta.setPv(sta.getPv() + 1);
		sta.setIp(audao.byTimeConut(time, u.getOs()));

		switch (id) {
		case 1:
			sta.setHome1(sta.getHome1() + 1);
			break;
		case 2:
			sta.setHome2(sta.getHome2() + 1);
			break;
		case 3:
			sta.setHome3(sta.getHome3() + 1);
			break;
		case 31:
			sta.setHome31(sta.getHome31() + 1);
			break;
		case 32:
			sta.setHome32(sta.getHome32() + 1);
			break;
		case 33:
			sta.setHome33(sta.getHome33() + 1);
			break;
		case 34:
			sta.setHome34(sta.getHome34() + 1);
			break;
		case 331:
			sta.setHome331(sta.getHome331() + 1);
			break;
		case 332:
			sta.setHome332(sta.getHome332() + 1);
			break;
		case 333:
			sta.setHome333(sta.getHome333() + 1);
			break;
		case 334:
			sta.setHome334(sta.getHome334() + 1);
			break;

		default:
			System.out.println("没有id无法统计!");
			break;
		}
		audao.Update(sta);

	}

	// for (int i = 0; i < l.size(); i++) {
	// if (province.equals("all") || province == null
	// || province.equals("null")) {
	// continue;
	// }
	//
	// Stas sta = audao.StasTimeDay(time, l.get(i), province);
	// usersaved = userdao.getSaveTime(time, l.get(i), province).size();
	// System.out.println("注册用户数量" + usersaved + l.get(i));
	//
	// vored = audao.VisitorTime(time, l.get(i), province).size();
	//
	// synlogin = userdao.getFinaltime(time, l.get(i), province).size();
	// if (synlogin != 0) {
	// if (synlogin % 3 == 0) {
	// usersyned = synlogin / 3;
	// } else {
	// usersyned = synlogin / 3 + 1;
	// }
	// } else {
	// usersyned = 0;
	// }
	// // 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹
	// // usersyned = userdao.getOnline(sys,province).size();
	// if (sta == null) {
	// sta = new Stas();
	// sta.setProvince(province);// 閿熸枻鎷烽敓鏂ゆ嫹
	// sta.setTime(time);// 缁熼敓鏂ゆ嫹鏃堕敓鏂ゆ嫹
	// sta.setUserlogined(synlogin);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
	// if (downloads != 0) {
	// sta.setDownloaded(1);
	// } else {
	// sta.setDownloaded(0);
	// }// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	// sta.setOs(l.get(i));// 閿熸枻鎷烽敓鐨嗙鎷风郴缁�
	// sta.setUsersyned(usersyned);// 鍚屾椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	// sta.setUsersaved(usersaved);// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	// sta.setVored(vored);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹閿熸枻鎷�
	// sta.setUv(synlogin);
	// sta.setIp(audao.byTimeConut(time, l.get(i)));
	// audao.Save(sta);
	// continue;
	// }
	// sta = audao.StasTimeDay(time, l.get(i), province);
	//
	// if (sta == null) {
	// continue;
	// }
	// // sta.setProvince(province);//缁熼敓鏂ゆ嫹鍏ㄩ敓鏂ゆ嫹
	// // sta.setTime(time);// 缁熼敓鏂ゆ嫹鏃堕敓鏂ゆ嫹
	//
	// sta.setUserlogined(synlogin);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
	// if (downloaded != 0 && l.get(i).equals(os)) {
	// sta.setDownloaded(sta.getDownloaded() + 1);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	// }
	// // sta.setOs(os);//閿熸枻鎷烽敓鐨嗙鎷风郴缁�
	// if (usersyned > sta.getUsersyned()) {
	// sta.setUsersyned(usersyned);// 鍚屾椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	// }
	// sta.setUsersaved(usersaved);// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	//
	// sta.setVored(vored);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹閿熸枻鎷�
	// sta.setUv(synlogin);
	// audao.Update(sta);
	//
	// }

	public void aStas() {

		time = util.getNumTime(0);
		List<String> l = new ArrayList<String>();
		l.add(User.WEB);
		l.add(User.IOS);
		l.add(User.ANDROID);
		l.add(User.SYN);
		l.add(User.BBT);
		l.add(User.WECHAT);

		l.add(User.ZHZH);
		l.add(User.XDD);

		l.add(User.KUX);

		l.add(User.HCOM);
		l.add(User.KDCOM);
		l.add(User.YGCOM);
		l.add(User.NLCOM);
		province = User.ALL;
		for (int i = 0; i < l.size(); i++) {
			System.out.println("look aStas:" + l.get(i));

			Stas sta = audao.StasTimeDay(time, l.get(i), province);
			usersaved = userdao.getSaveTime(time, l.get(i)).size();
			vored = audao.VisitorTime(time, l.get(i)).size();
			synlogin = userdao.getFinaltime(time, l.get(i)).size();
			if (synlogin != 0) {
				if (synlogin % 3 == 0) {
					usersyned = synlogin / 4;
				} else {
					usersyned = synlogin / 5;
				}
				if (usersyned == 0) {
					usersyned = 1;
				}
			} else {

				usersyned = 0;
			}
			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);
				sta.setTime(time);
				if (downloaded != 0 && l.get(i).equals(os)) {
					sta.setDownloaded(1);
				} else {
					sta.setDownloaded(0);

				}
				sta.setOs(l.get(i));
				if (usersyned > sta.getUsersyned()) {
					sta.setUsersyned(usersyned);
				}
				sta.setUsersaved(usersaved);
				sta.setVored(vored);
				sta.setUv(synlogin);
				sta.setIp(audao.byTimeConut(time, l.get(i)));
				audao.Save(sta);
				continue;
			}
			if (downloaded != 0 && l.get(i).equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);
			}
			if (usersyned > sta.getUsersyned()) {
				sta.setUsersyned(usersyned);
			}
			sta.setUsersaved(usersaved);
			sta.setVored(vored);
			sta.setUv(synlogin);
			sta.setIp(audao.byTimeConut(time, l.get(i)));
			audao.Update(sta);

		}

	}

	public void visitorMac() throws IOException {
		if (Mac == null) {
			util.Out().print(false);
			return;
		}
		vor = audao.sVisitor(Mac);
		if (os == null) {
			util.Out().print("null");
			return;
		}
		if (os.equals(User.ANDROID)) {
			os = User.ANDROID;
		} else if (os.equals(User.IOS)) {
			os = User.IOS;
		} else {

			os = null;
			util.Out().print("null");
			return;
		}

		if (vor == null) {

			vor = new Visitor();
			vor.setAppmac(Mac);
			vor.setNickname(Mac);
			vor.setOs(os);
			vor.setProvince(province);
			vor.setTime(util.getNowTime());
			audao.Save(vor);
			String url = "http://127.0.0.1/Befriend/aStas?os=" + os
					+ "&province=" + province;
			WechatKit.sendGet(url);
			util.Out().print(true);
			return;
		}
		util.Out().print(false);
	}

	public void JztdAppm() throws IOException {

		au = audao.select();
		if (au != null && apptv > 0 && !OpeFunction.isEmpty(updates)) {
			System.out.println(apptv);
			System.out.println(updates);
			au.setApptv(apptv);
			au.setTime(OpeFunction.getNowTime());
			au.setUpdates(updates);

			if (au.getUpnum() <= 0) {
				au.setUpnum(0);
			}

			audao.Update(au);
			OpeFunction.Out().print("Is Ok");
		} else {
			OpeFunction.Out().print("Is False");
		}
	}

	public void JztdApp() throws IOException {
		try {

			if (appFile == null) {
				OpeFunction.Out().print("FamilyGroup.apk==null");
				return;
			}
			if (!appFileFileName.equals("FamilyGroup.apk")) {
				OpeFunction.Out().print("!FamilyGroup.apk");
				return;
			}
			System.out.println(appFileFileName);
			au = audao.select();
			Boolean b = false;

			if (au != null) {
				File file1 = new File(ServletActionContext.getServletContext()
						.getRealPath("/AppUp/FamilyGroup.apk"));
				b = file1.delete();
				// audao.Remove(au);
			}

			savePath = "AppUp";
			AppUp ap = new AppUp();

			String upth = IP
					+ "Befriend/"
					+ OpeFunction.fileToServer(savePath, appFile,
							appFileFileName, appFileContentType, false);

			savePath = "AppUp/Past/" + OpeFunction.getNameDayTime();
			OpeFunction.fileToServer(savePath, appFile, appFileFileName,
					appFileContentType, true);
			ap.setApptv(downloads);
			ap.setTime(OpeFunction.getNowTime());
			ap.setUpdates(type);
			ap.setUpnum(0);
			ap.setPath(upth);
			audao.Save(ap);

			OpeFunction.Out().print("UP OK" + b);
		} catch (Exception e) {
			OpeFunction.Out().print(e.getMessage());
		}

	}

	public void weixinJzFoIosAndAndroid() throws IOException {
		boolean b = false;
		try {

			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);

			List<String> al = new ArrayList<String>();

			al.add("Mac OS X");

			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1) {
					b = true;
					System.out.println("OS:" + sUA.indexOf(al.get(i)));
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (b == true) {
			System.out.println("Mac OS X");
			String url = "http://127.0.0.1/Befriend/aStas?os=" + "ios"
					+ "&downloaded=1";
			WechatKit.sendGet(url);

			((HttpServletResponse) util.response())
					.sendRedirect("https://itunes.apple.com/cn/app/jia-zhang-zhi-you/id995642623?mt=8");

		} else {

			String url = "http://127.0.0.1/Befriend/aStas?os=" + "android"
					+ "&downloaded=1";
			WechatKit.sendGet(url);
			((HttpServletResponse) util.response())
					.sendRedirect("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=11679248&from=singlemessage&isTimeline=false&actionFlag=0&params=pname%3Dcn.com.parentsfriend%26versioncode%3D39%26channelid%3D%26actionflag%3D0&isappinstalled=1");

		}

	}

	public void JzFoIosAndAndroid() throws IOException {
		boolean b = false;
		try {

			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);

			List<String> al = new ArrayList<String>();

			al.add("Mac OS X");

			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1) {
					b = true;
					System.out.println("OS:" + sUA.indexOf(al.get(i)));
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (b == true) {

			String url = "http://127.0.0.1/Befriend/aStas?os=" + "ios"
					+ "&downloaded=1";
			WechatKit.sendGet(url);
			System.out.println("Mac OS X");

			((HttpServletResponse) util.response())
					.sendRedirect("https://itunes.apple.com/cn/app/jia-zhang-zhi-you/id995642623?mt=8");

		} else {

			String url = "http://127.0.0.1/Befriend/aStas?os=" + "android"
					+ "&downloaded=1";
			WechatKit.sendGet(url);

			((HttpServletResponse) util.response()).sendRedirect("http://"
					+ OpeFunction.getLIP() + "/Befriend/AppUp/FamilyGroup.apk");

		}

	}

	public void Jwebandwap() throws IOException {
		try {

			System.out.println("Jwebandwap");
			HttpServletRequest request = OpeFunction.request();
			HttpServletResponse response = OpeFunction.response();
			String sUA = request.getHeader("user-agent");
			System.out.println(sUA);

			List<String> al = new ArrayList<String>();
			al.add("Windows 98");
			al.add("Windows ME");
			al.add("Windows 2000");
			al.add("Windows XP");
			al.add("Windows NT");
			al.add("Ubuntu");
			al.add("Mac OS X");

			boolean b = false;
			for (int i = 0; i < al.size(); i++) {
				if (sUA.indexOf(al.get(i)) != -1
						&& sUA.indexOf(al.get(i)) == 13) {
					b = true;
					System.out.println("OS:" + sUA.indexOf(al.get(i)));
					break;
				}
			}

			if (b == true) {
				System.out.println("PC");

				request.getRequestDispatcher("/webjztd.html").forward(request,
						response);
			} else {
				System.out.println("phone");

				request.getRequestDispatcher("/iwebjztd.html").forward(request,
						response);
			}
		} catch (Exception e) {

		}

	}

	public String Appset() {
		pageSize = 4;

		System.out.println("currentPage:" + currentPage);
		if (currentPage < 1) {
			currentPage = 1;
		}
		int n = adao.ALL().size();
		if (n % 4 == 0) {
			n = n / 4;
		} else {
			n = n / 4 + 1;
		}
		if (currentPage >= n) {
			currentPage = n;
		}
		List<App> l = adao.FAll(currentPage, pageSize);
		Map session = (Map) ActionContext.getContext().get("session");
		session.put("l", l);
		session.put("n", n);
		session.put("currentPage", currentPage);
		return Action.SUCCESS;
	}

	public void Deleteapp() throws IOException {
		try {

			System.out.println("id:" + id);
			app = adao.byid(id);
			if (app == null) {
				util.Out().print("App Is Null");
				return;
			}
			System.out.println("app" + app);
			if (app == null) {
				OpeFunction.Out().print("null");
				return;
			}
			System.out.println(app.getPathapk());

			File file = new File(ServletActionContext.getServletContext()
					.getRealPath(app.getPathapk()));
			file.delete();

			System.out.println(app.getPathimg());
			File fil = new File(ServletActionContext.getServletContext()
					.getRealPath(app.getPathimg()));
			fil.delete();
			adao.remove(app);
			OpeFunction.Out().print(true);
			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/Appset");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void AppDs() {

		app = adao.byid(id);
		int iz = app.getRealds() + 1;
		int i = app.getDownloads() + 1;
		app.setRealds(iz);
		app.setDownloads(i);
		adao.Update(app);

	}

	public void Appup() throws IOException {
		try {
			au = audao.UP();
			OpeFunction.Out().print(OpeFunction.ToJson(au));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Appupnum() throws IOException {
		try {
			au = audao.UP();
			num = au.getUpnum();
			if (num > 0) {
				num = ++num;
				au.setUpnum(num);
			} else {
				au.setUpnum(1);
			}
			audao.Update(au);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void SaveFeedback() {
		try {

			System.out.println("SaveFeedback" + "," + username + ","
					+ information);
			User u = userdao.byUsernameAccnumnoPhone(username);
			if (u == null) {
				OpeFunction.Out().print(false);
				System.out.println("username Is Null");
				return;
			}
			Feedback f = new Feedback();
			f.setUsername(username);
			f.setInformation(information);
			f.setTime(OpeFunction.getNowTime());
			audao.Save(f);
			String Email = "";
			String sg = u.getPhone() + " information:" + information;
			Email = "user@jiazhangtd.net";
			OpeFunction.Email(Email, sg);
			OpeFunction.Out().print(true);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	public void webSaveFeedback() throws IOException {

		System.out.println(" webSaveFeedback" + "," + username + ","
				+ information);
		User u = userdao.byUsernameAccnumnoPhone(username);
		if (u == null) {

			username = "username Is Null";
		}
		Feedback f = new Feedback();
		f.setUsername(username);
		f.setInformation(information);
		f.setTime(OpeFunction.getNowTime());
		audao.Save(f);
		String Email = "";
		String sg = username + " information:" + information;
		Email = "user@jiazhangtd.net";
		// OpeFunction.Email(Email, sg);

		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/webNewsA10");

	}

	public void AUP() throws IOException {

		System.out.println(vnum);
		System.out.println(type);
		System.out.println(summary);
		System.out.println(name);

		savePath = "/appimg";
		if (imgFile == null) {
			OpeFunction.Out().print("logoNULL");
			return;
		}
		if (appFile == null) {
			OpeFunction.Out().print("appNULL");
			return;
		}
		if (imgFile1 == null) {
			OpeFunction.Out().print("imgFile1 Is NULL");
			return;
		}
		if (imgFile2 == null) {
			OpeFunction.Out().print("imgFile2 Is NULL");
			return;
		}
		if (imgFile3 == null) {
			OpeFunction.Out().print("imgFile3 Is NULL");
			return;
		}

		String img = OpeFunction.fileToServer(savePath, imgFile,
				imgFileFileName, imgFileContentType, true);

		String img1 = OpeFunction.fileToServer(savePath, imgFile1,
				imgFile1FileName, imgFile1ContentType, true);
		String img2 = OpeFunction.fileToServer(savePath, imgFile2,
				imgFile2FileName, imgFile2ContentType, true);
		String img3 = OpeFunction.fileToServer(savePath, imgFile3,
				imgFile3FileName, imgFile3ContentType, true);

		savePath = "/app";

		String apk = OpeFunction.fileToServer(savePath, appFile,
				appFileFileName, appFileContentType, false);
		float fl = 0;
		fl = OpeFunction.fileSizem(appFile);
		System.out.println("appFile path" + fl);
		App app = new App();
		app.setName(name);
		app.setPathapk(apk);
		app.setPathimg(img);

		app.setPathimg1(img1);
		app.setPathimg2(img2);
		app.setPathimg3(img3);

		app.setAppsize(fl);
		app.setVnum(vnum);
		app.setType(type);
		app.setRealds(0);

		if (downloads <= 0) {
			downloads = 0;

			System.out.println("downloads" + downloads);
		}
		app.setDownloads(downloads);

		if (num <= 0) {
			num = 0;
			System.out.println("num" + num);

		}
		app.setSequence(num);
		app.setTime(OpeFunction.getNowTime());
		app.setSummary(summary);
		app.setDpt(dpt);
		adao.Save(app);

		OpeFunction.Out().print(true);
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/Appset");

	}

	public void getbyidapp() throws IOException {
		if (id == 0) {
			OpeFunction.Out().print(OpeFunction.ToJson("idnull"));
			return;
		}
		app = adao.byid(id);
		if (app != null) {
			OpeFunction.Out().print(OpeFunction.ToJson(app));
		} else {
			OpeFunction.Out().print(OpeFunction.ToJson("null"));
		}
	}

	public void getapp() {
		try {

			if (num == 0) {
				num = 8;
			}

			List<App> a = adao.All(num);
			OpeFunction.Out().print(OpeFunction.ToJson(a));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String webGetapp() {
		try {

			if (num == 0) {
				num = 8;
			}

			List<App> la = adao.All(0);
			request.setAttribute("la", la);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}

	Adv adv = new Adv();
	private List<Adv> advl;
	private int sequence;// 排序
	private int Online = 0;// 0未发布 1发布
	private String href;// 广告连接

	public String saveAdv() throws IOException {
		img = "/IMG/Advimg/" + OpeFunction.getNameDayTime();
		img = OpeFunction.fileToServer(img, imgFile1, imgFile1FileName,
				imgFile1ContentType, true);
		adv.setName(name);
		adv.setSummary(summary);
		if (sequence > 0) {
			adv.setSequence(sequence);
		} else {
			adv.setSequence(1);
		}
		adv.setPathimg(img);
		adv.setTime(time);
		adv.setTimeo(time);
		adv.setType(type);
		adv.setHref(href);
		adao.Save(adv);
		System.out.println("saveAdv ok!");
		return Action.SUCCESS;
	}

	public String byidLookAdv() throws IOException {
		adv = adao.byAdvid(id);
		request.setAttribute("adv", adv);
		return Action.SUCCESS;

	}

	public void aClicktoRate() throws IOException {
		User u = (User) session.getAttribute("u");
		if (u == null) {
			u = userdao.byid(userid);
		}
		adv = adao.byAdvid(id);
		if (u != null && adv != null) {
			switch (u.getOs()) {
			case User.BBT:
				adv.setBbt(adv.getBbt() + 1);
				break;
			case User.SYN:
				adv.setSyn(adv.getSyn() + 1);
				break;
			case User.WEB:
				adv.setWeb(adv.getWeb() + 1);
				break;
			case User.ANDROID:
				adv.setAndroid(adv.getAndroid() + 1);
				break;
			case User.IOS:
				adv.setIos(adv.getIos() + 1);
				break;

			default:
				break;
			}
			adao.Update(adv);
		} else if (u == null && adv != null) {
			adv.setWechat(adv.getWechat() + 1);
			adao.Update(adv);
		}

	}

	public String onlineAdv() throws IOException {
		time = OpeFunction.getNowTime();
		adv = adao.byAdvid(id);
		if (adv != null) {
			if (Online == 0) {
				adv.setCalculatingTime(adv.getCalculatingTime()
						+ OpeFunction.calculatingTime(adv.getTimeo(), time));
				adv.setFinaltime(time);
			} else {
				adv.setTimeo(time);
			}

			System.out
					.println(OpeFunction.calculatingTime(adv.getTime(), time));
			adv.setOnline(Online);
			adao.Update(adv);
			System.out.println("修改广告状态成功!" + Online);
		}
		return Action.SUCCESS;
	}

	public String updateAdv() throws IOException {
		adv = adao.byAdvid(id);
		if (adv != null) {
			if (!OpeFunction.isEmpty(name)) {
				adv.setName(name);
			}
			if (!OpeFunction.isEmpty(href)) {
				adv.setHref(href);
			}
			if (!OpeFunction.isEmpty(summary)) {
				adv.setSummary(summary);
			}
			if (imgFile1 != null) {
				img = "/IMG/Advimg/" + OpeFunction.getNameDayTime();
				img = OpeFunction.fileToServer(img, imgFile1, imgFile1FileName,
						imgFile1ContentType, true);
				OpeFunction.fileRemove(adv.getPathimg());
				adv.setPathimg(img);

			}
			if (sequence > 0) {
				adv.setSequence(sequence);
			}
			adao.Update(adv);
			System.out.println("修改广告成功!");
		}
		return Action.SUCCESS;
	}

	public String getAdv() {
		advl = adao.getAdvnum(num, Online);
		request.setAttribute("advl", advl);
		return Action.SUCCESS;
	}

	public int getOnline() {
		return Online;
	}

	public void setOnline(int online) {
		Online = online;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getAppFileFileName() {
		return appFileFileName;
	}

	public void setAppFileFileName(String appFileFileName) {
		this.appFileFileName = appFileFileName;
	}

	public String getAppFileContentType() {
		return appFileContentType;
	}

	public void setAppFileContentType(String appFileContentType) {
		this.appFileContentType = appFileContentType;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public File getAppFile() {
		return appFile;
	}

	public void setAppFile(File appFile) {
		this.appFile = appFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public ApputilAction(ApputilDAO audao, AppDAO adao, UserDAO userdao) {
		super();
		this.audao = audao;
		this.adao = adao;
		this.userdao = userdao;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getApptv() {
		return apptv;
	}

	public void setApptv(int apptv) {
		this.apptv = apptv;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}

	public File getImgFile1() {
		return imgFile1;
	}

	public void setImgFile1(File imgFile1) {
		this.imgFile1 = imgFile1;
	}

	public File getImgFile2() {
		return imgFile2;
	}

	public void setImgFile2(File imgFile2) {
		this.imgFile2 = imgFile2;
	}

	public File getImgFile3() {
		return imgFile3;
	}

	public void setImgFile3(File imgFile3) {
		this.imgFile3 = imgFile3;
	}

	public String getImgFile1FileName() {
		return imgFile1FileName;
	}

	public void setImgFile1FileName(String imgFile1FileName) {
		this.imgFile1FileName = imgFile1FileName;
	}

	public String getImgFile1ContentType() {
		return imgFile1ContentType;
	}

	public void setImgFile1ContentType(String imgFile1ContentType) {
		this.imgFile1ContentType = imgFile1ContentType;
	}

	public String getImgFile2FileName() {
		return imgFile2FileName;
	}

	public void setImgFile2FileName(String imgFile2FileName) {
		this.imgFile2FileName = imgFile2FileName;
	}

	public String getImgFile2ContentType() {
		return imgFile2ContentType;
	}

	public void setImgFile2ContentType(String imgFile2ContentType) {
		this.imgFile2ContentType = imgFile2ContentType;
	}

	public String getImgFile3FileName() {
		return imgFile3FileName;
	}

	public void setImgFile3FileName(String imgFile3FileName) {
		this.imgFile3FileName = imgFile3FileName;
	}

	public String getImgFile3ContentType() {
		return imgFile3ContentType;
	}

	public void setImgFile3ContentType(String imgFile3ContentType) {
		this.imgFile3ContentType = imgFile3ContentType;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
	}

	public String getDpt() {
		return dpt;
	}

	public void setDpt(String dpt) {
		this.dpt = dpt;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMac() {
		return Mac;
	}

	public void setMac(String mac) {
		Mac = mac;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getSynlogin() {
		return synlogin;
	}

	public void setSynlogin(int synlogin) {
		this.synlogin = synlogin;
	}

	public int getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}

	public int getUsersyned() {
		return usersyned;
	}

	public void setUsersyned(int usersyned) {
		this.usersyned = usersyned;
	}

	public int getVored() {
		return vored;
	}

	public void setVored(int vored) {
		this.vored = vored;
	}

	public int getUsersaved() {
		return usersaved;
	}

	public void setUsersaved(int usersaved) {
		this.usersaved = usersaved;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return Keywords;
	}

	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

}
