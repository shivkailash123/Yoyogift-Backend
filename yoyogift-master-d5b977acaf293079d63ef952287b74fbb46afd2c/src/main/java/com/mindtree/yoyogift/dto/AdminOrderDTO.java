package com.mindtree.yoyogift.dto;

import java.util.Date;

public class AdminOrderDTO {
	private long orderId;
	private long orderQuantity;
	private float amount;
	private Date orderDate;
	private String userEmail;
	private long productId;

	public AdminOrderDTO() {
		super();
	}

	public AdminOrderDTO(long orderId, long orderQuantity, float amount, Date orderDate, String userEmail,
			long productId) {
		super();
		this.orderId = orderId;
		this.orderQuantity = orderQuantity;
		this.amount = amount;
		this.orderDate = orderDate;
		this.userEmail = userEmail;
		this.productId = productId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(long orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + (int) (orderQuantity ^ (orderQuantity >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
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
		AdminOrderDTO other = (AdminOrderDTO) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderQuantity != other.orderQuantity)
			return false;
		if (productId != other.productId)
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdminOrderDTO [orderId=" + orderId + ", orderQuantity=" + orderQuantity + ", amount=" + amount
				+ ", orderDate=" + orderDate + ", userEmail=" + userEmail + ", productId=" + productId + "]";
	}

}
