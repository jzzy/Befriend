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
@Table(name="city")
public class City  implements Serializable {



	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose private Integer id;
	@Column(name="name")
	@Expose private String name;
	@Column(name="sortmodel")
	@Expose private String sortmodel;
	public Integer getId() {
		return id;
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
	public String getSortmodel() {
		return sortmodel;
	}
	public void setSortmodel(String sortmodel) {
		this.sortmodel = sortmodel;
	}
	
	
}
