package com.befriend.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.befriend.entity.App;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
import com.befriend.entity.House;
import com.befriend.entity.ParentsLetter;
import com.befriend.entity.Stas;
import com.befriend.entity.User;
import com.befriend.entity.Visitor;
import com.befriend.util.Message;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ApputilAction {
	public OpeFunction util;// 锟斤拷锟斤拷锟斤拷
	private ApputilDAO audao;// ApputilDAO
	private UserDAO userdao;// 锟矫伙拷dao
	private AppDAO adao;// appdao
	private String username;// 锟矫伙拷锟斤拷
	private String information;//

	private File imgFile;// logo图片
	private File xlsxFile;// xlsx锟侥硷拷
	
	private String xlsxFileFileName;// 锟侥硷拷锟斤拷
	private String imgFileFileName;// 锟侥硷拷锟斤拷
	private String imgFileContentType;// 锟侥硷拷锟斤拷锟斤拷

	private File imgFile1;// 应锟矫斤拷图1
	private String imgFile1FileName;// 锟侥硷拷锟斤拷
	private String imgFile1ContentType;// 锟侥硷拷锟斤拷锟斤拷

	private File imgFile2;// 应锟矫斤拷图2
	private String imgFile2FileName;// 锟侥硷拷锟斤拷
	private String imgFile2ContentType;// 锟侥硷拷锟斤拷锟斤拷

	private File imgFile3;// 应锟矫斤拷图3
	private String imgFile3FileName;// 锟侥硷拷锟斤拷
	private String imgFile3ContentType;// 锟侥硷拷锟斤拷锟斤拷

	private String savePath;// 目录

	private File appFile;// app锟侥硷拷
	private String appFileFileName;// 锟侥硷拷锟斤拷
	private String appFileContentType;// 锟侥硷拷锟斤拷锟斤拷

	private String vnum;// app锟芥本锟斤拷
	private String type;// app锟斤拷锟�
	private String dpt;// 应锟斤拷锟斤拷锟斤拷
	private String summary;// 应锟矫革拷要

	private String name;// app锟斤拷锟斤拷
	private int num = 0;// app锟狡硷拷指锟斤拷

	private int currentPage = 1;// 页锟斤拷
	private int id = 0;// id
	private int pageSize = 4;// 锟斤拷锟斤拷

	private int downloads = 0;// app锟斤拷锟截达拷锟斤拷

	private int apptv = 0;// 锟芥本锟斤拷
	private String updates;// 锟斤拷锟斤拷锟斤拷锟斤拷

	AppUp au = new AppUp();// appup实锟斤拷锟斤拷 锟揭筹拷之锟窖革拷锟斤拷锟矫碉拷锟斤拷
	App app = new App();// app实锟斤拷锟斤拷
	Visitor vor = new Visitor();// 锟轿匡拷锟斤拷
	public HttpServletRequest request = ServletActionContext.getRequest();// 锟斤拷取request
	private String Mac;//
	private String os;// 系统
	private String time = util.getNowTime();
	private String province = "all";// 省锟斤拷
	private int synlogin;// 锟斤拷锟斤拷锟斤拷锟�
	private int downloaded;// 锟斤拷锟截达拷锟斤拷
	private int usersyned;// 同时锟斤拷锟斤拷锟斤拷
	private int vored;// 锟斤拷锟斤拷锟斤拷锟斤拷锟轿匡拷锟斤拷
	private int usersaved;// 锟斤拷锟斤拷注锟斤拷锟斤拷锟斤拷
	private String schoolname;// 学校锟斤拷锟斤拷
	private String address;// 学校锟斤拷址
	private String scope;// 锟斤拷锟斤拷围
	private String property;// 楼锟斤拷
	private String content;//锟斤拷锟斤拷
	private String img;//图片
	private String title;//锟斤拷锟斤拷
	HttpSession session = ServletActionContext.getRequest().getSession();
	/**
	 * 通锟斤拷id删锟斤拷锟揭筹拷锟斤拷锟斤拷
	 */
	public void RemoveParentsLetterone() throws IOException {
		ParentsLetter p=audao.Plbyid(id);
		if(p!=null){
		audao.Remove(p);
		}
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/selectParentsLetter");
		
	}
	/**
	 * 通锟斤拷id锟介看锟揭筹拷锟斤拷锟斤拷
	 */
	public String selectParentsLetterone() throws IOException {
		ParentsLetter p=audao.Plbyid(id);
		request.setAttribute("p", p);
		return Action.SUCCESS;
		
	}
	/**
	 * 锟介看锟揭筹拷锟斤拷锟斤拷
	 */
	public String selectParentsLetter() throws IOException {
		List<ParentsLetter> pl=audao.PlAll();
		request.setAttribute("pl", pl);
		return Action.SUCCESS;
		
	}
	/**
	 * 锟斤拷台 锟斤拷蛹页锟斤拷锟斤拷锟�
	 */
	public String SaveParentsLetter() throws IOException {
		System.out.println("SaveParentsLetter");
		ParentsLetter p=new ParentsLetter();
		p.setContent(content);
		String path="/ParentsLetter/img";
		if(imgFile!=null){
			img=util.ufileToServer(path, imgFile, "", "jpg", true);
			p.setImg(img);
		}
		
		p.setTitle(title);
		p.setTime(time);
		audao.Save(p);
		return Action.SUCCESS;
	}
	public void appSavePL() throws IOException {
		ParentsLetter p=new ParentsLetter();
		Message mge=new Message();
		if(util.isEmpty(content)||util.isEmpty(title)){
			mge.setCode(mge.NULL);
			mge.setStatement("图片或者标题为空!");
			util.Out().print(util.ToJson(mge));
			return;
		}
		p.setContent(content);
		String path="/ParentsLetter/img";
		System.out.println("imgFile-"+imgFile);
		if(imgFile!=null){
			img=util.ufileToServer(path, imgFile, "", "jpg", true);
			p.setImg(img);
		}else{
			System.out.println("imgFile-null");
		}
		
		p.setTitle(title);
		p.setTime(time);
		audao.Save(p);
		
		mge.setCode(mge.SUCCESS);
		mge.setStatement("成功!");
		mge.setContent("true");
		util.Out().print(util.ToJson(mge));
		
	}
	/**
	 * web 锟斤拷蛹页锟斤拷锟斤拷锟�
	 */
	public void SaveParentsL() throws IOException {
		ParentsLetter p=new ParentsLetter();
		p.setContent(content);
		String path="/ParentsLetter/img";
		if(imgFile!=null){
			/**
			BufferedImage sourceImg = ImageIO
					.read(new FileInputStream(imgFile));
			if (sourceImg.getWidth() < 720 || sourceImg.getHeight() <360) {
				util.Out().print("图片锟竭达拷锟斤拷要锟斤拷锟斤拷720*360 锟斤拷锟斤拷锟斤拷锟铰硷拷锟斤拷拢锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷裕锟�");
				return ;
			}
			float fimg = util.fileSize(imgFile);
			if (fimg > 2048.00) {
				util.Out().print("锟斤拷图片锟斤拷要锟斤拷小为 2MB 锟斤拷锟铰ｏ拷锟斤拷锟斤拷锟斤拷锟铰硷拷锟斤拷拢锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷裕锟�");
				return ;
			}
			*/
			
			img=util.ufileToServer(path, imgFile, "", "jpg", true);
			p.setImg(img);
		}
		
		p.setTitle(title);
		p.setTime(time);
		audao.Save(p);
		//util.outjS("/Befriend/webNewsA10","Thank you!");
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/webNewsA10");
		return ;
	}
	
	/**
	 * 通锟斤拷id删锟斤拷锟揭筹拷锟斤拷锟斤拷锟斤拷息
	 */
	public void Removejzfkid() throws IOException {
		Feedback f=audao.FeedbackByid(id);
		if(f!=null){
		audao.Remove(f);
		}
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/selectjzfk");
		
		
	}
	/**
	 * 通锟斤拷id锟介看锟揭筹拷锟斤拷锟斤拷锟斤拷息
	 */
	public String selectjzfkid() throws IOException {
		Feedback f=audao.FeedbackByid(id);
		request.setAttribute("f", f);
		return Action.SUCCESS;
		
	}
	/**
	 * 锟介看锟揭筹拷锟斤拷锟斤拷锟斤拷息
	 */
	public String selectjzfk() throws IOException {
		List<Feedback> fl=audao.FeedbackAll();
		request.setAttribute("fl", fl);
		return Action.SUCCESS;
		
	}
	/**
	 * web模锟斤拷锟斤拷询学锟斤拷锟斤拷锟斤拷息
	 */
	public String selectwxHouseallzh() throws IOException {
		System.out.println("selectwxHouseallzhh");
		
		List<House> hl=audao.HouseZh(6);
		
		for(int i=0;i<hl.size();i++){
			House h=hl.get(i);
			h.setCount((h.getCount()+1));
			audao.Update(h);
			
		}
		System.out.println("锟斤拷:"+hl.size());
		request.setAttribute("hl", hl);
		
		return Action.SUCCESS;
		
	}
	/**
	 * 
	 */
	public void appSelectHouseallZh() throws IOException {
		System.out.println("appSelectHouseallZh"+num);
		if(num<=0){
			num=6;
		}
		List<House> hl=audao.HouseZh(num);
		
		for(int i=0;i<hl.size();i++){
			House h=hl.get(i);
			h.setCount((h.getCount()+1));
			audao.Update(h);
			
		}
		util.Out().print(util.ToJson(hl));
		
		
	}
	/**
	 * web模锟斤拷锟斤拷询学锟斤拷锟斤拷锟斤拷息
	 */
	public String selectwxHouseall() throws IOException {
		
		if(util.isEmpty(schoolname)){
			System.out.println("schoolname锟角空碉拷");
		}
		List<House> hl=audao.bylikeshoolname(schoolname);
		
		for(int i=0;i<hl.size();i++){
			House h=hl.get(i);
			h.setCount((h.getCount()+1));
			audao.Update(h);
			
		}
		request.setAttribute("hl", hl);
		request.setAttribute("name", schoolname);
		return Action.SUCCESS;
		
	}
	/**
	 * app模锟斤拷锟斤拷询学锟斤拷锟斤拷锟斤拷息
	 */
	public void appselectHouseall() throws IOException {
		System.out.println("schoolname锟斤拷:"+schoolname);	
		Message mge=new Message();
		if(util.isEmpty(schoolname)){
			mge.setCode(mge.NULL);
			mge.setStatement("锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷");
			util.Out().print(util.ToJson(mge));
			return;
		}
		List<House> hl=audao.bylikeshoolname(schoolname);
		
		for(int i=0;i<hl.size();i++){
			House h=hl.get(i);
			h.setCount((h.getCount()+1));
			audao.Update(h);
			
		}
		mge.setCode(mge.SUCCESS);
		mge.setContent(hl);
		mge.setStatement("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷");
		util.Out().print(util.ToJson(mge));
		
	}
	/**
	 * 锟斤拷台模锟斤拷锟斤拷询学锟斤拷锟斤拷锟斤拷息
	 */
	public String selectHouseall() throws IOException {
		System.out.println("schoolname锟斤拷:"+schoolname);	
		if(util.isEmpty(schoolname)){
			System.out.println("schoolname锟秸碉拷");
			return Action.SUCCESS;
		}
		List<House> hl=audao.bylikeshoolname(schoolname);
		
		for(int i=0;i<hl.size();i++){
			House h=hl.get(i);
			h.setCount((h.getCount()+1));
			audao.Update(h);
			
		}
		request.setAttribute("hl", hl);
		return Action.SUCCESS;
		
	}
	/**
	 * 学锟斤拷锟斤拷锟斤拷息锟睫革拷
	 */
	public String upHouse() throws IOException {
		House house=audao.byid(id);
		System.out.println("锟斤拷锟斤拷upHouse"+id);
		if(house!=null){
			house.setAddress(address);
			house.setProperty(property);
			house.setSchoolname(schoolname);
			house.setScope(scope);
			house.setTime(time);
			audao.Update(house);
			System.out.println("锟睫改成癸拷");
			request.setAttribute("h", house);
			request.setAttribute("hh", "锟睫改成癸拷!");
			return Action.SUCCESS;
		}
		System.out.println("为锟斤拷!");
		return null;
	}
	/**
	 * 锟介看学锟斤拷锟斤拷锟斤拷息
	 */
	public String selectHousenoe() throws IOException {
		House house=audao.byid(id);
		System.out.println("锟斤拷锟斤拷selectHousenoe");
		if(house!=null){
			request.setAttribute("h", house);
			return Action.SUCCESS;
		}
		return null;
	}
	/**
	 * 锟斤拷台删锟斤拷学锟斤拷锟斤拷
	 * @throws IOException 
	 */
	public void RemovetHouse() throws IOException {
		House house=audao.byid(id);
		System.out.println("锟斤拷锟斤拷RemovetHouse");
		if(house!=null){
		audao.Remove(house);
		System.out.println("删锟斤拷锟斤拷house");
		}
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/selectHouse?currentPage="+currentPage);
		
		

	}

	/**
	 * 锟斤拷台锟斤拷页锟介看学锟斤拷锟斤拷
	 */
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

	/**
	 * 锟斤拷锟窖э拷锟斤拷锟�
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	public String Addschooldistrict() throws IOException, InvalidFormatException {
		System.out.println("锟斤拷锟斤拷锟斤拷锟窖э拷锟斤拷锟�");
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("没锟叫碉拷锟斤拷");
			return null;
		}
		
		if(xlsxFile!=null){
			System.out.println("xlsxFileFileName锟斤拷:"+xlsxFileFileName);
			
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(xlsxFile);

			// 循锟斤拷锟斤拷锟斤拷锟斤拷Sheet
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				
				// 循锟斤拷锟斤拷Row
				for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow == null) {
						continue;
					}
					//锟斤拷一锟轿诧拷锟斤拷 锟角憋拷锟斤拷
					if(rowNum<1){
						//System.out.println("锟叫ｏ拷"+xssfRow.getCell(0));
						System.out.println("学校锟斤拷锟狡ｏ拷"+xssfRow.getCell(1));
						System.out.println("学校锟斤拷址锟斤拷"+xssfRow.getCell(2));
						System.out.println("学锟斤拷锟斤拷围锟斤拷"+xssfRow.getCell(3));
						System.out.println("学锟斤拷锟斤拷围锟斤拷锟斤拷要锟街碉拷锟斤拷小锟斤拷锟斤拷楼锟教★拷锟斤拷位锟斤拷锟结："+xssfRow.getCell(4));
						System.out.println("锟斤拷"+numSheet+"锟斤拷锟斤拷锟斤拷锟斤拷Sheet,锟侥碉拷"+rowNum+"锟斤拷");
						}
					System.out.println("学校锟斤拷锟狡ｏ拷"+xssfRow.getCell(1));
					System.out.println("学校锟斤拷址锟斤拷"+xssfRow.getCell(2));
					System.out.println("学锟斤拷锟斤拷围锟斤拷"+xssfRow.getCell(3));
					System.out.println("学锟斤拷锟斤拷围锟斤拷锟斤拷要锟街碉拷锟斤拷小锟斤拷锟斤拷楼锟教★拷锟斤拷位锟斤拷锟结："+xssfRow.getCell(4));
					System.out.println("锟斤拷"+numSheet+"锟斤拷锟斤拷锟斤拷锟斤拷Sheet,锟侥碉拷"+rowNum+"锟斤拷");
					System.out.println("准锟斤拷锟斤拷锟�!");
					
					if(xssfRow.getCell(1)!=null){
						schoolname=xssfRow.getCell(1).toString();
						}
					if(schoolname==null){
						System.out.println("锟窖撅拷锟斤拷庸锟�");
						continue;
					}
					if(audao.byshoolname(schoolname)!=null){
						System.out.println("锟窖撅拷锟斤拷庸锟�");
						continue;
					}
					//锟斤拷锟揭拷娲拷锟斤拷锟较�
					House h = new House();
					h.setSchoolname(schoolname);
					if(xssfRow.getCell(2)!=null){
					h.setAddress(xssfRow.getCell(2).toString());
					}
					if(xssfRow.getCell(4)!=null){
					h.setProperty(xssfRow.getCell(4).toString());
					}
					if(xssfRow.getCell(3)!=null){
					h.setScope(xssfRow.getCell(3).toString());
					}
					
				
					
					h.setTime(time);
					h.setAdmin(admin.getAdmin());
					audao.Save(h);
										
				}
			}
			System.out.println("xlsx锟斤拷锟斤拷锟斤拷");
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
		h.setAdmin(admin.getAdmin());
		audao.Save(h);
		System.out.println("锟斤拷锟斤拷锟斤拷");
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
	// 锟斤拷锟斤拷注锟斤拷锟斤拷锟斤拷
	/**
	 * 锟斤拷录统锟狡变化 锟斤拷始锟斤拷
	 */
	public void aStas() {
		System.out.println("province" + province);
		// 锟斤拷锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷
		time = util.getNumTime(0);

		// 锟斤拷始锟斤拷3锟斤拷系统统锟斤拷锟斤拷
		String sys = "";
		for (int i = 0; i < 5; i++) {
			if (province.equals("all") || province == null
					|| province.equals("null")) {
				continue;
			}
			switch (i) {
			case 0:
				sys = "web";
				break;
			case 1:
				sys = "android";
				break;
			case 2:
				sys = "ios";
				break;
			case 3:
				sys = "syn";
				break;
			case 4:
				sys = "bbt";
				break;

			default:
				sys = null;
				break;
			}
			if (sys == null) {
				continue;
			}
			Stas sta = audao.StasTimeDay(time, sys, province);
			// 锟斤拷取锟斤拷锟斤拷注锟斤拷锟矫伙拷锟斤拷锟斤拷
			usersaved = userdao.getSaveTime(time, sys, province).size();
			// 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟轿匡拷
			vored = audao.VisitorTime(time, sys, province).size();
			System.out.println("11111111111111111" + vored + province + sys);
			// 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷
			synlogin = userdao.getFinaltime(time, sys, province).size();
			if (synlogin != 0) {
				if (synlogin % 3 == 0) {
					usersyned = synlogin / 3;
				} else {
					usersyned = synlogin / 3 + 1;
				}
			} else {
				usersyned = 0;
			}
			// 锟斤拷取锟斤拷锟斤拷锟矫伙拷
			// usersyned = userdao.getOnline(sys,province).size();
			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);// 锟斤拷锟斤拷
				sta.setTime(time);// 统锟斤拷时锟斤拷
				sta.setUserlogined(synlogin);// 锟斤拷锟斤拷锟斤拷锟�
				if (downloads != 0) {
					sta.setDownloaded(1);
				} else {
					sta.setDownloaded(0);
				}// 锟斤拷锟斤拷锟斤拷锟斤拷
				sta.setOs(sys);// 锟斤拷锟皆碉拷系统
				sta.setUsersyned(usersyned);// 同时锟斤拷锟斤拷锟斤拷
				sta.setUsersaved(usersaved);// 锟斤拷锟斤拷注锟斤拷锟斤拷锟斤拷
				sta.setVored(vored);// 锟斤拷锟斤拷锟斤拷锟斤拷锟轿匡拷锟斤拷

				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys, province);

			if (sta == null) {
				continue;
			}
			// sta.setProvince(province);//统锟斤拷全锟斤拷
			// sta.setTime(time);// 统锟斤拷时锟斤拷

			sta.setUserlogined(synlogin);// 锟斤拷锟斤拷锟斤拷锟�
			if (downloaded != 0 && sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);// 锟斤拷锟斤拷锟斤拷锟斤拷
			}
			// sta.setOs(os);//锟斤拷锟皆碉拷系统
			if (usersyned > sta.getUsersyned()) {
				sta.setUsersyned(usersyned);// 同时锟斤拷锟斤拷锟斤拷
			}
			sta.setUsersaved(usersaved);// 锟斤拷锟斤拷注锟斤拷锟斤拷锟斤拷

			sta.setVored(vored);// 锟斤拷锟斤拷锟斤拷锟斤拷锟轿匡拷锟斤拷

			audao.Update(sta);

		}

		for (int i = 0; i < 5; i++) {

			switch (i) {
			case 0:
				sys = "web";
				break;
			case 1:
				sys = "android";
				break;
			case 2:
				sys = "ios";
				break;
			case 3:
				sys = "syn";
				break;
			case 4:
				sys = "bbt";
				break;

			default:
				sys = null;
				break;
			}
			if (sys == null) {
				continue;
			}

			Stas sta = audao.StasTimeDay(time, sys, "all");
			// 锟斤拷取锟斤拷锟斤拷注锟斤拷锟矫伙拷锟斤拷锟斤拷
			usersaved = userdao.getSaveTime(time, sys).size();
			System.out.println("锟斤拷锟斤拷注锟斤拷锟斤拷" + usersaved + sys);
			// 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟轿匡拷
			vored = audao.VisitorTime(time, sys).size();
			System.out.println(vored + "vored" + sys + time);
			// 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷
			synlogin = userdao.getFinaltime(time, sys).size();
			// 锟斤拷取锟斤拷锟斤拷锟矫伙拷
			// usersyned = userdao.getOnline(sys).size();
			if (synlogin != 0) {
				if (synlogin % 3 == 0) {
					usersyned = synlogin / 3;
				} else {
					usersyned = synlogin / 3 + 1;
				}
			} else {
				usersyned = 0;
			}
			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);// 锟斤拷锟斤拷
				sta.setTime(time);// 统锟斤拷时锟斤拷
				sta.setUserlogined(synlogin);// 锟斤拷锟斤拷锟斤拷锟�
				if (downloaded != 0 && sys.equals(os)) {
					sta.setDownloaded(1);// 锟斤拷锟斤拷锟斤拷锟斤拷
				} else {
					sta.setDownloaded(0);// 锟斤拷锟斤拷锟斤拷锟斤拷
				}
				sta.setOs(sys);// 锟斤拷锟皆碉拷系统
				sta.setUsersyned(usersyned);// 同时锟斤拷锟斤拷锟斤拷
				sta.setUsersaved(usersaved);// 锟斤拷锟斤拷注锟斤拷锟斤拷锟斤拷
				sta.setVored(vored);// 锟斤拷锟斤拷锟斤拷锟斤拷锟轿匡拷锟斤拷
				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys, "all");
			// sta.setProvince(province);//统锟斤拷全锟斤拷
			// sta.setTime(time);// 统锟斤拷时锟斤拷

			sta.setUserlogined(synlogin);// 锟斤拷锟斤拷锟斤拷锟�
			if (downloaded != 0 && sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);// 锟斤拷锟斤拷锟斤拷锟斤拷
			}
			// sta.setOs(os);//锟斤拷锟皆碉拷系统

			sta.setUsersyned(usersyned);// 同时锟斤拷锟斤拷锟斤拷

			sta.setUsersaved(usersaved);// 锟斤拷锟斤拷注锟斤拷锟斤拷锟斤拷

			sta.setVored(vored);// 锟斤拷锟斤拷锟斤拷锟斤拷锟轿匡拷锟斤拷

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
		if (os.equals("android")) {
			os = "android";
		} else if (os.equals("ios")) {
			os = "ios";
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
		au = audao
				.select("http://182.92.100.235/Befriend/AppUp/FamilyGroup.apk");
		if (au != null) {
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
				OpeFunction.Out().print("FamilyGroup.apk为null");
				return;
			}
			if (!appFileFileName.equals("FamilyGroup.apk")) {
				OpeFunction.Out().print("锟斤拷锟街憋拷锟斤拷为'FamilyGroup.apk'");
				return;
			}
			System.out.println(appFileFileName);
			au = audao
					.select("http://182.92.100.235/Befriend/AppUp/FamilyGroup.apk");
			Boolean b = false;
		
			if (au != null) {
				File file1 = new File(ServletActionContext.getServletContext()
						.getRealPath("/AppUp/FamilyGroup.apk"));
				b = file1.delete();
				audao.Remove(au);
			}

			savePath = "AppUp";
			AppUp ap = new AppUp();

			String upth = "http://182.92.100.235/Befriend/"
					+ OpeFunction.fileToServer(savePath, appFile,
							appFileFileName, appFileContentType, false);
			
			savePath = "AppUp/Past";
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

			// 转锟斤拷
		
			((HttpServletResponse) util.response())
					.sendRedirect("http://182.92.100.235/Befriend/AppUp/FamilyGroup.apk");

		}

	}

	/**
	 * 
	 *
	 * @throws IOException
	 */
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
					System.out
							.println("OS:" + sUA.indexOf(al.get(i)));
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
			// TODO: handle exception
		}

	}

	/**
	 * 8 app 
	 * 
	 * @return
	 */
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

	/**
	 * 删锟斤拷app 通锟斤拷id
	 * 
	 * @throws IOException
	 */
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

	/**
	 * 
	 */
	public void AppDs() {

		app = adao.byid(id);
		int iz = app.getRealds() + 1;
		int i = app.getDownloads() + 1;
		app.setRealds(iz);
		app.setDownloads(i);
		adao.Ds(app);

	}

	/**
	 * 
	 * 
	 * @throws IOException
	 */
	public void Appup() throws IOException {
		try {
			au = audao.UP();
			OpeFunction.Out().print(OpeFunction.ToJson(au));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Appupnum
	 * 
	 * @throws IOException
	 */
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

	/**
	 * SaveFeedback
	 */
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

	/**
	 * Feedback
	 */
	public void webSaveFeedback() {
		try {

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
			if (u != null) {
				username = u.getPhone();
			}
			String Email = "";
			String sg = username + " information:" + information;
			Email = "user@jiazhangtd.net";
			// OpeFunction.Email(Email, sg);

			((HttpServletResponse) util.response()).sendRedirect(request
					.getContextPath() + "/webNewsA10");
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * save app
	 */
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

	/**
	 * 通锟斤拷id锟斤拷询app锟斤拷息
	 * 
	 * @throws IOException
	 */
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

	/**
	 * 锟斤拷锟截革拷 锟酵伙拷锟斤拷 锟斤拷8锟斤拷app
	 */
	public void getapp() {
		try {
			// num锟斤拷锟斤拷0 锟斤拷锟斤拷默锟斤拷值
			if (num == 0) {
				num = 8;
			}

			List<App> a = adao.All(num);
			OpeFunction.Out().print(OpeFunction.ToJson(a));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 锟斤拷锟截革拷 web锟斤拷锟斤拷 锟斤拷n锟斤拷app
	 */
	public String webGetapp() {
		try {
			// num锟斤拷锟斤拷0 锟斤拷锟斤拷默锟斤拷值
			if (num == 0) {
				num = 8;
			}
			// 锟斤拷询app锟斤拷息
			List<App> la = adao.All(0);
			request.setAttribute("la", la);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
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

}
