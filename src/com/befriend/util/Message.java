package com.befriend.util;

import java.io.Serializable;


/**
 * 消息实体类
 * @author STerOTto
 *
 */
public class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final Integer SUCCESS = 1;//成功
	public static final Integer FAILED = 0;//错误
	public static final Integer NULL = -1;//空
	public static final Integer ERROR = -2;//异常
	
	private Integer code;//状态吗
	private String statement;//备注
	private Object content;//请求内容
	
	public Integer getCode()
	{
		return code;
	}
	public void setCode(Integer code)
	{
		this.code = code;
	}
	public String getStatement()
	{
		return statement;
	}
	public void setStatement(String statement)
	{
		this.statement = statement;
	}
	public Object getContent()
	{
		return content;
	}
	public void setContent(Object content)
	{
		this.content = content;
	}
	

}
