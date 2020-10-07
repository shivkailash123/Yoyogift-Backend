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
import org.springframework.core.OrderComparator.OrderSourceProvider;

import com.mindtree.yoyogift.dto.FeedBackDisplayDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.serviceimpl.FeedBackServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FeedBackServiceTests {
	@Autowired
	FeedBackServiceImplementation feedbackService;
	@MockBean
	private FeedBackRepository feedbackRepository;
	@MockBean
	private ProductRepository productRepository;
	@MockBean
	private OrderRepository orderRepository;
	@MockBean
	private UserRepository userRepository;

	@Test
   public void getAllFeedBackDetailsTests()
   {
	   long id=234;
	   Product product=new Product(23445, "wert", "werty", 50, "sdfgh", 10, 20, new Date(), "ertyui", "ssrtyuio", false);
	   User user = new User();
		user.setUserId(2);
		user.setEmail("werty");
		user.setDate(new Date());
		user.setName("wertyu");
		user.setWallet(0.0);
		user.setPhoneNo("3456789");
		Orders orders = new Orders(4, 20, 4567, new Date(), user); 
		List<Orders> orderList=new ArrayList<Orders>();
		orderList.add(orders);
		user.setOrders(orderList);
	   FeedBack feedback=new FeedBack(234, 4, new Date(), "dfghj", user, product, orders);
	   Optional<Product> productOptional = Optional.of(product);
	   Mockito.lenient().when(productRepository.findById(id)).thenReturn(productOptional);
	   List<FeedBack> feed=new ArrayList<FeedBack>();
		feed.add(feedback);
		Mockito.lenient().when(feedbackRepository.findByProduct(product)).thenReturn(feed);
		try 
		{
	     List<FeedBackDisplayDto>feedbackdto =feedbackService.displayAllReviewsFromDb(id);
			System.out.println(feedbackdto);
			Assertions.assertEquals(1, feedbackdto.size());
			System.out.println("Success for positive case");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
	     List<FeedBackDisplayDto>feedbackdto =feedbackService.displayAllReviewsFromDb(5);
			System.out.println(feedbackdto);
			System.out.println("Success:");
		} catch (ServiceException e) {
			Assertions.assertEquals("Product Not Found Exception ", e.getMessage());
			System.out.println("Failure for product");
		}
   }

	@Test
	void addFeebackDetails() {

		long productid = 234;
		long userid = 2;
		long orderid = 4;
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
		FeedBack feedbacks = new FeedBack(2345, 4, new Date(), "ertyu", user, product, orders);
		Optional<User> userOptional = Optional.of(user);
		Optional<Product> productOptional = Optional.of(product);
		Optional<Orders> orderOptional = Optional.of(orders);
		Mockito.lenient().when(userRepository.findById(userid)).thenReturn(userOptional);
		Mockito.lenient().when(productRepository.findById(productid)).thenReturn(productOptional);
		Mockito.lenient().when(orderRepository.findById(orderid)).thenReturn(orderOptional);
		Mockito.lenient().when(feedbackRepository.save(feedbacks)).thenReturn(feedbacks);
		Mockito.lenient().when(feedbackRepository.findAll()).thenReturn(new ArrayList<FeedBack>());
		// FeedBack feedBack=new FeedBack(56789, 4, new Date(), "rty", userOptional,
		// productOptional,orderOptional);
		try {

			FeedBack feedback = feedbackService.addFeedback(feedbacks, productid, userid, orderid);
			System.out.println(feedback);
			System.out.println("Success: for positive case");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			FeedBack feedback = feedbackService.addFeedback(feedbacks, 10, userid, orderid);
			System.out.println(feedback);
		} catch (ServiceException e) {
			Assertions.assertEquals("Product Not Found ", e.getMessage());
			System.out.println("Failure for negative product");
		}

		try {

			FeedBack feedback = feedbackService.addFeedback(feedbacks, productid, 11, orderid);
			System.out.println(feedback);
		} catch (ServiceException e) {
			Assertions.assertEquals("User IS Not Found", e.getMessage());
			System.out.println("Failure for negative user");
		}

		try {

			FeedBack feedback = feedbackService.addFeedback(feedbacks, productid, userid, 15);
			System.out.println(feedback);
		} catch (ServiceException e) {
			Assertions.assertEquals("Order Is Not Found ", e.getMessage());
			System.out.println("Failure for negative order");
		}

	}

}
