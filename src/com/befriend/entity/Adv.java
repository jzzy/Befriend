package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adv")
public class Adv  implements Serializable {


	/**
	 * �����
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")//�������
	private String name;
	@Column(name="summary")//����Ҫ
	private String summary;
	
	@Column(name="time")//����ϴ�ʱ��
	private String time;
	@Column(name="timeo")//�������ʱ��
	private String timeo;
	@Column(name="finaltime")//�������ʱ��
	private String finaltime;
	
	@Column(name="pathimg")//���ͼƬ�ϴ���ַ
	private String pathimg;
	@Column(name="sequence")//�������
	private int sequence;
	@Column(name="type")//������
	private String type;
	@Column(name="href")//�������
	private String href;
	@Column(name="calculatingTime")//����ϼ�ʱ��
	private int calculatingTime;
	@Column(name="online")//�Ƿ񷢲� 1�Ƿ��� 0��δ����
	private int online;
	@Column(name="bbt")//����У԰
	private int bbt;
	@Column(name="syn")//�Ž�ͨ
	private int syn;
	@Column(name="wechat")//΢��
	private int wechat;
	@Column(name="ios")
	private int ios;
	@Column(name="android")
	private int android;
	@Column(name="web")
	private int web;
	
	
	
	
	public String getTimeo() {
		return timeo;
	}
	public void setTimeo(String timeo) {
		this.timeo = timeo;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getBbt() {
		return bbt;
	}
	public void setBbt(int bbt) {
		this.bbt = bbt;
	}
	public int getSyn() {
		return syn;
	}
	public void setSyn(int syn) {
		this.syn = syn;
	}
	public int getWechat() {
		return wechat;
	}
	public void setWechat(int wechat) {
		this.wechat = wechat;
	}
	public int getIos() {
		return ios;
	}
	public void setIos(int ios) {
		this.ios = ios;
	}
	public int getAndroid() {
		return android;
	}
	public void setAndroid(int android) {
		this.android = android;
	}
	public int getWeb() {
		return web;
	}
	public void setWeb(int web) {
		this.web = web;
	}
	public Integer getId() {
		return id;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPathimg() {
		return pathimg;
	}
	public void setPathimg(String pathimg) {
		this.pathimg = pathimg;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFinaltime() {
		return finaltime;
	}
	public void setFinaltime(String finaltime) {
		this.finaltime = finaltime;
	}
	public int getCalculatingTime() {
		return calculatingTime;
	}
	public void setCalculatingTime(int calculatingTime) {
		this.calculatingTime = calculatingTime;
	}


	
	
}
