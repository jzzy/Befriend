package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Adv;
import com.befriend.entity.App;
public interface AppDAO {
    	
	//���app
	public void Save(App app);
	//ͨ��id��ѯapp
	public App byid(int id);
	//ɾ��app
	public void remove(App app);
	//��ѯnum��  app
	public List<App> All(int num);
	//��ҳ��ѯ
	public List<App> FAll(int currentPage,int pageSize);
	//��ѯȫ��app
	public List<App> ALL();
	//ͳ��app���ش���
	public void Update(App app);
	
	//���adv
	public void Save(Adv adv);
	//����adv
	public void Update(Adv adv);
	//ͨ��id��ѯadv
	public Adv byAdvid(int id);
	//ɾ��adv
	public void remove(Adv adv);
	//��ѯnum��  adv
	public List<Adv> getAdvnum(int num,int Online);
	
}
