package com.mindtree.yoyogift.entity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FeedBack {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long feedBackId;
	private int ratingPoint;
	private Date date=new Date();
	private String review;
	@OneToOne(fetch = FetchType.EAGER)
	User user;
	@OneToOne( fetch = FetchType.EAGER)
	Product product;
	@OneToOne( fetch = FetchType.EAGER)
	Orders order;
	public long getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(long feedBackId) {
		this.feedBackId = feedBackId;
	}
	public int getRatingPoint() {
		return ratingPoint;
	}
	public void setRatingPoint(int ratingPoint) {
		this.ratingPoint = ratingPoint;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
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
	@Override
	public String toString() {
		return "FeedBack [feedBackId=" + feedBackId + ", ratingPoint=" + ratingPoint + ", date=" + date + ", review="
				+ review + ", user=" + user + ", product=" + product + ", order=" + order + "]";
	}
	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeedBack(long feedBackId, int ratingPoint, Date date, String review, User user, Product product,
			Orders order) {
		super();
		this.feedBackId = feedBackId;
		this.ratingPoint = ratingPoint;
		this.date = date;
		this.review = review;
		this.user = user;
		this.product = product;
		this.order = order;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	


}
