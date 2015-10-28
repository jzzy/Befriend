package com.befriend.dao;

import java.util.List;

import com.befriend.entity.EduComment;

public interface EduCommentDAO
{
	public EduComment find(int id);
	public List<EduComment> find(String merchantId,int fatherId,int currentPage,int pageSize);
}
