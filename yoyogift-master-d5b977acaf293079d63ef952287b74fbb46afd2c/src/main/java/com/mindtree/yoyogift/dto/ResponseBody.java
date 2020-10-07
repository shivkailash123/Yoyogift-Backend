package com.mindtree.yoyogift.dto;

import java.util.Date;

public class ResponseBody<T> {

	private Date responseTime;
	private ErrorDto eror;
	private boolean success;
	private T response;
	
	public ResponseBody() {
		super();
	}
	public ResponseBody(ErrorDto eror, boolean success, T response) {
		super();
		this.setResponseTime(new Date());
		this.eror = eror;
		this.success = success;
		this.response = response;
	}
	public ErrorDto getEror() {
		return eror;
	}
	public void setEror(ErrorDto eror) {
		this.eror = eror;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
}
