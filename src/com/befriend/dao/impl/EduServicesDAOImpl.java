package com.befriend.dao.impl;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.befriend.dao.EduServicesDAO;
import com.befriend.entity.Attention;
import com.befriend.entity.EduServices;
/**
 * 
 * @author STerOTto
 *
 */
@Transactional
public class EduServicesDAOImpl implements EduServicesDAO
{
	@PersistenceContext
	private EntityManager entityManger;

	@Override
	public EduServices find(int id) {
		
		return entityManger.find(EduServices.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EduServices> find(Map<String,String> map,int currentPage,int pageSize) {
		String sql = "select u from EduServices u ";
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

	@Override
	public void save(Attention Attention) {
		// TODO Auto-generated method stub
		entityManger.persist(Attention);
		
	}

	@Override
	public void remove(Attention Attention) {
		// TODO Auto-generated method stub
		entityManger.remove(Attention);
		
	}

	@Override
	public void update(Attention Attention) {
		// TODO Auto-generated method stub
		entityManger.merge(Attention);
		
	}

	@Override
	public Attention byId(int attentionId) {
		// TODO Auto-generated method stub
		return entityManger.find(Attention.class, attentionId);
	}

	@Override
	public List<Attention> byUserid(int userid, int currentPage, int pageSize,int come) {
		// TODO Auto-generated method stub
		Query query = entityManger.createQuery("select u from Attention u where u.userid=:userid and u.come=:come  order"
			      + " by u.time desc");
		query.setParameter("userid", userid);
		query.setParameter("come", come);
		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public int eduServicesCount(int eduServicesId,int come) {
		Query query = entityManger.createQuery("select u from Attention u where u.objectid=:eduServicesId  and u.come=:come  order"
			      + " by u.time desc");
		query.setParameter("eduServicesId", eduServicesId);
		query.setParameter("come", come);
		return (int)(long)query.getSingleResult();
	}

	@Override
	public EduServices findMerchantId(int merchantId) {
		String sql = "select u from EduServices u where u.merchantId=:merchantId";
		Query query = entityManger.createQuery(sql);
		query.setMaxResults(1);
		query.setParameter("merchantId", merchantId);
		List<EduServices> edusl=query.getResultList();
		if(edusl.size()>0)
			return edusl.get(0);
		return null;
	}

}
