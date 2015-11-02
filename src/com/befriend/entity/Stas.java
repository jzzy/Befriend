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
	@Column(name="home1")//主页1点次数
	private int home1;
	@Column(name="home2")//主页2点次数
	private int home2;
	@Column(name="home3")//主页3点次数
	private int home3;
	
	
	@Column(name="home31")//主页31点次数
	private int home31;
	@Column(name="home32")//主页32点次数
	private int home32;
	@Column(name="home33")//主页33点次数
	private int home33;

	@Column(name="home331")//主页331点次数
	private int home331;
	@Column(name="home332")//主页332点次数
	private int home332;
	@Column(name="home333")//主页333点次数
	private int home333;
	@Column(name="home334")//主页334点次数
	private int home334;
	
	@Column(name="home34")//主页34点次数
	private int home34;
	
	public int getHome1() {
		return home1;
	}
	public void setHome1(int home1) {
		this.home1 = home1;
	}
	public int getHome2() {
		return home2;
	}
	public void setHome2(int home2) {
		this.home2 = home2;
	}
	public int getHome3() {
		return home3;
	}
	public void setHome3(int home3) {
		this.home3 = home3;
	}
	public int getHome31() {
		return home31;
	}
	public void setHome31(int home31) {
		this.home31 = home31;
	}
	public int getHome32() {
		return home32;
	}
	public void setHome32(int home32) {
		this.home32 = home32;
	}
	public int getHome33() {
		return home33;
	}
	public void setHome33(int home33) {
		this.home33 = home33;
	}
	public int getHome331() {
		return home331;
	}
	public void setHome331(int home331) {
		this.home331 = home331;
	}
	public int getHome332() {
		return home332;
	}
	public void setHome332(int home332) {
		this.home332 = home332;
	}
	public int getHome333() {
		return home333;
	}
	public void setHome333(int home333) {
		this.home333 = home333;
	}
	public int getHome334() {
		return home334;
	}
	public void setHome334(int home334) {
		this.home334 = home334;
	}
	public int getHome34() {
		return home34;
	}
	public void setHome34(int home34) {
		this.home34 = home34;
	}
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
