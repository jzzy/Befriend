package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.EduCommentDAO;
import com.befriend.entity.EduComment;
import com.befriend.entity.User;
@SuppressWarnings("all")
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
		Query query  = entityManager.createQuery("select u from EduComment u where u.merchantId = :merchantId and u.fatherId = :fatherId order by u.time desc");
		query.setParameter("merchantId", merchantId);
		query.setParameter("fatherId", fatherId);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public void save(EduComment e)
	{
		entityManager.persist(e);
	}

	@Override
	public void update(EduComment e)
	{
		entityManager.merge(e);
	}

	@Override
	public void delete(EduComment e)
	{
		entityManager.remove(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EduComment> find(String merchantId)
	{
		Query query  = entityManager.createQuery("select u from EduComment u where u.merchantId = :merchantId and u.fatherId != 0 order by u.time desc");
		query.setParameter("merchantId", merchantId);
		return query.getResultList();
	}

	@Override
	public List<EduComment> likeFind(String string, int currentPage,
			int pageSize) {
		Query query  = entityManager.createQuery("select u from EduComment u where u.content like :string order by u.time desc");
		
		query.setParameter("string",""+ "%"+string+"%");

		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<EduComment> find(int currentPage, int pageSize) {
		Query query  = entityManager.createQuery("select u from EduComment u  order by u.time desc");
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<EduComment> find(String merchantId, int currentPage,
			int pageSize) {
		Query query  = entityManager.createQuery("select u from EduComment u where u.merchantId = :merchantId order by u.time desc");
		query.setParameter("merchantId", merchantId);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<EduComment> myComments(User user, int currentPage, int pageSize) {
		Query query  = entityManager.createQuery("select u from EduComment u where u.user = :user order by u.time desc");
		query.setParameter("user", user);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

}
