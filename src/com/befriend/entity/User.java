package com.befriend.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="users")
public class User implements Serializable
{

	/**
	 * 用户 
	 */
	private static final long serialVersionUID = 1L;
	///来源
	public static final String BBT="bbt";//掌中校园
	public static final String SYN="syn";//优教通
	public static final String DX="dx";//电信
	public static final String OWN="own";// 家长之友
	
	//家长之友内部分类
	public static final String WEB="web";//网页
	public static final String ANDROID="android";//安卓
	public static final String IOS="ios";//苹果
	
	//未登入用户算微信的
	public static final String WECHAT="wechat";//微信
	
	public static final String ALL="all";//all
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username")//用户名
	@Expose
	private String username;
	@Column(name="nickname")//昵称
	@Expose
	private String nickname;
	@Column(name="password")//密码
	private String password;
	@Column(name="img")//用户头像地址
	@Expose
	private String img;
	
	
	@Column(name="stage")//阶段
	private String stage;
	@Column(name="time")//注册时间
	private String time;
	@Column(name="address")//地址 省级
	@Expose
	/**
	 * 地址 省级
	 */
	private String address;
	
	@Column(name="addcity")//地址 市级
	@Expose
	private String addcity;
	@Column(name="phone")//电话
	@Expose
	private String phone;
	@Column(name="lookphone")//电话
	private String lookphone;
	@Column(name="school")//学校
	private String school;
	
	@Column(name="competence")//权限  0 普通用户  1 市级管理员 2省级管理员 4是教授
	private int competence;
	@Column(name="gag")//创建论坛权限  0 可以创建论坛  1 不可以  
	private int gag;
	
	@Column(name="loginnum")//用户登入次数
	private int loginnum;
	
	public String getLookphone() {
		return lookphone;
	}
	public void setLookphone(String lookphone) {
		this.lookphone = lookphone;
	}
	@Column(name="finaltime")//用户最后登入时间
	private String finaltime;
	@Column(name="ip")//用户ip
	private String ip;
	@Column(name="port")//用户port
	private int port;
	@Column(name="online")//用户 用户上线状态 0不在线 1在线
	@Expose
	private int online;
	@Column(name="accnumno")//用户账号 8位的
	@Expose
	private String accnumno;
	@Column(name="come")// own 是家长之友 syn 是 优教通 bbt 是 掌中校园
	private String come;
	@Column(name="os")// 
	private String os;
	
	@OneToMany(mappedBy="user")
	private List<EduComment> eduComment;
	
	@OneToMany(mappedBy="reply")
	private List<EduComment> replyComment;
	
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getCome() {
		return come;
	}
	public void setCome(String come) {
		this.come = come;
	}
	public int getPort() {
		return port;
	}
	public String getAccnumno() {
		return accnumno;
	}
	public void setAccnumno(String accnumno) {
		this.accnumno = accnumno;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getLoginnum() {
		return loginnum;
	}
	public void setLoginnum(int loginnum) {
		this.loginnum = loginnum;
	}
	public String getFinaltime() {
		return finaltime;
	}
	public void setFinaltime(String finaltime) {
		this.finaltime = finaltime;
	}
	public int getGag() {
		return gag;
	}
	public void setGag(int gag) {
		this.gag = gag;
	}
	public int getCompetence() {
		return competence;
	}
	public void setCompetence(int competence) {
		this.competence = competence;
	}
	public String getAddcity() {
		return addcity;
	}
	public void setAddcity(String addcity) {
		this.addcity = addcity;
	}
	public String getSchool()
	{
		return school;
	}
	public void setSchool(String school)
	{
		this.school = school;
	}
	public String getStage()
	{
		return stage;
	}
	public void setStage(String stage)
	{
		this.stage = stage;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public List<EduComment> getEduComment()
	{
		return eduComment;
	}
	public void setEduComment(List<EduComment> eduComment)
	{
		this.eduComment = eduComment;
	}
	public List<EduComment> getReplyComment()
	{
		return replyComment;
	}
	public void setReplyComment(List<EduComment> replyComment)
	{
		this.replyComment = replyComment;
	}
	

}
