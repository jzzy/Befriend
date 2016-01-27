package com.befriend.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.befriend.dao.EduServicesDAO;
import com.befriend.entity.Attention;
import com.befriend.entity.EduServices;
import com.befriend.util.JsonUtil;
import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class EduServicesAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private EduServicesDAO eduServicesDAO;

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

	public void saveEduAttention() throws IOException {
		System.out.println("userid " + userid + " objectid " + objectid);
		try {
			attention.setCome(Attention.COME_EduServices);
			attention.setTime(OpeFunction.getNowTime());
			attention.setUserid(userid);
			attention.setObjectid(objectid);
			eduServicesDAO.save(attention);
			OpeFunction.Out().print(true);
		} catch (Exception e) {
			OpeFunction.Out().print(false);
		}

	}

	public String getEduWeb() throws IOException {
		try {

			Map<String, String> map = new HashMap<String, String>();

//			if (!OpeFunction.isEmpty(merchantId)) {
//				map.put("merchantId", merchantId);
//			}

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
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("EduServices",
					eduServicesDAO.find(map, currentPage, pageSize));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}
	public String getEduWebAjax() throws IOException {
		try {

			Map<String, String> map = new HashMap<String, String>();

//			if (!OpeFunction.isEmpty(merchantId)) {
//				map.put("merchantId", merchantId);
//			}
			
			if (!OpeFunction.isEmpty(province)) {
				map.put("province", province);
				System.out.println("province"+province);
			}

			if (!OpeFunction.isEmpty(county)) {
				map.put("county", county);
				System.out.println("county"+county);
			}

			if (!OpeFunction.isEmpty(city)) {
				map.put("city", city);
				System.out.println("city"+city);
			}

			if (!OpeFunction.isEmpty(classFirst)) {
				map.put("classFirst", classFirst);
				System.out.println("classFirst"+classFirst);
			}

			if (!OpeFunction.isEmpty(classSecond)) {
				map.put("classSecond", classSecond);
				System.out.println("classSecond"+classSecond);
			}

			if (!OpeFunction.isEmpty(address)) {
				map.put("address", address);
				System.out.println("address"+address);
			}
			request.setAttribute("EduServices",
					eduServicesDAO.find(map, currentPage, pageSize));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}
	public String getLikeEduWeb() throws IOException {
		try {
			System.out.println("value"+value);

			request.setAttribute("EduServices",
					eduServicesDAO.findLike(value, currentPage, pageSize));
			request.setAttribute("value",value);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;

	}
	public String getLikeEduWebAjax() throws IOException {
		try {
			System.out.println("value"+value);
			System.out.println("currentPage"+currentPage);
			List<EduServices> edusl=eduServicesDAO.findLike(value, currentPage, pageSize);
			if(edusl.size()==0){
				System.out.println("没有更多了！");
				return null;
			}
			request.setAttribute("EduServices",
					edusl);
			request.setAttribute("value",value);
			
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

}
