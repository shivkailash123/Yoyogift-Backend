package com.mindtree.yoyogift.dto;

public class ProfileDTO {

	private long userId;
	private String name;
	private String phoneNo;
	private String email;
	private String password;
	private Double wallet;

	public ProfileDTO() {
		super();
	}
	public ProfileDTO(long userId, String name, String phoneNo, String email, String password, Double wallet) {
		super();
		this.userId = userId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.wallet = wallet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Double getWallet() {
		return wallet;
	}
	public void setWallet(Double wallet) {
		this.wallet = wallet;
	}
	
	

}
