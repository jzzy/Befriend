package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.EduCommentDAO;
import com.befriend.entity.EduComment;

@Transactional
public class EduCommentDAOImpl implements EduCommentDAO
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EduComment find(int id)
	{
		return entityManager.find(EduComment.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EduComment> find(String merchantId, int fatherId, int currentPage, int pageSize)
	{
		Query query  = entityManager.createNamedQuery("select u from EduComment u where u.merchantId = :merchantId and u.fatherId = :fatherId order by u.time desc");
		query.setParameter("merchantId", merchantId);
		query.setParameter("fatherId", fatherId);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

}
