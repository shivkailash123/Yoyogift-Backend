package com.mindtree.yoyogift;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.mindtree.yoyogift.entity.Coupouns;
import com.mindtree.yoyogift.entity.Messages;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.CoupounRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.serviceimpl.CouponServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GiftCardTests {

	@Autowired
	private CouponServiceImplementation couponService;

	@MockBean
	private CoupounRepository couponRepository;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void zeroGiftCardsOfUserTest() {
		List<Coupouns> giftCards = new ArrayList<Coupouns>();
		User user = new User();
		Mockito.lenient().when(this.userRepository.findById(1l)).thenReturn(Optional.of(user));
		Mockito.when(this.couponRepository.findByReedemedByOrderByIsRedeemedAscDateOfExpiryAsc(user))
				.thenReturn(giftCards);
		try {
			this.couponService.sendGiftCardsByUser(1l);
			Assertions.fail();
		} catch (ServiceException e) {
			Assertions.assertEquals("No Gift Cards Found..", e.getMessage());
		}
	}

	@Test
	public void userNotFoundTest() {
		Mockito.lenient().when(this.userRepository.findById(1l)).thenThrow(new RuntimeException("User not found"));
		try {
			this.couponService.sendGiftCardsByUser(1l);
			Assertions.fail();
		} catch (Exception e) {
			Assertions.assertEquals("User not found", e.getMessage());
		}
	}

	@Test
	public void sendGiftCardsByUserTest() {
		User user = new User();
		user.setEmail("1");
		user.setWallet(1d);
		Messages message = new Messages();
		message.setReceiver(user);
		message.setSender(user);
		List<Coupouns> coupouns = new ArrayList<>();
		Coupouns coupon = new Coupouns();
		coupon.setMessage(message);
		coupon.setReedemedBy(user);
		coupouns.add(coupon);
		Mockito.lenient().when(this.userRepository.findById(1l)).thenReturn(Optional.of(user));
		Mockito.lenient().when(this.couponRepository.findByReedemedByOrderByIsRedeemedAscDateOfExpiryAsc(user))
				.thenReturn(coupouns);
		try {
			this.couponService.sendGiftCardsByUser(1l);
		} catch (ServiceException e) {
			Assertions.fail();
		}
	}

	@Test
	public void redeemGiftCardUserExceptionTest() {
		Mockito.lenient().when(this.userRepository.findById(1l)).thenThrow(new RuntimeException("User not found"));
		try {
			this.couponService.redeemGiftCardByUser(1l, 1l);
			Assertions.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void redeemGiftCardNotFoundExceptionTest() {
		Mockito.lenient().when(this.couponRepository.findById(1l))
				.thenThrow(new RuntimeException("Gift Card not found."));
		try {
			this.couponService.redeemGiftCardByUser(1l, 1l);
			Assertions.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void redeemGiftCardRedeemedExceptionTest() {
		User user = new User();
		Coupouns coupon = new Coupouns();
		coupon.setRedeemed(true);
		Mockito.lenient().when(this.userRepository.findById(1l)).thenReturn(Optional.of(user));
		Mockito.lenient().when(this.couponRepository.findById(1l)).thenReturn(Optional.of(coupon));
		try {
			this.couponService.redeemGiftCardByUser(1l, 1l);
			Assertions.fail();
		} catch (ServiceException e) {
			Assertions.assertEquals("Gift card already redeemed", e.getMessage());
		}
	}

	@Test
	public void redeemGiftCardUserInvalidExceptionTest() {
		User user = new User();
		Coupouns coupon = new Coupouns();
		User wrongUser = new User();
		wrongUser.setName("1");
		coupon.setReedemedBy(wrongUser);
		Mockito.lenient().when(this.userRepository.findById(1l)).thenReturn(Optional.of(user));
		Mockito.lenient().when(this.couponRepository.findById(1l)).thenReturn(Optional.of(coupon));
		try {
			this.couponService.redeemGiftCardByUser(1l, 1l);
			Assertions.fail();
		} catch (ServiceException e) {
			Assertions.assertEquals("Gift card does not belong to User ID.", e.getMessage());
		}
	}

	@Test
	public void redeemGiftCardDateExceptionTest() {
		User user = new User();
		user.setWallet(0d);
		Coupouns coupon = new Coupouns();
		coupon.setAmount(0f);
		coupon.setDateOfExpiry(new Date(2323223232L));
		coupon.setReedemedBy(user);
		Messages message = new Messages();
		message.setReceiver(user);
		message.setSender(user);
		coupon.setMessage(message);
		coupon.setReedemedBy(user);
		List<Coupouns> coupouns = new ArrayList<>();
		coupouns.add(coupon);
		Mockito.lenient().when(this.couponRepository.findByReedemedByOrderByIsRedeemedAscDateOfExpiryAsc(user))
				.thenReturn(coupouns);
		Mockito.lenient().when(this.userRepository.findById(1l)).thenReturn(Optional.of(user));
		Mockito.lenient().when(this.couponRepository.findById(1l)).thenReturn(Optional.of(coupon));
		try {
			this.couponService.redeemGiftCardByUser(1l, 1l);
			Assertions.fail();
		} catch (ServiceException e) {
			Assertions.assertEquals("Gift Card has Expired.", e.getMessage());
		}
	}

	@Test
	public void redeemGiftCardTest() {
		User user = new User();
		user.setWallet(0d);
		Coupouns coupon = new Coupouns();
		coupon.setAmount(0f);
		coupon.setReedemedBy(user);
		Date date = new Date();
		date.setTime(date.getTime() + 2532489324l);
		coupon.setDateOfExpiry(date);
		Messages message = new Messages();
		message.setReceiver(user);
		message.setSender(user);
		coupon.setMessage(message);
		coupon.setReedemedBy(user);
		List<Coupouns> coupouns = new ArrayList<>();
		coupouns.add(coupon);
		Mockito.lenient().when(this.couponRepository.findByReedemedByOrderByIsRedeemedAscDateOfExpiryAsc(user))
				.thenReturn(coupouns);
		Mockito.lenient().when(this.userRepository.findById(1l)).thenReturn(Optional.of(user));
		Mockito.lenient().when(this.couponRepository.findById(1l)).thenReturn(Optional.of(coupon));
		try {
			this.couponService.redeemGiftCardByUser(1l, 1l);
		} catch (Exception e) {
			Assertions.fail();
		}
	}

}
