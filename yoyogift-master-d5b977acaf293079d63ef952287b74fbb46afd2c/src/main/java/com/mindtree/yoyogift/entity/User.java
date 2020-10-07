package com.mindtree.yoyogift.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String name;
	private String email;
	private String password;
	private String phoneNo;
	private Date date=new Date();
	private Double wallet;
	
	@JsonIgnore
	@OneToMany(fetch =FetchType.LAZY,mappedBy = "user")
	List<Orders>orders =new ArrayList<Orders>();
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="reedemedBy")
	List<Coupouns> coupoun =new ArrayList<Coupouns>();
	public User() {
		super();
	}

	public User(long userId, String name, String email, String password, String phoneNo, Date date, Double wallet,
			List<Orders> orders, List<Coupouns> coupoun) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.date = date;
		this.wallet = wallet;
		this.orders = orders;
		this.coupoun = coupoun;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getWallet() {
		return wallet;
	}
	public void setWallet(Double wallet) {
		this.wallet = wallet;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Coupouns> getCoupoun() {
		return coupoun;
	}

	public void setCoupoun(List<Coupouns> coupoun) {
		this.coupoun = coupoun;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNo=" + phoneNo + ", date=" + date + ", wallet=" + wallet + ", orders=" + orders + ", coupoun="
				+ coupoun + "]";
	}

	
}
