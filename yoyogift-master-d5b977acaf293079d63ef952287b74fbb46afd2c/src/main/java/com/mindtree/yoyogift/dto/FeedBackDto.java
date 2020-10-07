package com.mindtree.yoyogift.dto;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
public class FeedBackDto 
{
	@NotNull(message="Rating Point Is Not Be Null")
	@Min(value=1,message="Rating Point Is Not Be Less Than 1")
	@Max(value=5, message="Rating Point Is Not Be More Than 5")
	private int ratingPoint;
	private String review;
	private long feedbackId;
	public int getRatingPoint() {
		return ratingPoint;
	}
	public void setRatingPoint(int ratingPoint) {
		this.ratingPoint = ratingPoint;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public long getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}
	@Override
	public String toString() {
		return "FeedBackDto [ratingPoint=" + ratingPoint + ", review=" + review + ", feedbackId=" + feedbackId + "]";
	}
	public FeedBackDto(@NotNull(message = "Rating Point is Not Be Null") int ratingPoint, String review,
			long feedbackId) {
		super();
		this.ratingPoint = ratingPoint;
		this.review = review;
		this.feedbackId = feedbackId;
	}
	public FeedBackDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	




}