package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Book;
import com.befriend.entity.News;

/**
 * 新闻方法
 * 
 * @author Administrator
 *
 */
public interface NewsDAO {
	// 1 查询 看有多少条更新
	public List<News> likeNews(String title);
	// 1 查询 看有多少条更新
	public int maxNewsCount();
	// 删除新闻
	public void rm(News n);
	public void rm(Book book);
	// 分页查询
	public List<News> Pagination(String time,int pageSize, int currentPage);
	// 分页查询
	public List<News> Pagination(int pageSize, int currentPage,String time);
	// 1 查询num 按照时间排序
	public List<News> Hottimes(int num);
	// 获取小于等于当前时间的新闻
	public List<News> Hottimes(int num,String time);
	//获取大于当前时间的新闻
	public List<News> Hottimes(String time,int num);
	//获取大于当前时间的新闻
	public int HottimesCountLarge(String time,int num);
	// 获取小于等于当前时间的新闻
	public int HottimesCountSmall(String time,int num);
	// 2 查询num 按照时间排序 不包括 轻松驿站 健康导航 类
	public List<News> Hottime(int num,String time);

	// 查询全部新闻按照 收藏 评论数 发布时间 排序
	public List<News> All();

	// 2 按热点查询 num条新闻按照 收藏 评论数 发布时间 排序
	public List<News> Hottest(int num,String time);
	// 2 按热点查询 num条新闻按照 收藏 评论数 发布时间 排序 分页查询
	public List<News> Hottest(int pageSize,int currentPage,String time);
	
	
	// 3 本地 省级 市级 排序 按搜藏 评论数 时间
	public List<News> Hotarea(int num, String area);
	// 3 本地 省级 市级 排序 按搜藏 评论数 时间 分页查询
	public List<News> Hotarea(String area, String areas, int pageSize,int currentPage);
	// 3 本地 省级 市级 排序 按搜藏 评论数 时间 分页查询
	public List<News> Hotarea(String area, int pageSize,int currentPage,String time);
	//查询本地新闻数量
	public int Hotarea(String area,String time);
	
	// 更新新闻被收藏的次数
	public void Upnews(News news);
	public void Upnews(Book book);
	// 通过newsid查询 新闻
	public News byid(int newsid);
	public Book byIdBook(int id);
	public Book byTitleBook(String title);
	// 获取 时间最新的 新闻
	public List<News> news(int num);

	// 添加新闻
	public void Save(News n);
	public void Save(Book book);
	// 按照8大类查询
	public List<News> type(int num, String type,String time);


	// 按照8大类查询 的4小类
	public List<News> types(String type,String time);

	// 新闻按点击数 +收藏数 时间 排序 可以 定义查询 多少天之前的
	public List<News> cah(int num, String time, String qtime);

	// 新闻按点击数 +收藏数 时间 排序
	public List<News> cah(int num);

	// 新闻按点击数 +收藏数 时间 排序 分页查询
	public List<News> cah(int pageSize, int currentPage);
	public List<Book> paging(int pageSize, int currentPage,int type);
	public int countBools(int type);
	// 新闻按照 省 查询
	public List<News> area(String area, int num,String time);
	// 新闻按照 省 查询 本省的 专家新闻
	public List<News> area(String area);

	// 按照8大类查询 分页查询
	public List<News> type(int num, String type, int pageSize, int currentPage,String time);

	//按照8大类 4小类查询 分页查询
	
	public List<News> types(String type, int pageSize, int currentPage,String time);
}
