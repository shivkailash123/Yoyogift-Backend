package com.mindtree.yoyogift;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mindtree.yoyogift.controller.YoYoGift;
import com.mindtree.yoyogift.dto.OrderDto;
import com.mindtree.yoyogift.dto.ResponseBody;
import com.mindtree.yoyogift.dto.UpdateProductDto;
import com.mindtree.yoyogift.dto.UserDTO;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.service.ProductOrderService;
import com.mindtree.yoyogift.service.ProductService;
import com.mindtree.yoyogift.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ControllerTest {
	
	@Autowired
	private YoYoGift controller;
	@MockBean
	ModelMapper modelMapper;

	private ModelMapper modelMappe1=new ModelMapper();
	
	@MockBean
	ProductService productService;
	
	@MockBean
	UserService userService;
	
	
	@MockBean
	ProductOrderService productOrderService;
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void  addProductTest() throws AppException
	{
		Product product =new Product();
		product.setProductName("BestMother");
		product.setPrice(290);
		product.setCategory("Bracelette");
		product.setDescription("A Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz");
		product.setVendor("walmart");
		Mockito.lenient().when(this.productService.addProduct(product)).thenReturn(product);
		
		ResponseEntity<?> expected =new ResponseEntity<>(productService.addProduct(product),HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.addProduct(product);
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
		}
	
	@Test
	public void addUserTest() throws AppException
	{
		   UserDTO userDto=new UserDTO();
		   userDto.setUserId(1);
		   userDto.setEmail("ankit.prusty");
		   userDto.setName("Ankit Prusty");
		   userDto.setPhoneNo("9438359603");
		   userDto.setPassword("Ankit@1998");
		   userDto.setReferralCode("ihcfdcgcdg");
		   
		   User user2=modelMappe1.map(userDto,User.class);
		   Mockito.lenient().when(this.userService.addUser(user2,userDto.getReferralCode())).thenReturn(user2);
		   user2.setPassword(null);
		   ResponseEntity<?> expected=new ResponseEntity<ResponseBody<UserDTO>>(
					new ResponseBody<UserDTO>(null, true, modelMappe1.map(user2,UserDTO.class)), HttpStatus.OK);
			
			ResponseEntity<?> actual=this.controller.addUserDetails(userDto);
			Assertions.assertEquals(expected.getStatusCode(), actual.getStatusCode());
}
	
	@Test
	public void UpdateProduct() throws AppException
	{
		UpdateProductDto product=new UpdateProductDto();
		product.setProductId(1);
		product.setProductName("BestMother");
		product.setPrice(290);
		product.setCategory("Bracelette");
		product.setDescription("A Bracelette for your Mom");
		product.setDiscount(10);
		product.setStock(20);
		product.setImageUrl("xyz");
		product.setVendor("walmart");
		
		Product product1=modelMappe1.map(product,Product.class);
		
		Mockito.lenient().when(productService.updateProduct(product1)).thenReturn(product1);
	    ResponseEntity<?> expected=new ResponseEntity<ResponseBody<UpdateProductDto>>(new ResponseBody<UpdateProductDto>(null,true,modelMappe1.map(product1, UpdateProductDto.class)), HttpStatus.OK);
		ResponseEntity<?> actual=this.controller.updateProduct(product);
		Assertions.assertEquals(expected.getStatusCode(), actual.getStatusCode());
		
		
	}
	
	@Test
	public void getAllOrders()
	{
		
		List<OrderDto> orders=new ArrayList<OrderDto>();
		
		OrderDto order=new OrderDto();
		orders.add(order);
		Mockito.lenient().when(productOrderService.getOrders("ankit.prusty@yoyogift.com")).thenReturn(orders);
		
		ResponseEntity<?> expected =new ResponseEntity<>(productOrderService.getOrders("ankit.prusty@yoyogift.com"),HttpStatus.OK);
		ResponseEntity<?> actual=this.controller.getOrders("ankit.prusty@yoyogift.com");
		Assertions.assertEquals(expected.getStatusCode(), actual.getStatusCode());
		
		
	}

	
	

}
