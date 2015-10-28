package com.befriend.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.struts2.ServletActionContext;

import com.befriend.dao.EduCommentDAO;
import com.befriend.entity.EduComment;
import com.befriend.util.JsonUtil;

public class EduCommentAction
{
	private EduCommentDAO eduCommentDAO;
	private HttpServletResponse response;
	
	private String merchantId;
	private String userId;
	private String content;
	
	
	private int currentPage		= 1;
	private int pageSize 		= 10;
	
	public void getCommments() throws IOException
	{
		List<EduComment> eduComments = eduCommentDAO.find(merchantId, EduComment.ROOT, currentPage, pageSize);
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(JsonUtil.toJsonExpose(eduComments));
	}
	
	private void addComments()
	{
		
	}

}
