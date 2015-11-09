package com.befriend.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	
	private File[] 		fileList;
	private String []	fileListFileName;
	private String [] 	fileListContentType;
	
	public void upload() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String path = ServletActionContext.getServletContext().getRealPath("/")+"/file/upload/" ;
		File dir = new File(path);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		if(fileList != null && fileList.length>0)
		{
			for ( int i=0 ; i<fileList.length ; i++ )
			{
				try
				{
					File saveFile = new File(path+fileListFileName[i]);
					FileUtils.copyFile(fileList[i], saveFile);
					response.getWriter().println(ServletActionContext.getServletContext().getContextPath()+"/file/upload/"+fileListFileName[i]);
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
			}
		}
	}

	public File [] getFileList()
	{
		return fileList;
	}

	public void setFileList(File [] fileList)
	{
		this.fileList = fileList;
	}

	public String [] getFileListFileName()
	{
		return fileListFileName;
	}

	public void setFileListFileName(String [] fileListFileName)
	{
		this.fileListFileName = fileListFileName;
	}

	public String [] getFileListContentType()
	{
		return fileListContentType;
	}

	public void setFileListContentType(String [] fileListContentType)
	{
		this.fileListContentType = fileListContentType;
	}

	
	
	
	

}