package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Admin;
import com.befriend.entity.AppUp;
import com.befriend.entity.Areas;
import com.befriend.entity.Cities;
import com.befriend.entity.City;
import com.befriend.entity.Feedback;
import com.befriend.entity.House;
import com.befriend.entity.ParentsLetter;
import com.befriend.entity.Provinces;
import com.befriend.entity.SetIp;
import com.befriend.entity.Stas;
import com.befriend.entity.Visitor;

/**
 * 统计 家长天地客户端的 更新 和用户反馈数据
 * 
 * @author Administrator
 *
 */

public interface ApputilDAO {
	// 添加app
	public void Save(AppUp aup);

	// 修改appjztd信息
	public void Update(AppUp aup);

	// 查看是否更新
	public AppUp UP();

	// 用户添加反馈信息
	public void Save(Feedback f);

	// 删除反馈信息
	public void Remove(Feedback f);

	// 通过id查询反馈
	public Feedback FeedbackByid(int id);

	// 查看用户反馈信息
	public List<Feedback> FeedbackAll();

	// 通过 路径查询 App
	public AppUp select();

	// 通过 id查询 App
	public AppUp appbyid(int id);

	// 删除 App
	public void Remove(AppUp aup);

	// 管理员登入
	public Admin admin(String admin, String pwd);

	// 统计游客数量
	public void Save(Visitor vor);

	// 修改游客信息
	public void Update(Visitor vor);

	// 查询游客信息
	public Visitor sVisitor(String mac);

	// 查寻 当天 的游客数量
	public List<Visitor> VisitorTime(String time, String os);

	// 查寻 当天 的游客数量 按系统 按地区
	public List<Visitor> VisitorTime(String time, String os, String province);

	// 新增统计
	public void Save(Stas sta);

	// 修改统计
	public void Update(Stas sta);

	// 查询统计 输入当前日期 返回七天内的
	public List<Stas> StasTime(String province, String os);

	// 查询统计
	public List<Stas> StasTime(String all, String os, String timeq, String timeh);

	// 查询统计 输入当前日期 返回七天内的
	public List<Stas> StasTimeother(String province);

	// 查询统计 输入当前日期 返回七天内的
	public List<Stas> StasTime(String time, String province, String os);

	// 查询指定天数的 按系统
	public Stas StasTimeDay(String time, String os);

	// 查询指定天数的 按省 系统
	public Stas StasTimeDay(String time, String os, String province);

	/**
	 * 学区房
	 * 
	 * @param house
	 */

	// 添加学区房
	public void Save(House house);

	// 修改统计
	public void Update(House house);

	// 通过id查
	public House byid(int id);

	// 删除学区房
	public void Remove(House house);

	// 通过学校名称 楼盘名称 模糊查询
	public List<House> bylikeshoolname(String schoolname);

	// 通过学校名称 楼盘名称 不模糊查询
	public House byshoolname(String schoolname);

	// 分页
	public List<House> HouseAll(int currentPage, int pageSize);

	// 查询最火
	public List<House> HouseZh(int num);

	// 查询一共多少
	public int HouseAll();

	/**
	 * 家长来稿
	 * 
	 * @param ParentsLetter
	 */
	// 添加家长来信
	public void Save(ParentsLetter ParentsLetter);

	// 修改家长来信
	public void Update(ParentsLetter ParentsLetter);

	// 通过id查家长来信
	public ParentsLetter Plbyid(int id);

	// 查看所有家长来信
	public List<ParentsLetter> PlAll();

	public void Remove(ParentsLetter p);

	/**
	 * 记录用户访问量ip
	 */
	// 添加新ip
	public void Save(SetIp SetIp);

	// 修改ip
	public void Update(SetIp SetIp);
	// 查看指定日期 和端 记录的ip个数
	public SetIp byTimeIp(String time, String ip,String os);
	// 查看指定日期 和端 记录的ip个数
	public int byTimeConut(String time, String os);
	//删除
	public void Remove(SetIp SetIp);
	/**
	 * 查询地区ABCD
	 */
	public List<City> cityFind(String key);
	public List<City> cityFindAll();
	public void Save(City c);
	/**
	 * 查询省得到市
	 */
	public List<Provinces> ProvincesName(String name);
	public List<Cities> CitiesName(Integer provinceid);
	public List<Areas> AreasName(Integer cityid);
}
