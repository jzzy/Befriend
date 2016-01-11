package com.befriend.dao;

import java.util.List;

import com.befriend.entity.EduComment;

public interface EduCommentDAO
{
	public void save(EduComment e);
	public void update(EduComment e);
	public void delete(EduComment e);
	public EduComment find(int id);
	public List<EduComment> find(String merchantId,int fatherId,int currentPage,int pageSize);
	public List<EduComment> find(String merchantId);
	
	public List<EduComment> find(int currentPage,int pageSize);
	public List<EduComment> find(String merchantId,int currentPage,int pageSize);
	public List<EduComment> likeFind(String string,int currentPage,int pageSize);
}
