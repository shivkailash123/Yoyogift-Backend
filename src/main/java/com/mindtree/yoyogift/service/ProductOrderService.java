package com.mindtree.yoyogift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.OrderDto;
import com.mindtree.yoyogift.dto.AdminOrderDTO;
import com.mindtree.yoyogift.exception.service.ServiceException;


@Service
public interface ProductOrderService {

	String productOrder(long productId, long userId, int quantity) throws ServiceException;

	public List<OrderDto> getOrders(String userEmail);

	List<AdminOrderDTO> getAllOrders() throws ServiceException;
	
}
