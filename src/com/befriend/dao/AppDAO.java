package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Adv;
import com.befriend.entity.App;
public interface AppDAO {
    	
	//添加app
	public void Save(App app);
	//通过id查询app
	public App byid(int id);
	//删除app
	public void remove(App app);
	//查询num个  app
	public List<App> All(int num);
	//分页查询
	public List<App> FAll(int currentPage,int pageSize);
	//查询全部app
	public List<App> ALL();
	//统计app下载次数
	public void Update(App app);
	
	//添加adv
	public void Save(Adv adv);
	//跟新adv
	public void Update(Adv adv);
	//通过id查询adv
	public Adv byAdvid(int id);
	//删除adv
	public void remove(Adv adv);
	//查询num个  adv
	public List<Adv> getAdvnum(int num,int Online);
	
}
