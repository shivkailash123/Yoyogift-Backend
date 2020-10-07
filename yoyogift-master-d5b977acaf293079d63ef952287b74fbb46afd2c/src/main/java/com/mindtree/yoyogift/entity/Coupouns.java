package com.mindtree.yoyogift.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Coupouns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String coupounCode;
	private float amount;
	private Date dateOfExpiry;
	private boolean isRedeemed;
	private Date redeemedOn;
	@OneToOne(fetch = FetchType.EAGER)
	User reedemedBy;
	@OneToOne(fetch = FetchType.EAGER)
	Messages message;

	public Coupouns() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupouns(long id, String coupounCode, float amount, Date dateOfExpiry, boolean isRedeemed, Date redeemedOn,
			User reedemedBy, Messages message) {
		super();
		this.id = id;
		this.coupounCode = coupounCode;
		this.amount = amount;
		this.dateOfExpiry = dateOfExpiry;
		this.isRedeemed = isRedeemed;
		this.redeemedOn = redeemedOn;
		this.reedemedBy = reedemedBy;
		this.message = message;
	}

//	public Coupouns() {
//		super();
//	}
//	public Coupouns(long id, String coupounCode, float amount, Date dateOfExpiry, boolean isRedeemed, Date redeemedOn,
//			User reedemedBy) {
//		super();
//		this.id = id;
//		this.coupounCode = coupounCode;
//		this.amount = amount;
//		this.dateOfExpiry = dateOfExpiry;
//		this.isRedeemed = isRedeemed;
//		this.redeemedOn = redeemedOn;
//		this.reedemedBy = reedemedBy;
//	}
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getCoupounCode() {
//		return coupounCode;
//	}
//	public void setCoupounCode(String coupounCode) {
//		this.coupounCode = coupounCode;
//	}
//	public double getAmount() {
//		return amount;
//	}
//	public void setAmount(float voucheramount) {
//		this.amount = voucheramount;
//	}
//	public Date getDateOfExpiry() {
//		return dateOfExpiry;
//	}
//	public void setDateOfExpiry(java.util.Date dateVar1) {
//		this.dateOfExpiry = dateVar1;
//	}
//	public boolean isRedeemed() {
//		return isRedeemed;
//	}
//	public void setRedeemed(boolean isRedeemed) {
//		this.isRedeemed = isRedeemed;
//	}
//	public Date getRedeemedOn() {
//		return redeemedOn;
//	}
//	public void setRedeemedOn(Date redeemedOn) {
//		this.redeemedOn = redeemedOn;
//	}
//	public User getReedemedBy() {
//		return reedemedBy;
//	}
//	public void setReedemedBy(User reedemedBy) {
//		this.reedemedBy = reedemedBy;
//	}
//;
//	
//	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCoupounCode() {
		return coupounCode;
	}

	public void setCoupounCode(String coupounCode) {
		this.coupounCode = coupounCode;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public boolean isRedeemed() {
		return isRedeemed;
	}

	public void setRedeemed(boolean isRedeemed) {
		this.isRedeemed = isRedeemed;
	}

	public Date getRedeemedOn() {
		return redeemedOn;
	}

	public void setRedeemedOn(Date redeemedOn) {
		this.redeemedOn = redeemedOn;
	}

	public User getReedemedBy() {
		return reedemedBy;
	}

	public void setReedemedBy(User reedemedBy) {
		this.reedemedBy = reedemedBy;
	}

	public Messages getMessage() {
		return message;
	}

	public void setMessage(Messages message) {
		this.message = message;
	}

}
