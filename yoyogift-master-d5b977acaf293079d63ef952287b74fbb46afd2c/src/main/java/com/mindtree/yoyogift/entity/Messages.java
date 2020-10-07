package com.mindtree.yoyogift.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Messages {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long messageId;
	@OneToOne
	private User sender;
	@OneToOne(fetch = FetchType.EAGER)
	private User receiver;
	@OneToOne(fetch = FetchType.LAZY, mappedBy ="message")
	private Coupouns coupouns;
	private String content;
	
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private Date date;
	
	
	
	
	
	
}
