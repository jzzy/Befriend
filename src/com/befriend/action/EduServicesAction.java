package com.befriend.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.befriend.dao.EduServicesDAO;
import com.befriend.entity.Attention;
import com.befriend.entity.EduServices;
import com.befriend.entity.User;
import com.befriend.util.JsonUtil;
import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class EduServicesAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private EduServicesDAO eduServicesDAO;
	private HttpSession session = ServletActionContext.getRequest()
			.getSession();
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String province;
	private String city;
	private String county;
	private String merchantId;
	private String classFirst;
	private String classSecond;
	private String address;
	private String value;
	private int currentPage = 1;
	private int pageSize = 10;
	private Integer userid = null;
	private Integer objectid = null;
	private Integer attentionId = null;
	Attention attention = new Attention();
	List<Attention> la = new ArrayList<Attention>();
	List<EduServices> ls = new ArrayList<EduServices>();
	double lng = 116.483917;// 经度
	double lat = 39.920533;// 纬度
	String type="美食";//类别
	public String GaoDeFujin() throws IOException {

		try {
			
			String str = WechatKit
					.sendGet("http://restapi.amap.com/v3/place/around?key=5deb2485b0b9546999783b2fab8ff625"
							+ "&location="+lng+","+lat
							+ "&keywords="+type
							+ "&types="
							+ "&offset="+pageSize
							+ "&page="+currentPage
							+ "&extensions=all");
			JSONObject jsonObject = new JSONObject(str);

			JSONArray jsonArray = jsonObject.getJSONArray("pois");
			request.setAttribute("jsonArray", jsonArray);
			request.setAttribute("type", type);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				System.out.println(jo.getString("name"));
				System.out.println(jo.getString("type"));
				System.out.println(jo.getString("location"));
				System.out.println(jo.getString("tel"));

			}
			return Action.SUCCESS;
		} catch (Exception e) {

		}
		return Action.ERROR;
	}

	public void userLookEduAttention() throws IOException {
		try {
			la = eduServicesDAO.byUserid(userid, currentPage, pageSize,
					Attention.COME_EduServices);
			for (int i = 0; i < la.size(); i++) {
				ls.add(eduServicesDAO.findMerchantId(la.get(i).getObjectid()));
			}
			String result = "{\"la\":" + OpeFunction.ToJson(la) + ",\"ls\":"
					+ OpeFunction.ToJson(ls) + "}";
			OpeFunction.Out().print(result);
		} catch (Exception e) {
			OpeFunction.Out().print(false);
		}
	}

	public void removeEduAttention() throws IOException {
		try {
			attention = eduServicesDAO.byId(attentionId);
			eduServicesDAO.remove(attention);
			OpeFunction.Out().print(true);
		} catch (Exception e) {
			OpeFunction.Out().print(false);
		}
	}

	public void removeEduAttentionWeb() throws IOException {

		try {
			User u = (User) session.getAttribute("u");
			if (u == null) {

				System.out.println("请重新登入!");
				((HttpServletResponse) OpeFunction.response())
						.sendRedirect(request.getContextPath()
								+ "/SimulationApp/login.html");

				return;
			}
			attention = eduServicesDAO.byId(attentionId);
			eduServicesDAO.remove(attention);
			System.out.println("教辅机构删除成功！id=" + attentionId);
			OpeFunction.Out().print(true);
		} catch (Exception e) {
			OpeFunction.Out().print(false);
		}
	}

	public void saveEduAttention() throws IOException {
		System.out.println("userid " + userid + " objectid " + objectid);
		try {

			attention.setCome(Attention.COME_EduServices);
			attention.setTime(OpeFunction.getNowTime());
			attention.setUserid(userid);
			attention.setObjectid(objectid);
			if (eduServicesDAO.checkAttention(userid, objectid,
					Attention.COME_EduServices) == null) {
				eduServicesDAO.save(attention);
				OpeFunction.Out().print(true);
			} else {
				OpeFunction.Out().print(false);
			}

		} catch (Exception e) {
			OpeFunction.Out().print(false);
		}

	}

	public String daohang() throws IOException {
		request.setAttribute("lng", lng);
		request.setAttribute("lat", lat);
		return Action.SUCCESS;

	}

	public String getEduWeb() throws IOException {
		try {

			Map<String, String> map = new HashMap<String, String>();

			// if (!OpeFunction.isEmpty(merchantId)) {
			// map.put("merchantId", merchantId);
			// }
			// if(lng==116.483917||lat==39.920533){
			// ((HttpServletResponse)
			// OpeFunction.response()).sendRedirect(request
			// .getContextPath() + "/SimulationApp/lihu/GPS/baiduGPS.html");
			// }

			System.out.println("lng:" + lng + "lat:" + lat);
			session.setAttribute("lng", lng);
			session.setAttribute("lat", lat);
			if (!OpeFunction.isEmpty(province)) {
				map.put("province", province);
				request.setAttribute("province", province);
			}

			if (!OpeFunction.isEmpty(county)) {
				map.put("county", county);
				request.setAttribute("county", county);
			}

			if (!OpeFunction.isEmpty(city)) {
				map.put("city", city);
				request.setAttribute("city", city);
			}

			if (!OpeFunction.isEmpty(classFirst)) {
				map.put("classFirst", classFirst);
				request.setAttribute("classFirst", classFirst);
			}

			if (!OpeFunction.isEmpty(classSecond)) {
				map.put("classSecond", classSecond);
				request.setAttribute("classSecond", classSecond);
			}

			if (!OpeFunction.isEmpty(address)) {
				map.put("address", address);
				request.setAttribute("address", address);
			}
			List<EduServices> edl = eduServicesDAO.find(map, currentPage,
					pageSize);
			for (int i = 0; i < edl.size(); i++) {

				EduServices edu = edl.get(i);
				edu.setDistance(OpeFunction.Distance(lng, lat,
						edu.getLongitude(), edu.getLatitude()));
				System.out.println("距离:"
						+ OpeFunction.Distance(lng, lat, edu.getLongitude(),
								edu.getLatitude()));
				edl.set(i, edu);
			}
			edl.sort(new eduServicesSprt());
			for (int i = 0; i < edl.size(); i++) {

				System.out.println("距离2:" + edl.get(i).getDistance());

			}
			request.setAttribute("EduServices", edl);
			request.setAttribute("currentPage", currentPage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}

	@SuppressWarnings("all")
	private class eduServicesSprt implements Comparator<EduServices> {
		// 排序最近的在前
		public int compare(EduServices first, EduServices second) {
			if (first.getDistance() > second.getDistance())
				return 1;
			else if (first.getDistance() < second.getDistance())
				return -1;
			else
				return 0;
		}

	}

	public String getEduWebAjax() throws IOException {
		try {
			Map<String, String> map = new HashMap<String, String>();

			// if (!OpeFunction.isEmpty(merchantId)) {
			// map.put("merchantId", merchantId);
			// }

			if (!OpeFunction.isEmpty(province)) {
				map.put("province", province);
				System.out.println("province" + province);
			}

			if (!OpeFunction.isEmpty(county)) {
				map.put("county", county);
				System.out.println("county" + county);
			}

			if (!OpeFunction.isEmpty(city)) {
				map.put("city", city);
				System.out.println("city" + city);
			}

			if (!OpeFunction.isEmpty(classFirst)) {
				map.put("classFirst", classFirst);
				System.out.println("classFirst" + classFirst);
			}

			if (!OpeFunction.isEmpty(classSecond)) {
				map.put("classSecond", classSecond);
				System.out.println("classSecond" + classSecond);
			}

			if (!OpeFunction.isEmpty(address)) {
				map.put("address", address);
				System.out.println("address" + address);
			}
			List<EduServices> edl = eduServicesDAO.find(map, currentPage,
					pageSize);
			for (int i = 0; i < edl.size(); i++) {

				EduServices edu = edl.get(i);
				edu.setDistance(OpeFunction.Distance(lng, lat,
						edu.getLongitude(), edu.getLatitude()));
				System.out.println("排序前:"
						+ OpeFunction.Distance(lng, lat, edu.getLongitude(),
								edu.getLatitude()));
				edl.set(i, edu);
			}
			edl.sort(new eduServicesSprt());
			for (int i = 0; i < edl.size(); i++) {

				System.out.println("排序后:" + edl.get(i).getDistance());

			}
			request.setAttribute("EduServices", edl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}

	public String getLikeEduWeb() throws IOException {
		try {
			System.out.println("value" + value);
			List<EduServices> edl = eduServicesDAO.findLike(value, currentPage,
					pageSize);
			for (int i = 0; i < edl.size(); i++) {

				EduServices edu = edl.get(i);
				edu.setDistance(OpeFunction.Distance(lng, lat,
						edu.getLongitude(), edu.getLatitude()));
				System.out.println("排序前:"
						+ OpeFunction.Distance(lng, lat, edu.getLongitude(),
								edu.getLatitude()));
				edl.set(i, edu);
			}
			edl.sort(new eduServicesSprt());
			for (int i = 0; i < edl.size(); i++) {

				System.out.println("排序后:" + edl.get(i).getDistance());

			}
			request.setAttribute("EduServices", edl);
			request.setAttribute("value", value);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}

	public String getLikeEduWebAjax() throws IOException {
		try {
			System.out.println("value" + value);
			System.out.println("currentPage" + currentPage);
			List<EduServices> edl = eduServicesDAO.findLike(value, currentPage,
					pageSize);
			for (int i = 0; i < edl.size(); i++) {

				EduServices edu = edl.get(i);
				edu.setDistance(OpeFunction.Distance(lng, lat,
						edu.getLongitude(), edu.getLatitude()));
				System.out.println("排序前:"
						+ OpeFunction.Distance(lng, lat, edu.getLongitude(),
								edu.getLatitude()));
				edl.set(i, edu);
			}
			edl.sort(new eduServicesSprt());
			for (int i = 0; i < edl.size(); i++) {

				System.out.println("排序后:" + edl.get(i).getDistance());

			}
			request.setAttribute("EduServices", edl);
			if (edl.size() == 0) {
				System.out.println("没有更多了！");
				return null;
			}
			request.setAttribute("EduServices", edl);
			request.setAttribute("value", value);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}

	public void getEdu() throws IOException {
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Map<String, String> map = new HashMap<String, String>();

		if (!OpeFunction.isEmpty(merchantId)) {
			map.put("merchantId", merchantId);
		}

		if (!OpeFunction.isEmpty(province)) {
			map.put("province", province);
		}

		if (!OpeFunction.isEmpty(county)) {
			map.put("county", county);
		}

		if (!OpeFunction.isEmpty(city)) {
			map.put("city", city);
		}

		if (!OpeFunction.isEmpty(classFirst)) {
			map.put("classFirst", classFirst);
		}

		if (!OpeFunction.isEmpty(classSecond)) {
			map.put("classSecond", classSecond);
		}

		if (!OpeFunction.isEmpty(address)) {
			map.put("address", address);
		}

		response.getWriter()
				.print(JsonUtil.toJson(eduServicesDAO.find(map, currentPage,
						pageSize)));
	}

	public EduServicesAction(EduServicesDAO eduServicesDAO) {
		super();
		this.eduServicesDAO = eduServicesDAO;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
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

	public EduServicesDAO getEduServicesDAO() {
		return eduServicesDAO;
	}

	public void setEduServicesDAO(EduServicesDAO eduServicesDAO) {
		this.eduServicesDAO = eduServicesDAO;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getClassFirst() {
		return classFirst;
	}

	public void setClassFirst(String classFirst) {
		this.classFirst = classFirst;
	}

	public String getClassSecond() {
		return classSecond;
	}

	public void setClassSecond(String classSecond) {
		this.classSecond = classSecond;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getObjectid() {
		return objectid;
	}

	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}

	public Integer getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(Integer attentionId) {
		this.attentionId = attentionId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
