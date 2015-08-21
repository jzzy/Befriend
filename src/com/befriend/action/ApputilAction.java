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
	public OpeFunction util;// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	private ApputilDAO audao;// ApputilDAO
	private UserDAO userdao;// 閿熺煫浼欐嫹dao
	private AppDAO adao;// appdao
	private String username;// 閿熺煫浼欐嫹閿熸枻鎷�
	private String information;//

	private File imgFile;// logo鍥剧墖
	private File xlsxFile;// xlsx閿熶茎纭锋嫹
	
	private String xlsxFileFileName;// 閿熶茎纭锋嫹閿熸枻鎷�
	private String imgFileFileName;// 閿熶茎纭锋嫹閿熸枻鎷�
	private String imgFileContentType;// 閿熶茎纭锋嫹閿熸枻鎷烽敓鏂ゆ嫹

	private File imgFile1;// 搴旈敓鐭枻鎷峰浘1
	private String imgFile1FileName;// 閿熶茎纭锋嫹閿熸枻鎷�
	private String imgFile1ContentType;// 閿熶茎纭锋嫹閿熸枻鎷烽敓鏂ゆ嫹

	private File imgFile2;// 搴旈敓鐭枻鎷峰浘2
	private String imgFile2FileName;// 閿熶茎纭锋嫹閿熸枻鎷�
	private String imgFile2ContentType;// 閿熶茎纭锋嫹閿熸枻鎷烽敓鏂ゆ嫹

	private File imgFile3;// 搴旈敓鐭枻鎷峰浘3
	private String imgFile3FileName;// 閿熶茎纭锋嫹閿熸枻鎷�
	private String imgFile3ContentType;// 閿熶茎纭锋嫹閿熸枻鎷烽敓鏂ゆ嫹

	private String savePath;// 鐩綍

	private File appFile;// app閿熶茎纭锋嫹
	private String appFileFileName;// 閿熶茎纭锋嫹閿熸枻鎷�
	private String appFileContentType;// 閿熶茎纭锋嫹閿熸枻鎷烽敓鏂ゆ嫹

	private String vnum;// app閿熻姤鏈敓鏂ゆ嫹
	private String type;// app閿熸枻鎷烽敓锟�
	private String dpt;// 搴旈敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	private String summary;// 搴旈敓鐭潻鎷疯

	private String name;// app閿熸枻鎷烽敓鏂ゆ嫹
	private int num = 0;// app閿熺嫛纭锋嫹鎸囬敓鏂ゆ嫹

	private int currentPage = 1;// 椤甸敓鏂ゆ嫹
	private int id = 0;// id
	private int pageSize = 4;// 閿熸枻鎷烽敓鏂ゆ嫹

	private int downloads = 0;// app閿熸枻鎷烽敓鎴揪鎷烽敓鏂ゆ嫹

	private int apptv = 0;// 閿熻姤鏈敓鏂ゆ嫹
	private String updates;// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹

	AppUp au = new AppUp();// appup瀹為敓鏂ゆ嫹閿熸枻鎷� 閿熸彮绛规嫹涔嬮敓绐栭潻鎷烽敓鏂ゆ嫹閿熺煫纰夋嫹閿熸枻鎷�
	App app = new App();// app瀹為敓鏂ゆ嫹閿熸枻鎷�
	Visitor vor = new Visitor();// 閿熻娇鍖℃嫹閿熸枻鎷�
	public HttpServletRequest request = ServletActionContext.getRequest();// 閿熸枻鎷峰彇request
	private String Mac;//
	private String os;// 绯荤粺
	private String time = util.getNowTime();
	private String province = "all";// 鐪侀敓鏂ゆ嫹
	private int synlogin;// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
	private int downloaded;// 閿熸枻鎷烽敓鎴揪鎷烽敓鏂ゆ嫹
	private int usersyned;// 鍚屾椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	private int vored;// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹閿熸枻鎷�
	private int usersaved;// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	private String schoolname;// 瀛︽牎閿熸枻鎷烽敓鏂ゆ嫹
	private String address;// 瀛︽牎閿熸枻鎷峰潃
	private String scope;// 閿熸枻鎷烽敓鏂ゆ嫹鍥�
	private String property;// 妤奸敓鏂ゆ嫹
	private String content;//閿熸枻鎷烽敓鏂ゆ嫹
	private String img;//鍥剧墖
	private String title;//閿熸枻鎷烽敓鏂ゆ嫹
	HttpSession session = ServletActionContext.getRequest().getSession();
	/**
	 * 閫氶敓鏂ゆ嫹id鍒犻敓鏂ゆ嫹閿熸彮绛规嫹閿熸枻鎷烽敓鏂ゆ嫹
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
	 * 閫氶敓鏂ゆ嫹id閿熶粙鐪嬮敓鎻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	 */
	public String selectParentsLetterone() throws IOException {
		ParentsLetter p=audao.Plbyid(id);
		request.setAttribute("p", p);
		return Action.SUCCESS;
		
	}
	/**
	 * 閿熶粙鐪嬮敓鎻鎷烽敓鏂ゆ嫹閿熸枻鎷�
	 */
	public String selectParentsLetter() throws IOException {
		List<ParentsLetter> pl=audao.PlAll();
		request.setAttribute("pl", pl);
		return Action.SUCCESS;
		
	}
	/**
	 * 閿熸枻鎷峰彴 閿熸枻鎷疯浌椤甸敓鏂ゆ嫹閿熸枻鎷烽敓锟�
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
			mge.setStatement("鍥剧墖鎴栬�呮爣棰樹负绌�!");
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
		mge.setStatement("鎴愬姛!");
		mge.setContent("true");
		util.Out().print(util.ToJson(mge));
		
	}
	/**
	 * web 閿熸枻鎷疯浌椤甸敓鏂ゆ嫹閿熸枻鎷烽敓锟�
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
				util.Out().print("鍥剧墖閿熺杈炬嫹閿熸枻鎷疯閿熸枻鎷烽敓鏂ゆ嫹720*360 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓閾扮》鎷烽敓鏂ゆ嫹鎷㈤敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯閿燂拷");
				return ;
			}
			float fimg = util.fileSize(imgFile);
			if (fimg > 2048.00) {
				util.Out().print("閿熸枻鎷峰浘鐗囬敓鏂ゆ嫹瑕侀敓鏂ゆ嫹灏忎负 2MB 閿熸枻鎷烽敓閾帮綇鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熼摪纭锋嫹閿熸枻鎷锋嫝閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹瑁曢敓锟�");
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
	 * 閫氶敓鏂ゆ嫹id鍒犻敓鏂ゆ嫹閿熸彮绛规嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋伅
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
	 * 閫氶敓鏂ゆ嫹id閿熶粙鐪嬮敓鎻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭�
	 */
	public String selectjzfkid() throws IOException {
		Feedback f=audao.FeedbackByid(id);
		request.setAttribute("f", f);
		return Action.SUCCESS;
		
	}
	/**
	 * 閿熶粙鐪嬮敓鎻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭�
	 */
	public String selectjzfk() throws IOException {
		List<Feedback> fl=audao.FeedbackAll();
		request.setAttribute("fl", fl);
		return Action.SUCCESS;
		
	}
	/**
	 * web妯￠敓鏂ゆ嫹閿熸枻鎷疯瀛﹂敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭�
	 */
	public String selectwxHouseallzh() throws IOException {
		System.out.println("selectwxHouseallzhh");
		
		List<House> hl=audao.HouseZh(6);
		
		for(int i=0;i<hl.size();i++){
			House h=hl.get(i);
			h.setCount((h.getCount()+1));
			audao.Update(h);
			
		}
		System.out.println("閿熸枻鎷�:"+hl.size());
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
	 * web妯￠敓鏂ゆ嫹閿熸枻鎷疯瀛﹂敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭�
	 */
	public String selectwxHouseall() throws IOException {
		
		if(util.isEmpty(schoolname)){
			System.out.println("schoolname閿熻绌虹鎷�");
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
	 * app妯￠敓鏂ゆ嫹閿熸枻鎷疯瀛﹂敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭�
	 */
	public void appselectHouseall() throws IOException {
		System.out.println("schoolname閿熸枻鎷�:"+schoolname);	
		Message mge=new Message();
		if(util.isEmpty(schoolname)){
			mge.setCode(mge.NULL);
			mge.setStatement("null");
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
		mge.setStatement("Is Null");
		util.Out().print(util.ToJson(mge));
		
	}
	/**
	 * 閿熸枻鎷峰彴妯￠敓鏂ゆ嫹閿熸枻鎷疯瀛﹂敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭�
	 */
	public String selectHouseall() throws IOException {
		System.out.println("schoolname閿熸枻鎷�:"+schoolname);	
		if(util.isEmpty(schoolname)){
			System.out.println("schoolname閿熺Ц纰夋嫹");
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
	 * 瀛﹂敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鎭敓鐫潻鎷�
	 */
	public String upHouse() throws IOException {
		House house=audao.byid(id);
		System.out.println("閿熸枻鎷烽敓鏂ゆ嫹upHouse"+id);
		if(house!=null){
			house.setAddress(address);
			house.setProperty(property);
			house.setSchoolname(schoolname);
			house.setScope(scope);
			house.setTime(time);
			audao.Update(house);
			System.out.println("閿熺潾鏀规垚鐧告嫹");
			request.setAttribute("h", house);
			request.setAttribute("hh", "閿熺潾鏀规垚鐧告嫹!");
			return Action.SUCCESS;
		}
		System.out.println("涓洪敓鏂ゆ嫹!");
		return null;
	}
	/**
	 * 閿熶粙鐪嬪閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋伅
	 */
	public String selectHousenoe() throws IOException {
		House house=audao.byid(id);
		System.out.println("閿熸枻鎷烽敓鏂ゆ嫹selectHousenoe");
		if(house!=null){
			request.setAttribute("h", house);
			return Action.SUCCESS;
		}
		return null;
	}
	/**
	 * 閿熸枻鎷峰彴鍒犻敓鏂ゆ嫹瀛﹂敓鏂ゆ嫹閿熸枻鎷�
	 * @throws IOException 
	 */
	public void RemovetHouse() throws IOException {
		House house=audao.byid(id);
		System.out.println("閿熸枻鎷烽敓鏂ゆ嫹RemovetHouse");
		if(house!=null){
		audao.Remove(house);
		System.out.println("鍒犻敓鏂ゆ嫹閿熸枻鎷穐ouse");
		}
		((HttpServletResponse) util.response()).sendRedirect(request
				.getContextPath() + "/selectHouse?currentPage="+currentPage);
		
		

	}

	/**
	 * 閿熸枻鎷峰彴閿熸枻鎷烽〉閿熶粙鐪嬪閿熸枻鎷烽敓鏂ゆ嫹
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
	 * 閿熸枻鎷烽敓绐栄嶆嫹閿熸枻鎷烽敓锟�
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	public String Addschooldistrict() throws IOException, InvalidFormatException {
		System.out.println("閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓绐栄嶆嫹閿熸枻鎷烽敓锟�");
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			util.Out().print("娌￠敓鍙鎷烽敓鏂ゆ嫹");
			return null;
		}
		
		if(xlsxFile!=null){
			System.out.println("xlsxFileFileName閿熸枻鎷�:"+xlsxFileFileName);
			
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(xlsxFile);

			// 寰敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷稴heet
			for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				
				// 寰敓鏂ゆ嫹閿熸枻鎷稲ow
				for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow == null) {
						continue;
					}
					//閿熸枻鎷蜂竴閿熻娇璇ф嫹閿熸枻鎷� 閿熻鎲嬫嫹閿熸枻鎷�
					if(rowNum<1){
						//System.out.println("閿熷彨锝忔嫹"+xssfRow.getCell(0));
						System.out.println("瀛︽牎閿熸枻鎷烽敓鐙★綇鎷�"+xssfRow.getCell(1));
						System.out.println("瀛︽牎閿熸枻鎷峰潃閿熸枻鎷�"+xssfRow.getCell(2));
						System.out.println("瀛﹂敓鏂ゆ嫹閿熸枻鎷峰洿閿熸枻鎷�"+xssfRow.getCell(3));
						System.out.println("瀛﹂敓鏂ゆ嫹閿熸枻鎷峰洿閿熸枻鎷烽敓鏂ゆ嫹瑕侀敓琛楃鎷烽敓鏂ゆ嫹灏忛敓鏂ゆ嫹閿熸枻鎷锋ゼ閿熸暀鈽呮嫹閿熸枻鎷蜂綅閿熸枻鎷烽敓缁擄細"+xssfRow.getCell(4));
						System.out.println("閿熸枻鎷�"+numSheet+"閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹Sheet,閿熶茎纰夋嫹"+rowNum+"閿熸枻鎷�");
						}
					System.out.println("瀛︽牎閿熸枻鎷烽敓鐙★綇鎷�"+xssfRow.getCell(1));
					System.out.println("瀛︽牎閿熸枻鎷峰潃閿熸枻鎷�"+xssfRow.getCell(2));
					System.out.println("瀛﹂敓鏂ゆ嫹閿熸枻鎷峰洿閿熸枻鎷�"+xssfRow.getCell(3));
					System.out.println("瀛﹂敓鏂ゆ嫹閿熸枻鎷峰洿閿熸枻鎷烽敓鏂ゆ嫹瑕侀敓琛楃鎷烽敓鏂ゆ嫹灏忛敓鏂ゆ嫹閿熸枻鎷锋ゼ閿熸暀鈽呮嫹閿熸枻鎷蜂綅閿熸枻鎷烽敓缁擄細"+xssfRow.getCell(4));
					System.out.println("閿熸枻鎷�"+numSheet+"閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹Sheet,閿熶茎纰夋嫹"+rowNum+"閿熸枻鎷�");
					System.out.println("鍑嗛敓鏂ゆ嫹閿熸枻鎷烽敓锟�!");
					
					if(xssfRow.getCell(1)!=null){
						schoolname=xssfRow.getCell(1).toString();
						}
					if(schoolname==null){
						System.out.println("閿熺獤鎾呮嫹閿熸枻鎷峰焊閿燂拷");
						continue;
					}
					if(audao.byshoolname(schoolname)!=null){
						System.out.println("閿熺獤鎾呮嫹閿熸枻鎷峰焊閿燂拷");
						continue;
					}
					//閿熸枻鎷烽敓鎻亷鎷峰ú顫嫹閿熸枻鎷烽敓杈冿拷
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
			System.out.println("xlsx閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�");
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
		System.out.println("閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�");
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
	// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
	/**
	 * 閿熸枻鎷峰綍缁熼敓鐙″彉鍖� 閿熸枻鎷峰閿熸枻鎷�
	 */
	public void aStas() {
		System.out.println("province" + province);
		// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹
		time = util.getNumTime(0);

		// 閿熸枻鎷峰閿熸枻鎷�3閿熸枻鎷风郴缁熺粺閿熸枻鎷烽敓鏂ゆ嫹
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
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹
			usersaved = userdao.getSaveTime(time, sys, province).size();
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹
			vored = audao.VisitorTime(time, sys, province).size();
			System.out.println("11111111111111111" + vored + province + sys);
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹
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
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹
			// usersyned = userdao.getOnline(sys,province).size();
			if (sta == null) {
				sta = new Stas();
				sta.setProvince(province);// 閿熸枻鎷烽敓鏂ゆ嫹
				sta.setTime(time);// 缁熼敓鏂ゆ嫹鏃堕敓鏂ゆ嫹
				sta.setUserlogined(synlogin);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
				if (downloads != 0) {
					sta.setDownloaded(1);
				} else {
					sta.setDownloaded(0);
				}// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
				sta.setOs(sys);// 閿熸枻鎷烽敓鐨嗙鎷风郴缁�
				sta.setUsersyned(usersyned);// 鍚屾椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
				sta.setUsersaved(usersaved);// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
				sta.setVored(vored);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹閿熸枻鎷�

				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys, province);

			if (sta == null) {
				continue;
			}
			// sta.setProvince(province);//缁熼敓鏂ゆ嫹鍏ㄩ敓鏂ゆ嫹
			// sta.setTime(time);// 缁熼敓鏂ゆ嫹鏃堕敓鏂ゆ嫹

			sta.setUserlogined(synlogin);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
			if (downloaded != 0 && sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
			}
			// sta.setOs(os);//閿熸枻鎷烽敓鐨嗙鎷风郴缁�
			if (usersyned > sta.getUsersyned()) {
				sta.setUsersyned(usersyned);// 鍚屾椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
			}
			sta.setUsersaved(usersaved);// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹

			sta.setVored(vored);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹閿熸枻鎷�

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
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹
			usersaved = userdao.getSaveTime(time, sys).size();
			System.out.println("閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷�" + usersaved + sys);
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹
			vored = audao.VisitorTime(time, sys).size();
			System.out.println(vored + "vored" + sys + time);
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹
			synlogin = userdao.getFinaltime(time, sys).size();
			// 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熺煫浼欐嫹
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
				sta.setProvince(province);// 閿熸枻鎷烽敓鏂ゆ嫹
				sta.setTime(time);// 缁熼敓鏂ゆ嫹鏃堕敓鏂ゆ嫹
				sta.setUserlogined(synlogin);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
				if (downloaded != 0 && sys.equals(os)) {
					sta.setDownloaded(1);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
				} else {
					sta.setDownloaded(0);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
				}
				sta.setOs(sys);// 閿熸枻鎷烽敓鐨嗙鎷风郴缁�
				sta.setUsersyned(usersyned);// 鍚屾椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
				sta.setUsersaved(usersaved);// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
				sta.setVored(vored);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹閿熸枻鎷�
				audao.Save(sta);
				continue;
			}
			sta = audao.StasTimeDay(time, sys, "all");
			// sta.setProvince(province);//缁熼敓鏂ゆ嫹鍏ㄩ敓鏂ゆ嫹
			// sta.setTime(time);// 缁熼敓鏂ゆ嫹鏃堕敓鏂ゆ嫹

			sta.setUserlogined(synlogin);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
			if (downloaded != 0 && sys.equals(os)) {
				sta.setDownloaded(sta.getDownloaded() + 1);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
			}
			// sta.setOs(os);//閿熸枻鎷烽敓鐨嗙鎷风郴缁�

			sta.setUsersyned(usersyned);// 鍚屾椂閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�

			sta.setUsersaved(usersaved);// 閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹

			sta.setVored(vored);// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇鍖℃嫹閿熸枻鎷�

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
				OpeFunction.Out().print("FamilyGroup.apk涓簄ull");
				return;
			}
			if (!appFileFileName.equals("FamilyGroup.apk")) {
				OpeFunction.Out().print("閿熸枻鎷烽敓琛楁唻鎷烽敓鏂ゆ嫹涓�'FamilyGroup.apk'");
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

			// 杞敓鏂ゆ嫹
		
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
	 * 鍒犻敓鏂ゆ嫹app 閫氶敓鏂ゆ嫹id
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
	 * @throws IOException 
	 */
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
	 * 閫氶敓鏂ゆ嫹id閿熸枻鎷疯app閿熸枻鎷锋伅
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
	 * 閿熸枻鎷烽敓鎴潻鎷� 閿熼叺浼欐嫹閿熸枻鎷� 閿熸枻鎷�8閿熸枻鎷穉pp
	 */
	public void getapp() {
		try {
			// num閿熸枻鎷烽敓鏂ゆ嫹0 閿熸枻鎷烽敓鏂ゆ嫹榛橀敓鏂ゆ嫹鍊�
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
	 * 閿熸枻鎷烽敓鎴潻鎷� web閿熸枻鎷烽敓鏂ゆ嫹 閿熸枻鎷穘閿熸枻鎷穉pp
	 */
	public String webGetapp() {
		try {
			// num閿熸枻鎷烽敓鏂ゆ嫹0 閿熸枻鎷烽敓鏂ゆ嫹榛橀敓鏂ゆ嫹鍊�
			if (num == 0) {
				num = 8;
			}
			// 閿熸枻鎷疯app閿熸枻鎷锋伅
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