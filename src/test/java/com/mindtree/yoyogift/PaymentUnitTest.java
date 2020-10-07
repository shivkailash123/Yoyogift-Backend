package com.mindtree.yoyogift;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.yoyogift.controller.YoYoGift;
import com.mindtree.yoyogift.dto.GiftCardDto;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.exception.service.InvalidValueException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;
import com.mindtree.yoyogift.service.CouponReedemService;
import com.mindtree.yoyogift.service.ProductOrderService;
import com.mindtree.yoyogift.service.ProductService;
import com.mindtree.yoyogift.service.UserService;

public class PaymentUnitTest {

	@InjectMocks
	private YoYoGift yoYoGift;

	@Mock
	private ProductService productService;

	@Mock
	private CouponReedemService couponReedemService;

	@Mock
	private ProductOrderService productOrderService;

	@Mock
	private UserService userService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void userDetailTest() {

		String email = "shiv@yoyogift.com";
		User userVar = new User();
		userVar.setCoupoun(null);
		userVar.setOrders(null);
		userVar.setDate(null);
		userVar.setEmail("shiv@yoyogift.com");
		userVar.setName("shiv");
		userVar.setPassword(null);
		userVar.setPhoneNo("9845476831");
		userVar.setUserId(123);
		userVar.setWallet(2000.0);
		try {
			Mockito.lenient().when(userService.userDetail(email)).thenReturn(userVar);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<?> response = null;
		try {
			response = yoYoGift.userDetail(email);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getStatusCode());
		System.out.println(HttpStatus.OK);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(email, userVar.getEmail());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(email, userVar.getEmail());
	}

	@Test
	public void addMoneyTest() {
		long id = 2;
		int amount = 100;
		String userName = "shiv";
		String varResponse = "Hi " + userName + " " + amount + " amount added to your wallet";

		try {
			Mockito.lenient().when(userService.addMoney(id, amount)).thenReturn(varResponse);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (InvalidValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		ResponseEntity<?> response = null;
		try {
			response = yoYoGift.addMoney(id, amount);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getStatusCode());
		System.out.println(HttpStatus.OK);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("Hi shiv " + amount + " amount added to your wallet", varResponse);

	}

	@Test
	public void couponReedemedTest() {
		GiftCardDto giftCardDto = new GiftCardDto();
		int id = 136;
		giftCardDto.setMessage("Hi Shiv");
		giftCardDto.setProductId(1);
		giftCardDto.setProductQuantity(1);
		giftCardDto.setUserId(126);
		giftCardDto.setReciverEmail("arun@yoyogift.com");
		giftCardDto.setVoucherAmount(200);
		String varResponse = "Hi your orderid is: " + id;
		try {
			Mockito.lenient().when(couponReedemService.couponReedem(giftCardDto)).thenReturn(varResponse);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<?> response = null;
		try {
			response = yoYoGift.couponReedem(giftCardDto);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getStatusCode());
		System.out.println(HttpStatus.OK);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("Hi your orderid is: " + id, varResponse);

	}

	@Test
	public void productOrderTest() {
		long productId = 3;
		long userId = 126;
		int quantity = 1;
		int id = 123;
		String varResponse = "Hi your orderid is: " + id;
		try {
			Mockito.lenient().when(productOrderService.productOrder(productId, userId, quantity))
					.thenReturn(varResponse);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<?> response = null;
		try {
			response = yoYoGift.productOrder(productId, userId, quantity);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.getStatusCode());
		System.out.println(HttpStatus.OK);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("Hi your orderid is: " + id, varResponse);

	}
	
}