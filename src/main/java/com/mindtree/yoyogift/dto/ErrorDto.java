package com.mindtree.yoyogift.dto;

public class ErrorDto {
	
	private String message;

	public ErrorDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public ErrorDto() {
		super();
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
