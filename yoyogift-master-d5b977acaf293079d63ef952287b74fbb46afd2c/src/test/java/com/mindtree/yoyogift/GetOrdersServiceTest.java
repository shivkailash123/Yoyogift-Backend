package com.mindtree.yoyogift;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.ProductOrderService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GetOrdersServiceTest {
	
	@Autowired
	ProductOrderService productOrderService;
	
	@MockBean
	OrderRepository orderRepository;
	
	@MockBean
	FeedBackRepository feedbackRepository;
	
	@MockBean
	UserRepository userRepository;
	
	
	 @BeforeEach
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	 
	 @Test
	 public void GetOrderPositiveTest()
	 {
		 User user=new User();
		    user.setUserId(1);
		   user.setEmail("ankit.prusty@yoyogift.com");
		   user.setName("Ankit Prusty");
		   user.setPhoneNo("9438359603");
		   user.setPassword("Ankit@1998");
		   User user1=new User();
		   user1.setUserId(2);
		   user1.setEmail("ankit.prusty1998@yoyogift.com");
		   user1.setName("Ravi Shankar");
		   user1.setPhoneNo("9438359602");
		   user1.setPassword("Ankit@1998");
		   List<User> u=new ArrayList<User>();
		   u.add(user);
		   u.add(user1);
		  
		   
		    Product product=new Product();
		    product.setProductId(1);
		    product.setProductName("BestMother");
			product.setPrice(290);
			product.setCategory("Bracelette");
			product.setDescription("A Bracelette for your Mom");
			product.setDiscount(10);
			product.setStock(20);
			product.setImageUrl("xyz");
			product.setVendor("walmart");
			 
			
			 Product product1=new Product();
			 product1.setProductId(2);
			 product1.setProductName("Amazon Gift Card");
		     product1.setPrice(0);
			 product1.setCategory("Gift Card");
			 product1.setDescription("A Bracelette for your Mom");
			 product1.setDiscount(10);
			 product1.setStock(20);
			 product1.setImageUrl("xyz");
			 product1.setVendor("walmart");
				
			
			
		   Orders order=new Orders();
		   order.setOrderId(1);
		   order.setAmount(290);
		   order.setProductQuantity(1);
		   order.setProduct(product);
		   order.setUser(user);
		   order.setOrderDate(new Date());
		   
		   Orders order1=new Orders();
		   order1.setOrderId(2);
		   order1.setAmount(500);
		   order1.setProductQuantity(1);
		   order1.setProduct(product1);
		   order1.setUser(user);
		   order1.setOrderDate(new Date());
		   
		   
		   List<Orders> orders=new ArrayList<Orders>();
		   orders.add(order);
		   orders.add(order1);
		  
		   
		    FeedBack feedback=new FeedBack();
		    feedback.setFeedBackId(1);
		    feedback.setRatingPoint(4);
		    feedback.setOrder(order);
		    feedback.setUser(user);
		    feedback.setProduct(product);
		    feedback.setReview("hello");
		    
		    FeedBack feedback1=new FeedBack();
		    feedback1.setFeedBackId(2);
		    feedback1.setRatingPoint(3);
		    feedback1.setOrder(order1);
		    feedback1.setUser(user);
		    feedback1.setProduct(product1);
		    feedback1.setReview("nice");
		    
		    List<FeedBack> feedbacks=new ArrayList<FeedBack>();
		    feedbacks.add(feedback);
		    feedbacks.add(feedback1);
		    Mockito.lenient().when(this.userRepository.findAll()).thenReturn(u);
		    Mockito.lenient().when(this.feedbackRepository.findAll()).thenReturn(feedbacks);
		    Mockito.lenient().when(this.orderRepository.findAll()).thenReturn(orders);
		    
		    productOrderService.getOrders("ankit.prusty@yoyogift.com");
		   
		   
		   
		 
	 }

}
