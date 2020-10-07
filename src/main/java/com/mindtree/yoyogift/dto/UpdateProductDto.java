package com.mindtree.yoyogift.dto;

public class UpdateProductDto {
	private long productId;
	private String productName;
	private String category;
	private float price;
	private String description;
	private int stock;
	private int discount;
	private String vendor;
	private String imageUrl;
	public UpdateProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateProductDto(long productId, String productName, String category, float price, String description,
			int stock, int discount, String vendor, String imageUrl) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.discount = discount;
		this.vendor = vendor;
		this.imageUrl = imageUrl;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
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
