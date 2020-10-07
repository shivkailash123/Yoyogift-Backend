package com.mindtree.yoyogift.service.serviceimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.OrderDto;
import com.mindtree.yoyogift.dto.AdminOrderDTO;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.InvalidValueException;
import com.mindtree.yoyogift.exception.service.ListEmptyException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.OrderIsNotFoundException;
import com.mindtree.yoyogift.exception.service.custom.ProductNotFoundException;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.ProductOrderService;

@Service
public class OrderServiceImplementation implements ProductOrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FeedBackRepository feedbackRepository;

	@Override
	public String productOrder(long productId, long userId, int quantity) throws ServiceException {
		// return type
		// TODO Auto-generated method stub

		/*
		 * Fetching data from backend of user and product;
		 */
		Orders orderVar = new Orders();

		User userVar = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));// throw
																														// service
																														// exception
		Product productVar = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

		// function take date from system.

		Date date = new Date();

		/*
		 * Checking the wallet Balance
		 */

		// deducting price from wallet for that particular product
		float discount = (float) ((float) (productVar.getDiscount() * 1.0) / 100.0);
		if (userVar.getWallet() - (productVar.getPrice() - (productVar.getPrice() * discount) * quantity) < 0) {
			throw new InvalidValueException("Balance insufficent");
		} else {
			discount = (float) ((float) (productVar.getDiscount() * 1.0) / 100.0);

			userVar.setWallet(
					(double) Math.round(userVar.getWallet() - (productVar.getPrice() - (productVar.getPrice() * discount) * quantity)));

		}

		/*
		 * Checking the stock of the product
		 */
		if (productVar.getStock() - quantity < 0) {
			throw new InvalidValueException("Out of Stock");
		} else {
			productVar.setStock(productVar.getStock() - quantity);
		}

		/*
		 * Saving data to backend
		 */
		orderVar.setAmount(Math.round((productVar.getPrice() - (productVar.getPrice() * discount) * quantity)));
		orderVar.setProductQuantity(quantity);
		orderVar.setOrderDate(date);
		orderVar.setProduct(productVar);
		orderVar.setUser(userVar);

		Orders orders = orderRepository.save(orderVar);

		userRepository.save(userVar);
		productRepository.save(productVar);
		try {

			return "Order Placed Succesfully with id: " + orders.getOrderId();
		} catch (Exception e) {
			throw new OrderIsNotFoundException("Order is is not present");
		}
	}

	@Override
	public List<OrderDto> getOrders(String userEmail) {
		// TODO Auto-generated method stub
		List<Orders> order = orderRepository.findAll();
		List<OrderDto> order1 = new LinkedList<>();
		List<FeedBack> feedback = feedbackRepository.findAll();
		List<User> user = userRepository.findAll();
		long userId = 0;
		int flag = 1;
		for (User v : user) {
			if (v.getEmail().equalsIgnoreCase(userEmail)) {
				userId = v.getUserId();
				break;
			}
		}
		System.out.println(userId);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		for (Orders u : order) {
			if (u.getUser().getEmail().equalsIgnoreCase(userEmail)) {

				OrderDto o = new OrderDto();
				o.setOrderId(u.getOrderId());
				o.setProductQuantity(u.getProductQuantity());
				o.setAmount(u.getAmount());
				String str = dateFormat.format(u.getOrderDate());
				o.setUserDate(str);
				o.setProductName(u.getProduct().getProductName());
				o.setVendor(u.getProduct().getVendor());
				o.setImageUrl(u.getProduct().getImageUrl());
				o.setProductId(u.getProduct().getProductId());
				for (FeedBack y : feedback) {
					if ((y.getUser().getUserId() == userId) && (y.getOrder().getOrderId() == u.getOrderId())
							|| (u.getProduct().getCategory().equalsIgnoreCase("Gift Card")
									|| u.getProduct().getCategory().equalsIgnoreCase("GiftCard"))) {
						o.setFeedback(true);
						flag = 0;

					}
				}
				if (flag == 1) {
					o.setFeedback(false);
				}
				order1.add(o);
			}

		}
		return order1;

	}

	public List<AdminOrderDTO> getAllOrders() throws ServiceException {
		List<Orders> orders = this.orderRepository.findAll();
		if (orders.isEmpty()) {
			throw new ListEmptyException("No Orders in DB");
		} else {
			List<AdminOrderDTO> dtos = new ArrayList<>();
			orders.forEach(order -> {
				AdminOrderDTO dto = new AdminOrderDTO();
				dto.setAmount(order.getAmount());
				dto.setOrderDate(order.getOrderDate());
				dto.setOrderId(order.getOrderId());
				dto.setOrderQuantity(order.getProductQuantity());
				dto.setProductId(order.getProduct().getProductId());
				dto.setUserEmail(order.getUser().getEmail());
				dtos.add(dto);
			});
			return dtos;
		}
	}

}
