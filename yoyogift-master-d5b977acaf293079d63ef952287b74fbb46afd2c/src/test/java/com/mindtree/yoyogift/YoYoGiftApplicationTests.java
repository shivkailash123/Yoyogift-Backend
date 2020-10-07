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

import com.mindtree.yoyogift.dto.StatisticsDTO;
import com.mindtree.yoyogift.entity.Coupouns;
import com.mindtree.yoyogift.entity.Messages;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.serviceimpl.CommonServiceImplementation;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class YoYoGiftApplicationTests {
	
	@Autowired
	private CommonServiceImplementation commonService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private ProductRepository productRepository;

//	@Test
	void contextLoads() {
	}
	
	@Test
	public void statisticsPositiveTest() {
		Product product = new Product();
		product.setCategory("Bat");
		product.setProductName("MRF");
		product.setProductId(1);
		product.setDeleted(false);
		List<Product> products = new ArrayList<>();
		products.add(product);
		Date date = new Date();
		User user = new User();
		user.setEmail("1");
		user.setDate(new Date());
		Orders order = new Orders(1, 1, 1,date, user);
		order.setProduct(product);
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(order);
		user.setOrders(orders);
		Coupouns coupon = new Coupouns(1, "1", 1, date, false, null, user, new Messages());
		List<Coupouns> coupons = new ArrayList<>();
		coupons.add(coupon);
		user.setCoupoun(coupons);
		order.setUser(user);
		Date date2 = new Date();
		date2.setTime(date2.getTime() - 172800000l);
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.lenient().when(productRepository.findAllByIsDeleted(false)).thenReturn(products);
		Mockito.lenient().when(productRepository.findById(1l)).thenReturn(Optional.of(product));
		Mockito.lenient().when(orderRepository.findAllByProduct(product)).thenReturn(orders);
		Mockito.lenient().when(orderRepository.findAllByOrderDateBetween(date, date2)).thenReturn(orders);
		Mockito.lenient().when(userRepository.findAllByDateBetween(date, date2)).thenReturn(users);
		try {
			StatisticsDTO dto = this.commonService.generateStatistics();
			Assertions.assertEquals(1, dto.getProductCategories().size());
		} catch (ServiceException e) {
			Assertions.fail();
		}
	}

}
