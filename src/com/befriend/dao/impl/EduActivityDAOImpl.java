package com.befriend.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.EduActivityDAO;
import com.befriend.entity.EduActivity;

@Transactional
public class EduActivityDAOImpl implements EduActivityDAO
{
	
	@PersistenceContext
	private EntityManager entityManger;

	@Override
	public EduActivity find(int id)
	{
		return entityManger.find(EduActivity.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EduActivity> find(Map<String, String> map, int currentPage, int pageSize)
	{
		String sql = "select u from EduActivity u ";
		if(map.size()>0)
		{
			sql += "where ";
			int i = 0;
			for(Entry<String, String> entry: map.entrySet())
			{
				if(i == 0)
				{
					sql += "u." + entry.getKey() + " like '%" +entry.getValue() +"%' ";
				}
				else
				{
					sql += "and u." + entry.getKey() + " like '%" +entry.getValue() + "%' ";
				}
				i++;
			}
		}
		Query query = entityManger.createQuery(sql);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

}
