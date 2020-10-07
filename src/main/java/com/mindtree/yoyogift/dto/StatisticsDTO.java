package com.mindtree.yoyogift.dto;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDTO {

	private List<String> productCategories = new ArrayList<>();
	private List<Integer> productCountByCategory = new ArrayList<>();
	private List<String> usersJoiningDate = new ArrayList<>();
	private List<Integer> usersJoiningDateCount = new ArrayList<>();
	private List<String> topSellingProducts = new ArrayList<>();
	private List<Integer> topSellingProductsCount = new ArrayList<>();
	private List<String> purchaseTimes = new ArrayList<>();
	private List<Integer> purchaseTimesCount = new ArrayList<>();

	public StatisticsDTO() {
		super();
	}

	public StatisticsDTO(List<String> productCategories, List<Integer> productCountByCategory,
			List<String> usersJoiningDate, List<Integer> usersJoiningDateCount, List<String> topSellingProducts,
			List<Integer> topSellingProductsCount, List<String> purchaseTimes, List<Integer> purchaseTimesCount) {
		super();
		this.productCategories = productCategories;
		this.productCountByCategory = productCountByCategory;
		this.usersJoiningDate = usersJoiningDate;
		this.usersJoiningDateCount = usersJoiningDateCount;
		this.topSellingProducts = topSellingProducts;
		this.topSellingProductsCount = topSellingProductsCount;
		this.purchaseTimes = purchaseTimes;
		this.purchaseTimesCount = purchaseTimesCount;
	}

	public List<String> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<String> productCategories) {
		this.productCategories = productCategories;
	}

	public List<Integer> getProductCountByCategory() {
		return productCountByCategory;
	}

	public void setProductCountByCategory(List<Integer> productCountByCategory) {
		this.productCountByCategory = productCountByCategory;
	}

	public List<String> getUsersJoiningDate() {
		return usersJoiningDate;
	}

	public void setUsersJoiningDate(List<String> usersJoiningDate) {
		this.usersJoiningDate = usersJoiningDate;
	}

	public List<Integer> getUsersJoiningDateCount() {
		return usersJoiningDateCount;
	}

	public void setUsersJoiningDateCount(List<Integer> usersJoiningDateCount) {
		this.usersJoiningDateCount = usersJoiningDateCount;
	}

	public List<String> getTopSellingProducts() {
		return topSellingProducts;
	}

	public void setTopSellingProducts(List<String> topSellingProducts) {
		this.topSellingProducts = topSellingProducts;
	}

	public List<Integer> getTopSellingProductsCount() {
		return topSellingProductsCount;
	}

	public void setTopSellingProductsCount(List<Integer> topSellingProductsCount) {
		this.topSellingProductsCount = topSellingProductsCount;
	}

	public List<String> getPurchaseTimes() {
		return purchaseTimes;
	}

	public void setPurchaseTimes(List<String> purchaseTimes) {
		this.purchaseTimes = purchaseTimes;
	}

	public List<Integer> getPurchaseTimesCount() {
		return purchaseTimesCount;
	}

	public void setPurchaseTimesCount(List<Integer> purchaseTimesCount) {
		this.purchaseTimesCount = purchaseTimesCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productCategories == null) ? 0 : productCategories.hashCode());
		result = prime * result + ((productCountByCategory == null) ? 0 : productCountByCategory.hashCode());
		result = prime * result + ((purchaseTimes == null) ? 0 : purchaseTimes.hashCode());
		result = prime * result + ((purchaseTimesCount == null) ? 0 : purchaseTimesCount.hashCode());
		result = prime * result + ((topSellingProducts == null) ? 0 : topSellingProducts.hashCode());
		result = prime * result + ((topSellingProductsCount == null) ? 0 : topSellingProductsCount.hashCode());
		result = prime * result + ((usersJoiningDate == null) ? 0 : usersJoiningDate.hashCode());
		result = prime * result + ((usersJoiningDateCount == null) ? 0 : usersJoiningDateCount.hashCode());
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
		StatisticsDTO other = (StatisticsDTO) obj;
		if (productCategories == null) {
			if (other.productCategories != null)
				return false;
		} else if (!productCategories.equals(other.productCategories))
			return false;
		if (productCountByCategory == null) {
			if (other.productCountByCategory != null)
				return false;
		} else if (!productCountByCategory.equals(other.productCountByCategory))
			return false;
		if (purchaseTimes == null) {
			if (other.purchaseTimes != null)
				return false;
		} else if (!purchaseTimes.equals(other.purchaseTimes))
			return false;
		if (purchaseTimesCount == null) {
			if (other.purchaseTimesCount != null)
				return false;
		} else if (!purchaseTimesCount.equals(other.purchaseTimesCount))
			return false;
		if (topSellingProducts == null) {
			if (other.topSellingProducts != null)
				return false;
		} else if (!topSellingProducts.equals(other.topSellingProducts))
			return false;
		if (topSellingProductsCount == null) {
			if (other.topSellingProductsCount != null)
				return false;
		} else if (!topSellingProductsCount.equals(other.topSellingProductsCount))
			return false;
		if (usersJoiningDate == null) {
			if (other.usersJoiningDate != null)
				return false;
		} else if (!usersJoiningDate.equals(other.usersJoiningDate))
			return false;
		if (usersJoiningDateCount == null) {
			if (other.usersJoiningDateCount != null)
				return false;
		} else if (!usersJoiningDateCount.equals(other.usersJoiningDateCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatisticsDTO [productCategories=" + productCategories + ", productCountByCategory="
				+ productCountByCategory + ", usersJoiningDate=" + usersJoiningDate + ", usersJoiningDateCount="
				+ usersJoiningDateCount + ", topSellingProducts=" + topSellingProducts + ", topSellingProductsCount="
				+ topSellingProductsCount + ", purchaseTimes=" + purchaseTimes + ", purchaseTimesCount="
				+ purchaseTimesCount + "]";
	}

}
