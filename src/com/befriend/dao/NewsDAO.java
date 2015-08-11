package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Book;
import com.befriend.entity.News;

/**
 * ���ŷ���
 * 
 * @author Administrator
 *
 */
public interface NewsDAO {
	// 1 ��ѯ ���ж���������
	public List<News> n2ews(int newsid);
	// ɾ������
	public void rm(News n);
	public void rm(Book book);
	// ��ҳ��ѯ
	public List<News> Pagination(int pageSize, int currentPage);

	// 1 ��ѯnum ����ʱ������
	public List<News> Hottimes(int num);

	// 2 ��ѯnum ����ʱ������ ������ ������վ �������� ��
	public List<News> Hottime(int num);

	// ��ѯȫ�����Ű��� �ղ� ������ ����ʱ�� ����
	public List<News> All();

	// 2 ���ȵ��ѯ num�����Ű��� �ղ� ������ ����ʱ�� ����
	public List<News> Hottest(int num);
	// 2 ���ȵ��ѯ num�����Ű��� �ղ� ������ ����ʱ�� ���� ��ҳ��ѯ
	public List<News> Hottest(int pageSize,int currentPage);
	
	
	// 3 ���� ʡ�� �м� ���� ���Ѳ� ������ ʱ��
	public List<News> Hotarea(int num, String area);
	// 3 ���� ʡ�� �м� ���� ���Ѳ� ������ ʱ�� ��ҳ��ѯ
	public List<News> Hotarea(String area, String areas, int pageSize,int currentPage);
	// 3 ���� ʡ�� �м� ���� ���Ѳ� ������ ʱ�� ��ҳ��ѯ
	public List<News> Hotarea(String area, int pageSize,int currentPage);

	
	// �������ű��ղصĴ���
	public void Upnews(News news);
	public void Upnews(Book book);
	// ͨ��newsid��ѯ ����
	public News byid(int newsid);
	public Book byIdBook(int id);
	public Book byTitleBook(String title);
	// ��ȡ ʱ�����µ� ����
	public List<News> news(int num);

	// �������
	public void Save(News n);
	public void Save(Book book);
	// ����8�����ѯ
	public List<News> type(int num, String type);


	// ����8�����ѯ ��4С��
	public List<News> types(String type);

	// ���Ű������ +�ղ��� ʱ�� ���� ���� �����ѯ ������֮ǰ��
	public List<News> cah(int num, String time, String qtime);

	// ���Ű������ +�ղ��� ʱ�� ����
	public List<News> cah(int num);

	// ���Ű������ +�ղ��� ʱ�� ���� ��ҳ��ѯ
	public List<News> cah(int pageSize, int currentPage);
	public List<Book> paging(int pageSize, int currentPage,int type);
	public int countBools(int type);
	// ���Ű��� ʡ ��ѯ
	public List<News> area(String area, int num);
	// ���Ű��� ʡ ��ѯ ��ʡ�� ר������
	public List<News> area(String area);

	// ����8�����ѯ ��ҳ��ѯ
	public List<News> type(int num, String type, int pageSize, int currentPage);

	//����8���� 4С���ѯ ��ҳ��ѯ
	
	public List<News> types(String type, int pageSize, int currentPage);
}
