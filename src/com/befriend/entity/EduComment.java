package com.befriend.entity;

import java.io.Serializable;

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

import com.google.gson.annotations.Expose;


/**
 * @author SterOtto
 */
@Entity
@Table(name="edu_comment")
public class EduComment implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static int ROOT = 0;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@Expose
	private Integer id;
	
	@Column(name="merchant_id",nullable=false)
	@Expose
	private String merchantId;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@Expose
	private User user;
	
	@JoinColumn(name="reply_id")
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@Expose
	private User reply;
	
	@Column(name="score")
	@Expose
	private int score	=0;
	
	@Column(name="content")
	@Expose
	private String content;
	
	@Column(name="time")
	@Expose
	private String time;
	
	@Column(name="pictures")
	@Expose
	private String pictures;
	
	@Column(name="father_id")
	@Expose
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

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public User getReply()
	{
		return reply;
	}

	public void setReply(User reply)
	{
		this.reply = reply;
	}
	
}
