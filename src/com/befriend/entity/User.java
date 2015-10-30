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
	 * �û� 
	 */
	private static final long serialVersionUID = 1L;
	///��Դ
	public static final String BBT="bbt";//����У԰
	public static final String SYN="syn";//�Ž�ͨ
	public static final String DX="dx";//����
	public static final String OWN="own";// �ҳ�֮��
	
	//�ҳ�֮���ڲ�����
	public static final String WEB="web";//��ҳ
	public static final String ANDROID="android";//��׿
	public static final String IOS="ios";//ƻ��
	
	//δ�����û���΢�ŵ�
	public static final String WECHAT="wechat";//΢��
	
	public static final String ALL="all";//all
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username")//�û���
	@Expose
	private String username;
	@Column(name="nickname")//�ǳ�
	@Expose
	private String nickname;
	@Column(name="password")//����
	private String password;
	@Column(name="img")//�û�ͷ���ַ
	@Expose
	private String img;
	
	
	@Column(name="stage")//�׶�
	private String stage;
	@Column(name="time")//ע��ʱ��
	private String time;
	@Column(name="address")//��ַ ʡ��
	@Expose
	/**
	 * ��ַ ʡ��
	 */
	private String address;
	
	@Column(name="addcity")//��ַ �м�
	@Expose
	private String addcity;
	@Column(name="phone")//�绰
	@Expose
	private String phone;
	@Column(name="lookphone")//�绰
	private String lookphone;
	@Column(name="school")//ѧУ
	private String school;
	
	@Column(name="competence")//Ȩ��  0 ��ͨ�û�  1 �м�����Ա 2ʡ������Ա 4�ǽ���
	private int competence;
	@Column(name="gag")//������̳Ȩ��  0 ���Դ�����̳  1 ������  
	private int gag;
	
	@Column(name="loginnum")//�û��������
	private int loginnum;
	
	public String getLookphone() {
		return lookphone;
	}
	public void setLookphone(String lookphone) {
		this.lookphone = lookphone;
	}
	@Column(name="finaltime")//�û�������ʱ��
	private String finaltime;
	@Column(name="ip")//�û�ip
	private String ip;
	@Column(name="port")//�û�port
	private int port;
	@Column(name="online")//�û� �û�����״̬ 0������ 1����
	@Expose
	private int online;
	@Column(name="accnumno")//�û��˺� 8λ��
	@Expose
	private String accnumno;
	@Column(name="come")// own �Ǽҳ�֮�� syn �� �Ž�ͨ bbt �� ����У԰
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
