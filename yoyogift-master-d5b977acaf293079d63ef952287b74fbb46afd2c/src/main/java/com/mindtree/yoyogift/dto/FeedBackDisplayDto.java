package com.mindtree.yoyogift.dto;
import java.util.Date;
public class FeedBackDisplayDto 
{
	private int ratingPoint;
	private String review;
	private String name;
	private Date date;
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
	public FeedBackDisplayDto(int ratingPoint, String review, String name, Date date) {
		super();
		this.ratingPoint = ratingPoint;
		this.review = review;
		this.name = name;
		this.date = date;
	}
	public FeedBackDisplayDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "FeedBackDisplayDto [ratingPoint=" + ratingPoint + ", review=" + review + ", name=" + name + ", date="
				+ date + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ratingPoint;
		result = prime * result + ((review == null) ? 0 : review.hashCode());
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
		FeedBackDisplayDto other = (FeedBackDisplayDto) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ratingPoint != other.ratingPoint)
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		return true;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
		
}
