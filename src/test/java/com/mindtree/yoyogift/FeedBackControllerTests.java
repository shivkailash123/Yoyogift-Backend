package com.mindtree.yoyogift;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.reflect.TypeToken;
import com.mindtree.yoyogift.controller.YoYoGift;
import com.mindtree.yoyogift.dto.FeedBackDisplayDto;
import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.ResponseBody;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.service.serviceimpl.FeedBackServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FeedBackControllerTests 
{
@MockBean
ModelMapper modelMapper;

private ModelMapper model=new ModelMapper();

@Autowired
private YoYoGift controller;

@MockBean
FeedBackServiceImplementation feedBackService;

@SuppressWarnings("serial")
@Test
public void getAllFeedBackDetailsTests() throws AppException
{
	List<FeedBackDisplayDto> feedBackDisplayDto = new ArrayList<FeedBackDisplayDto>();
	long id=100;
	Mockito.lenient().when(feedBackService.displayAllReviewsFromDb(id)).thenReturn(feedBackDisplayDto);
	ResponseBody<List<FeedBackDisplayDto>> responseBody = new ResponseBody<List<FeedBackDisplayDto>>(null, true,
			modelMapper.map(feedBackService.displayAllReviewsFromDb(id), new TypeToken<List<FeedBackDisplayDto>>() {
			}.getType()));
	ResponseEntity<ResponseBody<List<FeedBackDisplayDto>>> expected = new ResponseEntity<ResponseBody<List<FeedBackDisplayDto>>>(
			responseBody, HttpStatus.OK);
	ResponseEntity<?> actual = this.controller.displayAllReviews(id);
	Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	
}
@SuppressWarnings({ "serial" })
@Test
public void addFeedBackDetailsTests() throws AppException
{
	long productId=100;
	long userId=200;
	long orderId=300;
FeedBackDto feedBackDto=new FeedBackDto(4, "wertyui", 5);
Product product = new Product(234, "wert", "werty", 50, "sdfgh", 10, 20, new Date(), "ertyui", "ssrtyuio",
		false);
User user = new User();
user.setUserId(2);
user.setEmail("werty");
user.setDate(new Date());
user.setName("wertyu");
user.setWallet(0.0);
user.setPhoneNo("3456789");
Orders orders = new Orders(4, 20, 4567, new Date(), user);
List<Orders> orderList = new ArrayList<Orders>();
orderList.add(orders);
user.setOrders(orderList);
FeedBack feedback=new FeedBack(5, 4, new Date(), "cvbnm", user, product, orders);
Mockito.lenient().when(feedBackService.addFeedback(feedback,productId,userId,orderId)).thenReturn(feedback);
ResponseBody<FeedBackDto> responseBody = new ResponseBody<FeedBackDto>(null, true,
		model.map(feedBackService.addFeedback(feedback,productId,userId,orderId), new TypeToken<FeedBackDto>() {
		}.getType()));
ResponseEntity<ResponseBody<FeedBackDto>> expected = new ResponseEntity<ResponseBody<FeedBackDto>>(
		responseBody, HttpStatus.OK);
ResponseEntity<?> actual = this.controller.addFeedbackDetail(productId,userId,orderId,feedBackDto);
Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	
}
	
}
