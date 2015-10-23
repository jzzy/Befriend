package com.befriend.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.befriend.dao.ApputilDAO;
import com.befriend.entity.Admin;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
import com.befriend.entity.House;
import com.befriend.entity.ParentsLetter;
import com.befriend.entity.SetIp;
import com.befriend.entity.Stas;
import com.befriend.entity.Visitor;
@SuppressWarnings("all")
@Transactional
public class ApputilDAOImpl implements ApputilDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AppUp UP() {
		Query query = entityManager.createQuery("select u from AppUp u order"
				+ " by u.time desc");
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<AppUp> AppUp = query.getResultList();
		if (AppUp.size() > 0)
			return AppUp.get(0);
		return null;
	}

	@Override
	public void Save(Feedback f) {
		entityManager.persist(f);

	}

	@Override
	public List<Feedback> FeedbackAll() {
		Query query = entityManager.createQuery("select u from Feedback u "
				+ "order by u.time desc ");

		return query.getResultList();

	}

	@Override
	public void Save(AppUp aup) {
		entityManager.persist(aup);

	}

	@Override
	public AppUp select(String Path) {
		Query query = entityManager
				.createQuery("select u from AppUp u where u.path=:Path");
		// query.setMaxResults(1);
		query.setParameter("Path", Path);
		@SuppressWarnings("unchecked")
		List<AppUp> AppUp = query.getResultList();
		if (AppUp.size() > 0)
			return AppUp.get(0);
		return null;
	}

	@Override
	public void Remove(AppUp aup) {
		entityManager.remove(aup);

	}

	@Override
	public Admin admin(String admin, String pwd) {
		Query query = entityManager
				.createQuery("select a from Admin a where a.admin=:admin and a.pwd=:pwd");
		query.setParameter("admin", admin);
		query.setParameter("pwd", pwd);
		List<Admin> ad = query.getResultList();
		if (ad.size() > 0)
			return ad.get(0);
		return null;
	}

	@Override
	public AppUp appbyid(int id) {
		Query query = entityManager
				.createQuery("select u from AppUp u where u.id=:id");
		// query.setMaxResults(1);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<AppUp> AppUp = query.getResultList();
		if (AppUp.size() > 0)
			return AppUp.get(0);
		return null;
	}

	@Override
	public void Update(AppUp aup) {
		entityManager.merge(aup);

	}

	@Override
	public void Save(Visitor vor) {
		entityManager.persist(vor);

	}

	@Override
	public void Update(Visitor vor) {
		entityManager.merge(vor);

	}

	@Override
	public Visitor sVisitor(String mac) {
		Query query = entityManager
				.createQuery("select u from Visitor u where u.appmac=:mac");
		// query.setMaxResults(1);
		query.setParameter("mac", mac);
		@SuppressWarnings("unchecked")
		List<Visitor> Visitor = query.getResultList();
		if (Visitor.size() > 0)
			return Visitor.get(0);
		return null;
	}

	@Override
	public void Save(Stas sta) {
		entityManager.persist(sta);

	}

	@Override
	public void Update(Stas sta) {
		entityManager.merge(sta);

	}

	@Override
	public List<Stas> StasTime(String province,String os) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.os=:os and u.province=:province  order by u.time desc,u.province desc,u.os desc");
		query.setParameter("os", os);
		query.setParameter("province", province);
		query.setMaxResults(7);
		
		return query.getResultList();

	}

	@Override
	public Stas StasTimeDay(String time,String os) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.time LIKE :time and u.os=:os");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		if (query.getResultList().size() > 0)

			return (Stas) query.getResultList().get(0);

		return null;

	}


	@Override
	public Stas StasTimeDay(String time, String os, String province) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.time LIKE :time and u.os=:os and u.province=:province");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		query.setParameter("province", province);
		if (query.getResultList().size() > 0)

			return (Stas) query.getResultList().get(0);

		return null;
	}

	@Override
	public List<Visitor> VisitorTime(String time, String os) {
		Query query = entityManager
				.createQuery("select u from Visitor u where u.time LIKE :time and u.os=:os");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		return query.getResultList();
	}

	@Override
	public List<Visitor> VisitorTime(String time, String os,String province) {
		Query query = entityManager
				.createQuery("select u from Visitor u where u.time LIKE :time and u.os=:os and u.province=:province");
		query.setParameter("time", time + "%");
		query.setParameter("os", os);
		query.setParameter("province", province);
		return query.getResultList();
	}

	@Override
	public List<Stas> StasTime(String time, String province, String os) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stas> StasTimeother(String province) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.province!=:province  order by u.province desc,u.os desc,u.time desc");
		
		query.setParameter("province", province);
		//query.setMaxResults(7);
		
		return query.getResultList();
	}

	@Override
	public void Save(House house) {
		// TODO Auto-generated method stub\
		entityManager.persist(house);
		
	}

	@Override
	public void Update(House house) {
		// TODO Auto-generated method stub
		entityManager.merge(house);
		
	}

	@Override
	public House byid(int id) {
		Query query = entityManager
				.createQuery("select u from House u where u.id=:id");
		query.setParameter("id", id);
		List<House> hou=query.getResultList();
		if(hou.size()>0){
			return hou.get(0);
		}
		return null;
	}

	@Override
	public void Remove(House house) {
		// TODO Auto-generated method stub
		entityManager.remove(house);
	}

	@Override
	public List<House> bylikeshoolname(String schoolname) {
		Query query = entityManager
				.createQuery("select u from House u where u.schoolname like :schoolname or u.keywords like :schoolname  or u.property like :schoolname order by u.time");
		query.setParameter("schoolname","%"+schoolname+"%");
		query.setMaxResults(20);
		
		return query.getResultList();
	}

	

	@Override
	public List<House> HouseAll(int currentPage, int pageSize) {
		Query query = entityManager
				.createQuery("select u from House u  order by u.time desc");
		
		int startRow = (currentPage-1)*pageSize;
		if(startRow<0){
			startRow=0;
		}
		//第几页
		query.setFirstResult(startRow);
		//每页显示几条数据
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public int HouseAll() {
		Query query = entityManager.createQuery("select count(u) from House u");
		//int count = new Long((long)query.getSingleResult()).intValue();
		int count =(int)(long)query.getSingleResult();
		return count;
	}

	@Override
	public void Remove(Feedback f) {
		// TODO Auto-generated method stub
		entityManager.remove(f);
	}

	@Override
	public Feedback FeedbackByid(int id) {
		Query query = entityManager.createQuery("select u from Feedback u "
				+ "where u.id=:id");
				query.setParameter("id", id);
				query.setMaxResults(1);
		List<Feedback> fl= query.getResultList();
		if(fl.size()>0){
			
			return fl.get(0);
		}
		return null;
	}

	@Override
	public void Save(ParentsLetter ParentsLetter) {
		// TODO Auto-generated method stub
		entityManager.persist(ParentsLetter);
	}

	@Override
	public void Update(ParentsLetter ParentsLetter) {
		// TODO Auto-generated method stub
		entityManager.merge(ParentsLetter);
		
	}

	@Override
	public ParentsLetter Plbyid(int id) {
		Query query = entityManager.createQuery("select u from ParentsLetter u "
				+ "where u.id=:id");
				query.setParameter("id", id);
				query.setMaxResults(1);
		List<ParentsLetter> fl= query.getResultList();
		if(fl.size()>0){
			
			return fl.get(0);
		}
		return null;
	}

	@Override
	public List<ParentsLetter> PlAll() {
		Query query = entityManager.createQuery("select u from ParentsLetter u order by u.time desc");
				
				
		return query.getResultList();
		
	}

	@Override
	public void Remove(ParentsLetter p) {
		// TODO Auto-generated method stub
		entityManager.remove(p);
	}

	@Override
	public List<House> HouseZh(int num) {
		Query query = entityManager
				.createQuery("select u from House u  order by u.count desc");
		
		
		query.setMaxResults(num);
		return query.getResultList();
	}

	@Override
	public House byshoolname(String schoolname) {
		Query query = entityManager
				.createQuery("select u from House u where u.schoolname like :schoolname or u.property like :schoolname order by u.time");
		query.setParameter("schoolname","%"+schoolname+"%");
		query.setMaxResults(1);
		List<House> hl=query.getResultList();
		if(hl.size()>0){
			return hl.get(0);
		}
		return null;
	}

	@Override
	public List<Stas> StasTime(String all, String os, String timeq,
			String timeh) {
		Query query = entityManager
				.createQuery("select u from Stas u where u.os=:os and u.province=:all "
						+ " and u.time>:timeq and u.time<=:timeh "
						+ " order by u.time desc,u.os desc");
		query.setParameter("os", os);
		query.setParameter("timeq", timeq);
		query.setParameter("all", all);
		query.setParameter("timeh", timeh);
		return query.getResultList();
	}

	@Override
	public void Save(SetIp SetIp) {
		// TODO Auto-generated method stub
		entityManager.persist(SetIp);
	}

	@Override
	public void Update(SetIp SetIp) {
		// TODO Auto-generated method stub
		entityManager.merge(SetIp);
	}

	@Override
	public int byTimeConut(String time, String os) {
		Query query = entityManager
				.createQuery("select count(u) from SetIp u where u.os=:os and u.time=:time order by u.id desc");
		query.setParameter("os", os);
		query.setParameter("time", time);
		int count =(int)(long)query.getSingleResult();
		return count;
	}

	@Override
	public void Remove(SetIp SetIp) {
		// TODO Auto-generated method stub
		entityManager.remove(SetIp);
	}

	@Override
	public SetIp byTimeIp(String time, String ip,String os) {
		Query query = entityManager
				.createQuery("select u from SetIp u where u.ip=:ip and u.time=:time and u.os=:os order by u.id desc");
		query.setParameter("ip", ip);
		query.setParameter("os", os);
		query.setParameter("time", time);
		List<SetIp> sep=query.getResultList();
		if(sep.size()>0)
			return sep.get(0);
		return null;
	}

	
}
