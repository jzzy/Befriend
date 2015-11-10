package com.befriend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="activity")
public class EduActivity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="merchant_id",nullable=false)
	private String merchantId;
	
	@Column(name="is_v")
	private String isV;
	
	@Column(name="is_coop")
	private String isCoop;
	
	@Column(name="is_stop")
	private String isStop;
	
	@Column(name="province")
	private String province;
	
	@Column(name="city")
	private String city;
	
	@Column(name="county")
	private String county;
	
	@Column(name="class_one")
	private String classOne;
	
	@Column(name="class_two")
	private String classTwo;
	
	@Column(name="class_three")
	private String classThree;
	
	@Column(name="class_four")
	private String classFour;
	
	@Column(name="price")
	private double price;
	
	@Column(name="place")
	private String place;
	
	@Column(name="type")
	private String type;
	
	@Column(name="title")
	private String title;
	
	@Column(name="alias")
	private String alias;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="age")
	private String age;
	
	@Column(name="time")
	private String time;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;
	
	@Column(name="introduce")
	private String introduce;
	
	@Column(name="summary")
	private String summary;
	
	@Column(name="sight")
	private String sight;
	
	@Column(name="sets")
	private String sets;
	
	@Column(name="label")
	private String label;
	
	@Column(name="score")
	private double score;
	
	@Column(name="map_type")
	private String mapType;
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="langitude")
	private String longitude;

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

	public String getIsV()
	{
		return isV;
	}

	public void setIsV(String isV)
	{
		this.isV = isV;
	}

	public String getIsCoop()
	{
		return isCoop;
	}

	public void setIsCoop(String isCoop)
	{
		this.isCoop = isCoop;
	}

	public String getIsStop()
	{
		return isStop;
	}

	public void setIsStop(String isStop)
	{
		this.isStop = isStop;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCounty()
	{
		return county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	public String getClassOne()
	{
		return classOne;
	}

	public void setClassOne(String classOne)
	{
		this.classOne = classOne;
	}

	public String getClassTwo()
	{
		return classTwo;
	}

	public void setClassTwo(String classTwo)
	{
		this.classTwo = classTwo;
	}

	public String getClassThree()
	{
		return classThree;
	}

	public void setClassThree(String classThree)
	{
		this.classThree = classThree;
	}

	public String getClassFour()
	{
		return classFour;
	}

	public void setClassFour(String classFour)
	{
		this.classFour = classFour;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getIntroduce()
	{
		return introduce;
	}

	public void setIntroduce(String introduce)
	{
		this.introduce = introduce;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getSight()
	{
		return sight;
	}

	public void setSight(String sight)
	{
		this.sight = sight;
	}

	public String getSets()
	{
		return sets;
	}

	public void setSets(String sets)
	{
		this.sets = sets;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public double getScore()
	{
		return score;
	}

	public void setScore(double score)
	{
		this.score = score;
	}

	public String getMapType()
	{
		return mapType;
	}

	public void setMapType(String mapType)
	{
		this.mapType = mapType;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	
	
}
