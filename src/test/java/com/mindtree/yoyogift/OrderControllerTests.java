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
import com.mindtree.yoyogift.dto.AdminOrderDTO;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.RedeemableGiftCardDTO;
import com.mindtree.yoyogift.dto.ResponseBody;
import com.mindtree.yoyogift.dto.StatisticsDTO;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.service.CommonService;
import com.mindtree.yoyogift.service.serviceimpl.CouponServiceImplementation;
import com.mindtree.yoyogift.service.serviceimpl.OrderServiceImplementation;
import com.mindtree.yoyogift.service.serviceimpl.ProductServiceImplementation;
import com.mindtree.yoyogift.service.serviceimpl.UserServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderControllerTests {

	@Autowired
	private YoYoGift controller;

	@Autowired
	private ModelMapper modelMapper;

	@MockBean
	private OrderServiceImplementation orderService;
	
	@MockBean
	private CommonService commonService;

	@MockBean
	private ProductServiceImplementation productService;

	@MockBean
	private UserServiceImplementation userService;

	@MockBean
	private CouponServiceImplementation couponService;

	@SuppressWarnings("serial")
	@Test
	public void displayTest() throws AppException {
		List<ProductDto> products = new ArrayList<ProductDto>();
		Mockito.lenient().when(productService.findAll()).thenReturn(products);
		ResponseBody<List<ProductDto>> responseBody = new ResponseBody<List<ProductDto>>(null, true,
				modelMapper.map(productService.findAll(), new TypeToken<List<ProductDto>>() {
				}.getType()));
		ResponseEntity<ResponseBody<List<ProductDto>>> expected = new ResponseEntity<ResponseBody<List<ProductDto>>>(
				responseBody, HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.display();
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}

	@Test
	public void getAllUsersTest() throws AppException {
		List<User> usersMockData = new ArrayList<>();
		Mockito.lenient().when(userService.getAllUsers()).thenReturn(usersMockData);
		ResponseBody<List<User>> responseBody = new ResponseBody<List<User>>(null, true,
				this.userService.getAllUsers());
		ResponseEntity<ResponseBody<List<User>>> expected = new ResponseEntity<ResponseBody<List<User>>>(responseBody,
				HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.getAllUsers();
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}

	@Test
	public void getAllOrdersTest() throws AppException {
		List<AdminOrderDTO> ordersMockData = new ArrayList<>();
		Mockito.lenient().when(orderService.getAllOrders()).thenReturn(ordersMockData);
		ResponseBody<List<AdminOrderDTO>> responseBody = new ResponseBody<List<AdminOrderDTO>>(null, true,
				this.orderService.getAllOrders());
		ResponseEntity<ResponseBody<List<AdminOrderDTO>>> expected = new ResponseEntity<ResponseBody<List<AdminOrderDTO>>>(
				responseBody, HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.getAllOrders();
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}

	@Test
	public void redeemGiftCardsByUserTest() throws AppException {
		List<RedeemableGiftCardDTO> giftCardsMockData = new ArrayList<>();
		Mockito.lenient().when(this.couponService.redeemGiftCardByUser(1l, 1l)).thenReturn(giftCardsMockData);
		ResponseBody<List<RedeemableGiftCardDTO>> responseBody = new ResponseBody<List<RedeemableGiftCardDTO>>(null,
				true, this.couponService.redeemGiftCardByUser(1l, 1l));
		ResponseEntity<ResponseBody<List<RedeemableGiftCardDTO>>> expected = new ResponseEntity<ResponseBody<List<RedeemableGiftCardDTO>>>(
				responseBody, HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.redeemGiftCardsByUser(1l, 1l);
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}

	@Test
	public void getGiftCardsByUserTest() throws AppException {
		List<RedeemableGiftCardDTO> giftCardsMockData = new ArrayList<>();
		Mockito.lenient().when(this.couponService.sendGiftCardsByUser(1l)).thenReturn(giftCardsMockData);
		ResponseBody<List<RedeemableGiftCardDTO>> responseBody = new ResponseBody<List<RedeemableGiftCardDTO>>(null,
				true, this.couponService.sendGiftCardsByUser(1l));
		ResponseEntity<ResponseBody<List<RedeemableGiftCardDTO>>> expected = new ResponseEntity<ResponseBody<List<RedeemableGiftCardDTO>>>(
				responseBody, HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.getGiftCardsByUser(1l);
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}

	@SuppressWarnings("serial")
	@Test
	public void deleteProductTest() throws AppException {
		// ResponseBody<ProductDto>
		Product product = new Product(1, "1", "1", 1, "1", 1, 1, new Date(), "1", "1", false);
		Mockito.lenient().when(this.productService.deleteProduct(1l)).thenReturn(product);
		ResponseBody<ProductDto> responseBody = new ResponseBody<ProductDto>(null, true,
				modelMapper.map(productService.deleteProduct(1l), new TypeToken<ProductDto>() {
				}.getType()));
		ResponseEntity<ResponseBody<ProductDto>> expected = new ResponseEntity<ResponseBody<ProductDto>>(responseBody,
				HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.deleteProductById(1l);
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}
	
	@Test
	public void getStatisticsTest() throws AppException {
		StatisticsDTO dto = new StatisticsDTO();
		Mockito.lenient().when(this.commonService.generateStatistics()).thenReturn(dto);
		ResponseBody<StatisticsDTO> responseBody = new ResponseBody<StatisticsDTO>(null, true, this.commonService.generateStatistics());
		ResponseEntity<ResponseBody<StatisticsDTO>> expected = new ResponseEntity<ResponseBody<StatisticsDTO>>(responseBody,
				HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.getStatisticsData();
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}

}
