package com.mindtree.yoyogift.dto;

import java.util.Date;

public class RedeemableGiftCardDTO {
	private long id;
	private String coupounCode;
	private float amount;
	private Date dateOfExpiry;
	private boolean isRedeemed;
	private Date redeemedOn;
	private String message;
	private String senderEmail;
	private double walletBalance;

	public RedeemableGiftCardDTO() {
		super();
	}

	public RedeemableGiftCardDTO(long id, String coupounCode, float amount, Date dateOfExpiry, boolean isRedeemed,
			Date redeemedOn, String message, String senderEmail, double wallerBalance) {
		super();
		this.id = id;
		this.coupounCode = coupounCode;
		this.amount = amount;
		this.dateOfExpiry = dateOfExpiry;
		this.isRedeemed = isRedeemed;
		this.redeemedOn = redeemedOn;
		this.message = message;
		this.senderEmail = senderEmail;
		this.walletBalance = wallerBalance;
	}

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((coupounCode == null) ? 0 : coupounCode.hashCode());
		result = prime * result + ((dateOfExpiry == null) ? 0 : dateOfExpiry.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isRedeemed ? 1231 : 1237);
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((redeemedOn == null) ? 0 : redeemedOn.hashCode());
		result = prime * result + ((senderEmail == null) ? 0 : senderEmail.hashCode());
		long temp;
		temp = Double.doubleToLongBits(walletBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RedeemableGiftCardDTO other = (RedeemableGiftCardDTO) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (coupounCode == null) {
			if (other.coupounCode != null)
				return false;
		} else if (!coupounCode.equals(other.coupounCode))
			return false;
		if (dateOfExpiry == null) {
			if (other.dateOfExpiry != null)
				return false;
		} else if (!dateOfExpiry.equals(other.dateOfExpiry))
			return false;
		if (id != other.id)
			return false;
		if (isRedeemed != other.isRedeemed)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (redeemedOn == null) {
			if (other.redeemedOn != null)
				return false;
		} else if (!redeemedOn.equals(other.redeemedOn))
			return false;
		if (senderEmail == null) {
			if (other.senderEmail != null)
				return false;
		} else if (!senderEmail.equals(other.senderEmail))
			return false;
		if (Double.doubleToLongBits(walletBalance) != Double.doubleToLongBits(other.walletBalance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RedeemableGiftCardDTO [id=" + id + ", coupounCode=" + coupounCode + ", amount=" + amount
				+ ", dateOfExpiry=" + dateOfExpiry + ", isRedeemed=" + isRedeemed + ", redeemedOn=" + redeemedOn
				+ ", message=" + message + ", senderEmail=" + senderEmail + ", walletBalance=" + walletBalance + "]";
	}

}
