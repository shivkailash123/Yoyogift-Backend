package com.mindtree.yoyogift.entity;





import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	private int productQuantity;
	private float amount;
	private Date orderDate;
	@ManyToOne(fetch = FetchType.EAGER)
	User user;
	@OneToOne(fetch = FetchType.EAGER)
	Product product;
	public Orders() {
		super();
	}
	public Orders(long orderId, int productQuantity, float amount, Date orderDate, User user) {
		super();
		this.orderId = orderId;
		this.productQuantity = productQuantity;
		this.amount = amount;
		this.orderDate = orderDate;
		this.user = user;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	

}
