package com.mindtree.yoyogift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.yoyogift.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	public List<Product> findAllByIsDeleted(boolean isDeleted);
	public List<Product> findByProductName(String productName);
	public List<Product> findByImageUrl(String imageUrl);
	public List<Product> findByCategory(String category);
	public List<Product> findByDiscount(int discount);
	public List<Product> findByVendor(String vendor);
	public List<Product> findByPrice(float price);
	public List<Product> findByPriceLessThan(float price);
	public List<Product> findByPriceGreaterThan(float price);
	public List<Product> findByPriceBetween(float lowerLimit, float upperLimit);
	public List<Product> findByProductNameAndDescriptionAndImageUrl(String name, String description, String imageUrl);
}
