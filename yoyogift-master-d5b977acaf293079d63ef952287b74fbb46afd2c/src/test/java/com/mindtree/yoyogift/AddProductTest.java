package com.mindtree.yoyogift;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class AddProductTest {
	
	@Autowired
	ProductService productService;
	
	@MockBean
	ProductRepository productRepository;
	
	   @BeforeEach
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	   
	   @Test
	   public void AddProductPostiveTest()
	   {
		   Product product =new Product();
			
			product.setProductName("BestMother");
			product.setPrice(290);
			product.setCategory("Bracelette");
			product.setDescription("A Bracelette for your Mom");
			product.setDiscount(10);
			product.setStock(20);
			product.setImageUrl("xyz.jpg");
			product.setVendor("walmart");
			
			
			try {
				productService.addProduct(product);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   
	   @Test
	   public void AddProductNegativeTest()
	   {
		   Product product=new Product();
		     product.setProductName("BestMother");
			product.setPrice(0);
			product.setCategory("Bracelette");
			product.setDescription("Bracelette for your Mom");
			product.setDiscount(10);
			product.setStock(20);
			product.setImageUrl("xyz.jpg");
			product.setVendor("walmart");
			
			try {
				productService.addProduct(product);
				
			}
			catch (ServiceException e) {
				// TODO Auto-generated catch block
				Assertions.assertEquals("Price must be 1 or More", e.getMessage());
				
				
			}
			    product.setProductName("BestMother");
				product.setPrice(10);
				product.setCategory("Bracelette");
				product.setDescription("Bracelette for your Mom");
				product.setDiscount(100);
				product.setStock(20);
				product.setImageUrl("xyz.jpg");
				product.setVendor("walmart");
				try {
					productService.addProduct(product);
					
				}
				catch (ServiceException e) {
					// TODO Auto-generated catch block
					Assertions.assertEquals("Discount Percent can not be more than 90", e.getMessage());
					
					
				}
				 product.setProductName("BestMother");
					product.setPrice(10);
					product.setCategory("Bracelette");
					product.setDescription("Bracelette for your Mom");
					product.setDiscount(90);
					product.setStock(0);
					product.setImageUrl("xyz.png");
					product.setVendor("walmart");
					
					
					String img=product.getImageUrl();
					String check=img.substring(img.length()-4,img.length());
					System.out.println(check);
					try {
						productService.addProduct(product);
						
					}
					catch (ServiceException e) {
						// TODO Auto-generated catch block
						Assertions.assertEquals("Stock Cannot Be Zero or negative", e.getMessage());
						
						
					}
				product.setProductName("BestMother");
				product.setPrice(10);
				product.setCategory("Bracelette");
				product.setDescription("Bracelette for your Mom");
				product.setDiscount(0);
				product.setStock(20);
				product.setImageUrl("xyz.jpg");
				product.setVendor("walmart");
				try {
					productService.addProduct(product);
					
				}
				catch (ServiceException e) {
					// TODO Auto-generated catch block
					Assertions.assertEquals("Discount Percent can not be less than 1", e.getMessage());
					
					
				}
				product.setProductName("BestMother");
				product.setPrice(10);
				product.setCategory("Bracelette");
				product.setDescription("Bracelette for your Mom");
				product.setDiscount(10);
				product.setStock(20);
				product.setImageUrl("xyz");
				product.setVendor("walmart");
				try {
					productService.addProduct(product);
					
				}
				catch (ServiceException e) {
					// TODO Auto-generated catch block
					Assertions.assertEquals("Invalid ImageURL", e.getMessage());
					
					
				}
				
				product.setProductName("BestMother");
				product.setPrice(10);
				product.setCategory("Bracelette");
				product.setDescription("Bracelette for your Mom");
				product.setDiscount(10);
				product.setStock(20);
				product.setImageUrl("xyz.pdf");
				product.setVendor("walmart");
				try {
					productService.addProduct(product);
					
				}
				catch (ServiceException e) {
					// TODO Auto-generated catch block
					Assertions.assertEquals("Image can be only in .jpg/.png Format", e.getMessage());
					
					
				}
			
			
	   }

}
