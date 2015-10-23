package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stas")
public class Stas  implements Serializable {
	/**
	 * 每天记录  统计
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="vored")//每天新增游客数量
	private int vored;
	@Column(name="usersaved")//每天新增用户数量
	private int usersaved;
	@Column(name="userlogined")//每天活跃的用户数量
	private int userlogined;
	@Column(name="usersyned")//每天同时在线(巅峰)用户数量
	private int usersyned;
	@Column(name="downloaded")//每天客户端下载量
	private int downloaded;
	@Column(name="time")//时间 2005-05-05
	private String time;
	@Column(name="os")// 来自的系统
	private String os;
	@Column(name="province")// 来自的的省
	private String province;
	@Column(name="pv")//浏览次数
	private int pv;
	@Column(name="uv")//当天的访问的客户端
	private int uv;
	@Column(name="ip")//当天访问过的ip次数
	private int ip;
	
	
	
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public int getUv() {
		return uv;
	}
	public void setUv(int uv) {
		this.uv = uv;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getVored() {
		return vored;
	}
	public void setVored(int vored) {
		this.vored = vored;
	}
	public int getUsersaved() {
		return usersaved;
	}
	public void setUsersaved(int usersaved) {
		this.usersaved = usersaved;
	}
	public int getUserlogined() {
		return userlogined;
	}
	public void setUserlogined(int userlogined) {
		this.userlogined = userlogined;
	}
	public int getUsersyned() {
		return usersyned;
	}
	public void setUsersyned(int usersyned) {
		this.usersyned = usersyned;
	}
	public int getDownloaded() {
		return downloaded;
	}
	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
