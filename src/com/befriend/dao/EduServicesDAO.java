package com.befriend.dao;

import java.util.List;
import java.util.Map;

import com.befriend.entity.EduServices;


/**
 * @author STerOTto
 *
 */
public interface EduServicesDAO 
{
	public EduServices find(int id);
	
	public List<EduServices> find(Map<String,String> map,int currentPage,int pageSize);

}
