package com.mindtree.yoyogift.dto;

public class UserCredentialsDTO {

	String email;
	boolean socialFlag;
	public UserCredentialsDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		
	}

	String password;

	public UserCredentialsDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSocialFlag() {
		return socialFlag;
	}

	public void setSocialFlag(boolean socialFlag) {
		this.socialFlag = socialFlag;
	}

}
