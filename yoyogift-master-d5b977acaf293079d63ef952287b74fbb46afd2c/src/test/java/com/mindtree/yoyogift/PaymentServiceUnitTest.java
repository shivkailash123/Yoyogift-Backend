package com.mindtree.yoyogift;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.mindtree.yoyogift.dto.GiftCardDto;
import com.mindtree.yoyogift.entity.Coupouns;
import com.mindtree.yoyogift.entity.Messages;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.exception.service.InvalidEmailException;
import com.mindtree.yoyogift.exception.service.InvalidValueException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;
import com.mindtree.yoyogift.repository.CoupounRepository;
import com.mindtree.yoyogift.repository.MessageRepository;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.serviceimpl.CouponServiceImplementation;
import com.mindtree.yoyogift.service.serviceimpl.EmailServiveImp;
import com.mindtree.yoyogift.service.serviceimpl.OrderServiceImplementation;
import com.mindtree.yoyogift.service.serviceimpl.UserServiceImplementation;
import com.mindtree.yoyogift.utility.ReferalCodeUtil;
import com.mindtree.yoyogift.utility.Utility;

@SpringBootTest
public class PaymentServiceUnitTest {

	@InjectMocks
	CouponServiceImplementation couponServiceImplementation;
	@InjectMocks
	OrderServiceImplementation orderServiveImplementation;
	@InjectMocks
	UserServiceImplementation userServiceImplementation;
	@InjectMocks
	EmailServiveImp emailServiceImpl;

	@Mock
	private OrderRepository orderRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private CoupounRepository coupounRepository;
	@Mock
	private MessageRepository messageRepository;
	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void couponRedeemTest() {
		System.err.println("entry");
		GiftCardDto giftCardDto = new GiftCardDto(123, 200, "arun@yoyogift.com", "Hi bro", 2, 1);

		String varEmail = giftCardDto.getReciverEmail();

		int orderId = 123;

		String varMessage = "Hi your oder is placed with orderId: " + orderId;
		// Mockito.lenient().when(couponServiceImplementation.couponReedem(giftCardDto)).thenReturn(varMessage);

		User varUser = new User(123, "shiv", "shiv@yoyogift.com", "lkAHDKas23", "9845476821", null, 200.0, null, null);
		User reciverUser = new User();
		reciverUser.setName("arun");
		reciverUser.setEmail("arun@yoyogift.com");
		reciverUser.setOrders(null);
		reciverUser.setPassword("agtryukusds434");
		reciverUser.setDate(new Date());
		reciverUser.setPhoneNo("9838504908");
		reciverUser.setUserId(7);
		reciverUser.setWallet(230.0);
		reciverUser.setCoupoun(null);

		Product productVar = new Product(2, "Rolex watch", "watch", 200000, "good watch", 100, 5, null, "Amazon", null,
				true);
		Optional<User> user1 = Optional.of(varUser);
		Mockito.lenient().when(userRepository.findByEmail(varEmail)).thenReturn(reciverUser);
		Mockito.lenient().when(userRepository.findById(giftCardDto.getUserId())).thenReturn(user1);
		// when(productRepository.findById(giftCardDto.getProductId())).thenReturn(productVar).orElseThrow(()
		// -> new ProductNotFoundException("Product Not Found"));
		Optional<Product> productVar1 = Optional.of(productVar);
		Mockito.lenient().when(productRepository.findById(giftCardDto.getProductId())).thenReturn(productVar1);

		Mockito.lenient().when(userRepository.save(varUser)).thenReturn(varUser);
		Mockito.lenient().when(userRepository.save(reciverUser)).thenReturn(reciverUser);
		Messages messageVar = new Messages();
		messageVar.setContent(giftCardDto.getMessage());
		messageVar.setDate(new Date());
		messageVar.setReceiver(reciverUser);
		messageVar.setSender(varUser);

		Mockito.lenient().when(messageRepository.save(messageVar)).thenReturn(messageVar);
		String varString = null;

		Coupouns coupouns = new Coupouns();
		coupouns.setAmount(giftCardDto.getVoucherAmount());
		coupouns.setDateOfExpiry(new Date());

		coupouns.setCoupounCode("jdfaksjdf121");

		coupouns.setRedeemed(false);
		coupouns.setReedemedBy(null);
		coupouns.setRedeemedOn(new Date());
		Mockito.lenient().when(coupounRepository.save(coupouns)).thenReturn(coupouns);

		Orders varOrders = new Orders();
		varOrders.setAmount(giftCardDto.getVoucherAmount());
		varOrders.setProductQuantity(1);
		varOrders.setOrderDate(new Date());
		varOrders.setUser(varUser);
		varOrders.setProduct(productVar);
		varOrders.setOrderId(123l);
		Mockito.lenient().when(orderRepository.save(varOrders)).thenReturn(varOrders);

		try {
			Mockito.lenient().when(couponServiceImplementation.couponReedem(giftCardDto)).thenReturn(varMessage);
			// Mockito.lenient().when(userService.userDetail(email)).thenReturn(userVar);
			// System.out.println(varString);
			Assertions.assertEquals("Hi your order is placed with orderId: " + 123, varMessage);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}

//		System.out.println(varString);
//		Assertions.assertEquals("Hi your oder is placed with orderId: " + orderId, varMessage);

	}

	@Test
	public void productOrderTest() {
		long productId = 123;
		long userId = 23;
		int quantity = 1;
		int orderId = 21;
		String varMessage = "Hi your oder is placed with orderId: " + orderId;
		Orders orderVar = new Orders();
		User user = new User();
		user.setCoupoun(null);
		user.setDate(new Date());
		user.setEmail("shiv@yoyogift.com");
		user.setName("shiv");
		user.setOrders(null);
		user.setPassword("eofiosidf23423434");
		user.setPhoneNo("9845476833");
		user.setUserId(23);
		user.setWallet(2000000.0);

		Product productVar = new Product();

		productVar.setCategory("nickless");
		productVar.setDate(new Date());
		productVar.setDeleted(false);
		productVar.setDescription("Diamond neckless");
		productVar.setDiscount(5);
		productVar.setImageUrl(null);
		productVar.setPrice(20000);
		productVar.setProductId(123);
		productVar.setProductName("Diamond Neckless");
		productVar.setStock(45);
		productVar.setVendor("amazon");
		Optional<User> user1 = Optional.of(user);
		Optional<Product> productVar1 = Optional.of(productVar);
		Mockito.lenient().when(userRepository.findById(userId)).thenReturn(user1);
		Mockito.lenient().when(productRepository.findById(productId)).thenReturn(productVar1);
		orderVar.setAmount(productVar.getPrice());
		orderVar.setProductQuantity(quantity);
		orderVar.setOrderDate(new Date());
		orderVar.setProduct(productVar);
		orderVar.setUser(user);

		Mockito.lenient().when(userRepository.save(user)).thenReturn(user);
		Mockito.lenient().when(productRepository.save(productVar)).thenReturn(productVar);
		Mockito.lenient().when(orderRepository.save(orderVar)).thenReturn(orderVar);
		// System.err.println(orderVar.getOrderId());
		try {
			orderServiveImplementation.productOrder(productId, userId, quantity);
			Assertions.assertEquals("Hi your oder is placed with orderId: ", varMessage);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("Order is not Present");
		}

	}

	@Test
	public void userDetailTest() {
		String varEmail = "shiv@yoyogift.com";
		int amount = 100;
		User user = new User();
		user.setCoupoun(null);
		user.setDate(new Date());
		user.setEmail("shiv@yoyogift.com");
		user.setName("shiv");
		user.setOrders(null);
		user.setPassword("eofiosidf23423434");
		user.setPhoneNo("9845476833");
		user.setUserId(23);
		user.setWallet(2000000.0);

		Mockito.lenient().when(userRepository.findByEmail(varEmail)).thenReturn(user);
		try {
			userServiceImplementation.addMoney(user.getUserId(), amount);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (InvalidValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Assertions.assertEquals(varEmail, user.getEmail());

//		User userVar = userRepository.findByEmail(email);
//		if (userVar == null) {
//			throw new UserNotFoundException("User is not present");
//		} else {
//			return userVar;
//		}
//		
//	}

	}

	@Test
	public void userDetailNegativeTest() {
		String varEmail = "shiv@yoyogift.com";
		int amount = 100;
		User user = new User();
		user.setCoupoun(null);
		user.setDate(new Date());
		user.setEmail("arun@yoyogift.com");
		user.setName("shiv");
		user.setOrders(null);
		user.setPassword("eofiosidf23423434");
		user.setPhoneNo("9845476833");
		user.setUserId(23);
		user.setWallet(2000000.0);

		try {
			Mockito.lenient().when(userServiceImplementation.userDetail(varEmail)).thenReturn(user);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		Assertions.assertNotEquals(varEmail, user.getEmail());

	}

	@Test
	public void addAmountTest() {

		long id = 2;
		int amount = 100;
		User user = new User();
		user.setCoupoun(null);
		user.setDate(new Date());
		user.setEmail("shiv@yoyogift.com");
		user.setName("shiv");
		user.setOrders(null);
		user.setPassword("eofiosidf23423434");
		user.setPhoneNo("9845476833");
		user.setUserId(2);
		user.setWallet(2000000.0);
		String varString = "Hi " + user.getName() + " " + (amount / 5) + " Yo Yo Point added to your account";
		// User userVar = userRepository.findById(id).orElseThrow(() -> new
		// UserNotFoundException("User Not Present"));

		Optional<User> userVar = Optional.of(user);
		Mockito.lenient().when(userRepository.findById(id)).thenReturn(userVar);
		try {
			userServiceImplementation.addMoney(id, amount);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (InvalidValueException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		Assertions.assertEquals("Hi " + user.getName() + " " + (amount / 5) + " Yo Yo Point added to your account",
				varString);

	}

	@Test
	public void addMoneyNegativeTestCase() {
		long id = 2;
		int amount = -1;
		User user = new User();
		user.setCoupoun(null);
		user.setDate(new Date());
		user.setEmail("shiv@yoyogift.com");
		user.setName("shiv");
		user.setOrders(null);
		user.setPassword("eofiosidf23423434");
		user.setPhoneNo("9845476833");
		user.setUserId(2);
		user.setWallet(2000000.0);
		String varString = "Hi " + user.getName() + " " + (amount / 5) + " Yo Yo Point added to your account";
		// User userVar = userRepository.findById(id).orElseThrow(() -> new
		// UserNotFoundException("User Not Present"));

		Optional<User> userVar = Optional.of(user);
		Mockito.lenient().when(userRepository.findById(id)).thenReturn(userVar);
		try {
			userServiceImplementation.addMoney(id, amount);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (InvalidValueException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Assertions.assertEquals("Hi " + user.getName() + " " + (amount / 5) + " Yo Yo Point added to your account",
				varString);

	}

	@Test
	public void emailServiceTest() throws ServiceException {

		String varEmail = "shivkailash12345@gmail.com";
		long id = 123;
		String varMessage = "message send";


		List<Coupouns> coupouns = new ArrayList<Coupouns>();
		List<Orders> orders = new ArrayList<Orders>();
		
		User user = new User(123, "shiv", "shivkailash12345@gmail.com", "12345", "9845476831", new Date(), 20.0, orders,
				coupouns);

		/*
		 * Implementation for hash code to send in a message.
		 */
		Optional<User> userVar = Optional.of(user);
		Mockito.lenient().when(userRepository.findById(id)).thenReturn(userVar);

	
		try {
			varMessage = emailServiceImpl.sendNotification(varEmail, id);
			Assertions.assertEquals("message send", varMessage);
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			throw new InvalidEmailException(ex.getMessage());
		}

	}

}
