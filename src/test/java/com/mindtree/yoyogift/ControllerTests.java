package com.mindtree.yoyogift;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mindtree.yoyogift.dto.AdminOrderDTO;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.service.serviceimpl.OrderServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderServiceTests {

	@Autowired
	private OrderServiceImplementation orderService;

	@MockBean
	private OrderRepository orderRepository;

	@Test
	public void getAllOrdersNegativeTest() {
		List<Orders> ordersMockData = new ArrayList<Orders>();
		Mockito.lenient().when(this.orderRepository.findAll()).thenReturn(ordersMockData);
		try {
			List<AdminOrderDTO> adminOrders = this.orderService.getAllOrders();
			Assertions.fail();
			System.out.println(adminOrders);
		} catch (ServiceException e) {
			Assertions.assertEquals("No Orders in DB", e.getMessage());
		}
	}

	@Test
	public void getAllOrdersPositiveTest() {
		List<Orders> ordersMockData = new ArrayList<Orders>();
		Orders order = new Orders(1, 1, 50, new Date(), new User());
		order.setProduct(new Product());
		ordersMockData.add(order);
		Mockito.lenient().when(this.orderRepository.findAll()).thenReturn(ordersMockData);
		try {
			List<AdminOrderDTO> adminOrders = this.orderService.getAllOrders();
			Assertions.assertTrue(adminOrders.size() > 0);
		} catch (ServiceException e) {
			Assertions.fail();
		}
	}

}
