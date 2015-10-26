package com.befriend.dao;

import java.util.List;
import java.util.Map;

import com.befriend.entity.EduActivity;

/**
 * @author STerOTto
 *
 */
public interface EduActivityDAO
{
	public EduActivity find(int id);
	
	public List<EduActivity> find(Map<String,String> map,int currentPage,int pageSize);

}
