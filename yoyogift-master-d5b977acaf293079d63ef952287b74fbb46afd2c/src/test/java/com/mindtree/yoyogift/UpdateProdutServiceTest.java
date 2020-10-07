package com.mindtree.yoyogift;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.service.ProductService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UpdateProdutServiceTest {
	
	@Autowired
	ProductService  productService;
	
	@MockBean
	ProductRepository productRepository;
	 
	
	@BeforeEach
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	   
	
	
	@Test
	public void UpdateProductPositiveTest()
	{
		Product product =new Product();
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(290);
		product.setCategory("Bracelette");
		product.setDescription("A Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz.jpg");
		product.setVendor("walmart");
		 Optional<Product> productOptional = Optional.of(product);
		   Mockito.lenient().when(productRepository.findById((long) 1)).thenReturn(productOptional);
		
		
		try {
			productService.updateProduct(product);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void UpdateProductNegativeTest()
	{
        Product product =new Product();
        product.setProductId(1);
		product.setProductName("1estMother");
		product.setPrice(290);
		product.setCategory("Bracelette");
		product.setDescription("A Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz.jpg");
		product.setVendor("walmart");
		 Optional<Product> productOptional = Optional.of(product);
		   Mockito.lenient().when(productRepository.findById((long) 1)).thenReturn(productOptional);
		
		
		try {
			productService.updateProduct(product);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("PRODUCT NAME MUST ONLY CONTAINS ALPHABETS", e.getMessage());
		}
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(1);
		product.setCategory("1@racelette");
		product.setDescription("A Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz.png");
		product.setVendor("walmart");
		try {
			productService.updateProduct(product);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("CATEGORY MUST ONLY CONTAINS ALPHABETS", e.getMessage());
		}
		product.setProductName("BestMother");
		product.setPrice(1);
		product.setCategory("Bracelette");
		product.setDescription("@ @racelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz.jpg");
		product.setVendor("walmart");
		
		try {
			productService.updateProduct(product);
			
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("DESCRIPTION MUST ONLY CONTAINS ALPHABETS", e.getMessage());
		}
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(1);
		product.setCategory("Bracelette");
		product.setDescription("Bracelette for your Mom");
		product.setDiscount(100);
		product.setStock(20);
		product.setImageUrl("xyz.jpg");
		product.setVendor("walmart");
		try {
			productService.updateProduct(product);
			
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("Discount cannot  more than 90%", e.getMessage());
		}
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(-1);
		product.setCategory("Bracelette");
		product.setDescription("@ @racelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz.jpg");
		product.setVendor("walmart");
		try {
			productService.updateProduct(product);
			
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("Price cannot be negative", e.getMessage());
			
			
		}
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(12);
		product.setCategory("Bracelette");
		product.setDescription("Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz.jpg");
		product.setVendor("@1223");
		try {
			productService.updateProduct(product);
			
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("VENDOR MUST ONLY CONTAINS ALPHABETS", e.getMessage());
			
			
		}
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(12);
		product.setCategory("Bracelette");
		product.setDescription("Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(-1);
		product.setImageUrl("xyz.jpg");
		product.setVendor("walmart");
		
		try {
			productService.updateProduct(product);
			
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("stock cannot be negative", e.getMessage());
			
			
		}
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(12);
		product.setCategory("Bracelette");
		product.setDescription("Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(-1);
		product.setImageUrl("xyz");
		product.setVendor("walmart");
		
		try {
			productService.updateProduct(product);
			
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("Invalid ImageURL", e.getMessage());
			
			
		}
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(12);
		product.setCategory("Bracelette");
		product.setDescription("Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(-1);
		product.setImageUrl("xyz.pdf");
		product.setVendor("walmart");
		
		try {
			productService.updateProduct(product);
			
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("Image can be only in .jpg/.png Format", e.getMessage());
			
			
		}
		
		
		
		
	}

}

