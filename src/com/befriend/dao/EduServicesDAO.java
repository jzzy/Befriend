package com.befriend.dao;

import java.util.List;
import java.util.Map;

import com.befriend.entity.Attention;
import com.befriend.entity.EduServices;


/**
 * @author STerOTto
 *
 */
public interface EduServicesDAO 
{
	public EduServices find(int id);
	public EduServices findMerchantId(int merchantId);
	public List<EduServices> find(Map<String,String> map,int currentPage,int pageSize);
	public List<EduServices> findLike(Map<String,String> map,String value,int currentPage,int pageSize);
	
	public void save(Attention Attention);
	public void remove(Attention Attention);
	public void update(Attention Attention);
	public Attention byId(int attentionId);
	public Attention checkAttention(int userid,int objectid,int come);
	public List<Attention> byUserid(int userid,int currentPage,int pageSize,int come);
	public int eduServicesCount(int eduServicesId,int come);
}
