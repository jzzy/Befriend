package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//��̳��Ҫ��
@Entity
@Table(name = "forumone")
public class ForumOne  implements Serializable {

	/**
	 * ��̳��Ҫ�� one
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "title")
	// ��̳����
	private String title;

	@Column(name = "type")
	// ��̳��� 1 ר�Ҵ��� 2ѧǰ 3Сѧ 4��ѧ 5��������
	private int type;
	@Column(name = "total")
	// ��̳�ܻظ���
	private int total;

	@Column(name = "userid")
	// ��̳������
	private int userid;
	@Column(name = "time")
	// ��̳����ʱ��
	private String time;

	@Column(name = "content")
	// ��̳����
	private String content;

	@Column(name = "area")
	// ��̳����ʡ��
	private String area;
	@Column(name = "areas")
	// ��̳�����м�
	private String areas;
	@Column(name = "img")
	// ��̳ͼƬ
	private String img;
	@Column(name = "follectnum")
	// ��̳���ղش���
	private int follectnum;
	@Column(name = "fHits")
	// ��̳���������
	private int fHits;

	@Column(name = "frs")
	// ��̳���ظ�����
	private int frs;



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getfHits() {
		return fHits;
	}

	public void setfHits(int fHits) {
		this.fHits = fHits;
	}

	public int getFrs() {
		return frs;
	}

	public void setFrs(int frs) {
		this.frs = frs;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getFollectnum() {
		return follectnum;
	}

	public void setFollectnum(int follectnum) {
		this.follectnum = follectnum;
	}

}