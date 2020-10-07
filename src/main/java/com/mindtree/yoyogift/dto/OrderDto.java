package com.mindtree.yoyogift.dto;



public class OrderDto {
	
	private long orderId;
    private int productQuantity;
    private float amount;
    private String userDate;
    private long productId;
    private boolean feedback;
    public boolean isFeedback() {
		return feedback;
	}
	public void setFeedback(boolean feedback) {
		this.feedback = feedback;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	private String productName;
    private String vendor;
    private String imageUrl;
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDto(long orderId, int productQuantity, float amount,String userDate, String productName, String vendor,
			String imageUrl,long productId) {
		super();
		this.orderId = orderId;
		this.productQuantity = productQuantity;
		this.amount=amount;
		this.userDate = userDate;
		this.productName = productName;
		this.vendor = vendor;
		this.imageUrl = imageUrl;
		this.productId=productId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
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
	
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
    

}
