package com.befriend.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.befriend.dao.EduServicesDAO;
import com.befriend.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class EduServicesAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	
	private EduServicesDAO eduServicesDAO;
	
	private HttpServletResponse response;
	
	private String province;
	private String city;
	private String county;
	private String merchantId;
	private String classFirst;
	private String classSecond;
	private String address;
	private int currentPage = 1;
	private int pageSize = 10;
	
	public void getEdu() throws IOException
	{
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Map<String,String> map = new HashMap<String, String>();
		
		if(!StringUtils.isEmpty(merchantId))
		{
			map.put("merchantId", merchantId);
		}
		
		if(!StringUtils.isEmpty(province))
		{
			map.put("province", province);
		}
		
		if(!StringUtils.isEmpty(county))
		{
			map.put("county", county);
		}
		
		if(!StringUtils.isEmpty(city))
		{
			map.put("city", city);
		}
		
		if(!StringUtils.isEmpty(classFirst))
		{
			map.put("classFirst", classFirst);
		}
		
		if(!StringUtils.isEmpty(classSecond))
		{
			map.put("classSecond", classSecond);
		}
		
		if(!StringUtils.isEmpty(address))
		{
			map.put("address", address);
		}
		
		response.getWriter().print(JsonUtil.toJson(eduServicesDAO.find(map, currentPage, pageSize)));
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
	
	

}
