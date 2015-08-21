package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review implements Serializable
{
	/**
	 * ��������
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username")//�û���
	private String username;
	@Column(name="newsid")//����id
	private int newsid;
	@Column(name="review")//��������
	private String review;
	@Column(name="time")//����ʱ��
	private String time;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	
	public String getUsername() {
	    return username;
	}
	public void setUsername(String username) {
	    this.username = username;
	}
	public int getNewsid()
	{
		return newsid;
	}
	public void setNewsid(int newsid)
	{
		this.newsid = newsid;
	}
	public String getReview()
	{
		return review;
	}
	public void setReview(String review)
	{
		this.review = review;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	

}