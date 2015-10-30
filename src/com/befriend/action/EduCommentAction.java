package com.befriend.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.befriend.dao.EduCommentDAO;
import com.befriend.dao.UserDAO;
import com.befriend.entity.EduComment;
import com.befriend.entity.User;
import com.befriend.util.JsonUtil;

public class EduCommentAction
{
	private EduCommentDAO 		eduCommentDAO;
	private UserDAO				userDAO;
	private HttpServletResponse response;
	
	private String 	merchantId;
	private int 	score		=0;
	private String 	userId;
	private String 	replyId;
	private String 	content;
	private String 	fatherId;
	
	private File [] 	pictures;
	private String [] 	picturesFileName;
	private String [] 	picturesContentType;
	
	
	private int currentPage		= 1;
	private int pageSize 		= 10;
	
	/**
	 * @describe get merchant comments
	 * @param merchantId necessary
	 * @param fatherId choice
	 * @param currentPage choice
	 * @param pageSize choice
	 * @throws IOException
	 */
	public void getCommments() throws IOException
	{
		List<EduComment> eduComments = null;
		if(!StringUtils.isEmpty(fatherId) && StringUtils.isNumeric(fatherId))
		{
			eduComments = eduCommentDAO.find(merchantId, Integer.valueOf(fatherId), currentPage, pageSize);
		}
		else
		{
			eduComments = eduCommentDAO.find(merchantId, EduComment.ROOT, currentPage, pageSize);
		}
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(JsonUtil.toJsonExpose(eduComments));
	}
	
	/**
	 * @describe add comment
	 * @param userId necessary
	 * @param merchantId necessary
	 * @param pictures choice
	 * @param content choice
	 * @param score choice
	 * @param fatherId choice
	 * @throws IOException
	 */
	public void addComments() throws IOException
	{
		response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		boolean result = false;
		if(!StringUtils.isEmpty(userId)&&StringUtils.isNumeric(userId)
				&& !StringUtils.isEmpty(merchantId))
		{
			User user = userDAO.byid(Integer.valueOf(userId));
			if(user != null)
			{
				EduComment eduComment = new EduComment();
				
				String picStr = "";
				if(pictures != null && pictures.length >0)
				{
					String path = File.separator + "file" + File.separator + user.getId() + File.separator 
								+ "EduComment" + File.separator + merchantId + File.separator;
					String relPath = ServletActionContext.getServletContext().getRealPath(path);
					File saveDir = new File(relPath);
					if(!saveDir.exists())
					{
						saveDir.mkdirs();
					}
					
					for ( int i=0; i<pictures.length; i++ )
					{
						try
						{
							File saveFile = new File(saveDir, picturesFileName[i]);
							FileUtils.copyFile(pictures[i], saveFile);
							picStr += path + picturesFileName[i] + "!#";
						}
						catch ( IOException e )
						{
							e.printStackTrace();
						}
					}
					
				}
				
				eduComment.setMerchantId(merchantId);
				eduComment.setUser(user);
				eduComment.setScore(score);
				eduComment.setContent(content);
				eduComment.setPictures(picStr);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				eduComment.setTime(sdf.format(date));
				if(!StringUtils.isEmpty(fatherId) && StringUtils.isNumeric(fatherId))
				{
					eduComment.setFatherId(Integer.valueOf(fatherId));
				}
				if(!StringUtils.isEmpty(replyId)&&StringUtils.isNumeric(replyId))
				{
					User reply = userDAO.byid(Integer.valueOf(replyId));
					eduComment.setReply(reply);
				}
				eduCommentDAO.save(eduComment);
				result=true;
			}
			else
			{
				result = false;
			}
		}
		else
		{
			result = false;
		}
		response.getWriter().println(result);
	}
	
	
	public EduCommentAction(EduCommentDAO eduCommentDAO, UserDAO userDAO)
	{
		super();
		this.eduCommentDAO = eduCommentDAO;
		this.userDAO = userDAO;
	}
	public String getMerchantId()
	{
		return merchantId;
	}
	public void setMerchantId(String merchantId)
	{
		this.merchantId = merchantId;
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getFatherId()
	{
		return fatherId;
	}
	public void setFatherId(String fatherId)
	{
		this.fatherId = fatherId;
	}
	public File [] getPictures()
	{
		return pictures;
	}
	public void setPictures(File [] pictures)
	{
		this.pictures = pictures;
	}
	public String [] getPicturesFileName()
	{
		return picturesFileName;
	}
	public void setPicturesFileName(String [] picturesFileName)
	{
		this.picturesFileName = picturesFileName;
	}
	public String [] getPicturesContentType()
	{
		return picturesContentType;
	}
	public void setPicturesContentType(String [] picturesContentType)
	{
		this.picturesContentType = picturesContentType;
	}
	public int getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public String getReplyId()
	{
		return replyId;
	}

	public void setReplyId(String replyId)
	{
		this.replyId = replyId;
	}
	
}
