package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="attention")

public class Attention implements Serializable
{

	
	private static final long serialVersionUID = 1L;
	public final static int COME_EduServices = 1;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@Column(name="userid")
	@Expose
	private int userid;
	@Column(name="objectid")
	@Expose
	private int objectid;
	@Column(name="come")
	@Expose
	private int come;
	@Column(name="time")
	@Expose
	private String time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getObjectid() {
		return objectid;
	}
	public void setObjectid(int objectid) {
		this.objectid = objectid;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCome() {
		return come;
	}
	public void setCome(int come) {
		this.come = come;
	}
	
	
	

}
