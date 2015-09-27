package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="edu_services")
@Entity
public class EduServices implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="merchant_id")
	private String merchantId;
	
	@Column(name="is_vip")
	private String isVip;
	
	@Column(name="is_stop")
	private String isStop;
	
	@Column(name="name")
	private String name;
	
	@Column(name="alias")
	private String alias;
	
	@Column(name="province")
	private String province;
	
	@Column(name="city")
	private String city;
	
	@Column(name="pro_pinyin")
	private String proPinYin;
	
	@Column(name="city_id")
	private String cityId;
	
	@Column(name="county")
	private String county;
	
	@Column(name="class_first")
	private String classFirst;
	
	@Column(name="class_first_id")
	private String classFirstId;
	
	@Column(name="class_second")
	private String classSecond;
	
	@Column(name="class_second_id")
	private String classSecondId;
	
	@Column(name="address")
	private String address;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="business_hours")
	private String businessHours;
	
	@Column(name="ave_price")
	private float avePrice 		= 0;
	
	@Column(name="star")
	private float star 			= 0;
	
	@Column(name="picture")
	private String picture;
	
	@Column(name="describe")
	private String describe;
	
	@Column(name="label")
	private String label;
	
	@Column(name="map_type")
	private int mapType 		= 0;
	
	@Column(name="latitude")
	private double latitude 	= 0;
	
	@Column(name="longitude")
	private double longitude 	= 0;
	
	@Column(name="env_score")
	private float envScore 		= 0;
	
	@Column(name="ser_score")
	private float serScore 		= 0;
	
	@Column(name="five_star")
	private int fiveStar 		= 0;
	
	@Column(name="four_star")
	private int fourStar 		= 0;
	
	@Column(name="three_star")
	private int threeStar 		= 0;
	
	@Column(name="two_star")
	private int twoStar 		= 0;
	
	@Column(name="one_star")
	private int oneStar 		= 0;
	
	@Column(name="is_store")
	private String isStore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProPinYin() {
		return proPinYin;
	}

	public void setProPinYin(String proPinYin) {
		this.proPinYin = proPinYin;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getClassFirst() {
		return classFirst;
	}

	public void setClassFirst(String classFirst) {
		this.classFirst = classFirst;
	}

	public String getClassFirstId() {
		return classFirstId;
	}

	public void setClassFirstId(String classFirstId) {
		this.classFirstId = classFirstId;
	}

	public String getClassSecond() {
		return classSecond;
	}

	public void setClassSecond(String classSecond) {
		this.classSecond = classSecond;
	}

	public String getClassSecondId() {
		return classSecondId;
	}

	public void setClassSecondId(String classSecondId) {
		this.classSecondId = classSecondId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public float getAvePrice() {
		return avePrice;
	}

	public void setAvePrice(float avePrice) {
		this.avePrice = avePrice;
	}

	public float getStar() {
		return star;
	}

	public void setStar(float star) {
		this.star = star;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getMapType() {
		return mapType;
	}

	public void setMapType(int mapType) {
		this.mapType = mapType;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public float getEnvScore() {
		return envScore;
	}

	public void setEnvScore(float envScore) {
		this.envScore = envScore;
	}

	public float getSerScore() {
		return serScore;
	}

	public void setSerScore(float serScore) {
		this.serScore = serScore;
	}

	public int getFiveStar() {
		return fiveStar;
	}

	public void setFiveStar(int fiveStar) {
		this.fiveStar = fiveStar;
	}

	public int getFourStar() {
		return fourStar;
	}

	public void setFourStar(int fourStar) {
		this.fourStar = fourStar;
	}

	public int getThreeStar() {
		return threeStar;
	}

	public void setThreeStar(int threeStar) {
		this.threeStar = threeStar;
	}

	public int getTwoStar() {
		return twoStar;
	}

	public void setTwoStar(int twoStar) {
		this.twoStar = twoStar;
	}

	public int getOneStar() {
		return oneStar;
	}

	public void setOneStar(int oneStar) {
		this.oneStar = oneStar;
	}

	public String getIsStore() {
		return isStore;
	}

	public void setIsStore(String isStore) {
		this.isStore = isStore;
	}
	
	
	

}
