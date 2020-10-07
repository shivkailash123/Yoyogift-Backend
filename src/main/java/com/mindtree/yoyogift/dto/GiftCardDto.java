package com.mindtree.yoyogift.dto;

public class GiftCardDto {

	private long userId;
	private float voucherAmount;
	private String reciverEmail;
	private String message;
	private long productId;
	private int productQuantity;

	@Override
	public String toString() {
		return "GiftCardDto [userId=" + userId + ", voucherAmount=" + voucherAmount + ", reciverEmail=" + reciverEmail
				+ ", message=" + message + ", productId=" + productId + ", productQuantity=" + productQuantity + "]";
	}

	public GiftCardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public float getVoucherAmount() {
		return voucherAmount;
	}

	public void setVoucherAmount(float voucherAmount) {
		this.voucherAmount = voucherAmount;
	}

	public String getReciverEmail() {
		return reciverEmail;
	}

	public void setReciverEmail(String reciverEmail) {
		this.reciverEmail = reciverEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public GiftCardDto(long userId, float voucherAmount, String reciverEmail, String message, long productId,
			int productQuantity) {
		super();
		this.userId = userId;
		this.voucherAmount = voucherAmount;
		this.reciverEmail = reciverEmail;
		this.message = message;
		this.productId = productId;
		this.productQuantity = productQuantity;
	}

}
