package com.befriend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="edu_comment")
public class EduComment
{
	public static int ROOT = 0;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="merchan_id",nullable=false)
	private String merchantId;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	private User user;
	
	@Column(name="conten")
	private String content;
	
	@Column(name="time")
	private String time;
	
	@Column(name="pictures")
	private String pictures;
	
	@Column(name="father_id")
	private int fatherId;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getMerchantId()
	{
		return merchantId;
	}

	public void setMerchantId(String merchantId)
	{
		this.merchantId = merchantId;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getPictures()
	{
		return pictures;
	}

	public void setPictures(String pictures)
	{
		this.pictures = pictures;
	}

	public int getFatherId()
	{
		return fatherId;
	}

	public void setFatherId(int fatherId)
	{
		this.fatherId = fatherId;
	}
	
}
