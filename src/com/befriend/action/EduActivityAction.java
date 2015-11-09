package com.befriend.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author STerOTto
 *
 */
@SuppressWarnings("all")
public class EduActivityAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	
	
	private String province;
	private String city;
	private String county;
	private String merchantId;
	private String classFirst;
	private String classSecond;
	private String address;
	private int currentPage = 1;
	private int pageSize = 10;

}
