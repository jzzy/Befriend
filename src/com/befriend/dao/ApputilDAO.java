package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Admin;
import com.befriend.entity.AppUp;
import com.befriend.entity.Feedback;
import com.befriend.entity.House;
import com.befriend.entity.ParentsLetter;
import com.befriend.entity.Stas;
import com.befriend.entity.Visitor;

/**
 * ͳ�� �ҳ���ؿͻ��˵� ���� ���û���������
 * 
 * @author Administrator
 *
 */

public interface ApputilDAO {
	// ����app
	public void Save(AppUp aup);

	// �޸�appjztd��Ϣ
	public void Update(AppUp aup);

	// �鿴�Ƿ����
	public AppUp UP();

	// �û����ӷ�����Ϣ
	public void Save(Feedback f);

	// ɾ��������Ϣ
	public void Remove(Feedback f);

	// ͨ��id��ѯ����
	public Feedback FeedbackByid(int id);

	// �鿴�û�������Ϣ
	public List<Feedback> FeedbackAll();

	// ͨ�� ·����ѯ App
	public AppUp select(String Path);

	// ͨ�� id��ѯ App
	public AppUp appbyid(int id);

	// ɾ�� App
	public void Remove(AppUp aup);

	// ����Ա����
	public Admin admin(String admin, String pwd);

	// ͳ���ο�����
	public void Save(Visitor vor);

	// �޸��ο���Ϣ
	public void Update(Visitor vor);

	// ��ѯ�ο���Ϣ
	public Visitor sVisitor(String mac);

	// ��Ѱ ���� ���ο�����
	public List<Visitor> VisitorTime(String time, String os);

	// ��Ѱ ���� ���ο����� ��ϵͳ ������
	public List<Visitor> VisitorTime(String time, String os, String province);

	// ����ͳ��
	public void Save(Stas sta);

	// �޸�ͳ��
	public void Update(Stas sta);

	// ��ѯͳ�� ���뵱ǰ���� ���������ڵ�
	public List<Stas> StasTime(String province, String os);

	// ��ѯͳ�� ���뵱ǰ���� ���������ڵ�
	public List<Stas> StasTimeother(String province);

	// ��ѯͳ�� ���뵱ǰ���� ���������ڵ�
	public List<Stas> StasTime(String time, String province, String os);

	// ��ѯָ�������� ��ϵͳ
	public Stas StasTimeDay(String time, String os);

	// ��ѯָ�������� ��ʡ ϵͳ
	public Stas StasTimeDay(String time, String os, String province);
	/**
	 * ѧ����
	 * @param house
	 */

	// ����ѧ����
	public void Save(House house);

	// �޸�ͳ��
	public void Update(House house);

	// ͨ��id��
	public House byid(int id);

	// ɾ��ѧ����
	public void Remove(House house);

	// ͨ��ѧУ���� ¥������ ģ����ѯ
	public List<House> bylikeshoolname(String schoolname);
	// ͨ��ѧУ���� ¥������ ��ģ����ѯ
	public House byshoolname(String schoolname);

	// ��ҳ
	public List<House> HouseAll(int currentPage, int pageSize);
	// ��ѯ���
	public List<House> HouseZh(int num);

	// ��ѯһ������
	public int HouseAll();
/**
 * �ҳ�����
 * @param ParentsLetter
 */
	// ���Ӽҳ�����
	public void Save(ParentsLetter ParentsLetter);

	// �޸ļҳ�����
	public void Update(ParentsLetter ParentsLetter);

	// ͨ��id��ҳ�����
	public ParentsLetter Plbyid(int id);
	//�鿴���мҳ�����
	public List<ParentsLetter> PlAll();
	public void Remove(ParentsLetter p);
}