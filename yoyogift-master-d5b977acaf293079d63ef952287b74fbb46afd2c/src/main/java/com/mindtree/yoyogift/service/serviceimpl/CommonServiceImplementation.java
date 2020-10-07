package com.mindtree.yoyogift.service.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.yoyogift.dto.StatisticsDTO;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.CommonService;
import com.mindtree.yoyogift.utility.Utility;

@Service
public class CommonServiceImplementation implements CommonService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public StatisticsDTO generateStatistics() throws ServiceException {
		StatisticsDTO dto = new StatisticsDTO();
		dto = this.getProductStatistics(dto);
		dto = this.getUserStatistics(dto);
		dto = this.getOrdersStatistics(dto);
		return dto;
	}

	@Override
	public StatisticsDTO getProductStatistics(StatisticsDTO dto) {
		List<Product> products = this.productRepository.findAllByIsDeleted(false);
		Map<String, List<Product>> productMap = products.stream().collect(Collectors.groupingBy(Product::getCategory));
		productMap.entrySet().forEach(entry -> {
			dto.getProductCategories().add(entry.getKey());
			dto.getProductCountByCategory().add(entry.getValue().size());
		});
		Map<Long, List<Product>> salesMap = products.stream().collect(Collectors.groupingBy(Product::getProductId));
		salesMap.entrySet().forEach(entry -> {
			Product product = this.productRepository.findById(entry.getKey()).get();
			List<Orders> ordersByProduct = this.orderRepository.findAllByProduct(product);
			String name = product.getProductName();
			dto.getTopSellingProducts().add(name);
			dto.getTopSellingProductsCount().add(ordersByProduct.size());
		});
		return dto;
	}

	@Override
	public StatisticsDTO getUserStatistics(StatisticsDTO dto) {
		Date date = new Date();
		Date changeDate = new Date();
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getUserJoiningStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getUserJoiningStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getUserJoiningStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getUserJoiningStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getUserJoiningStatistics(dto, changeDate, date);
		return dto;
	}

	@Override
	public StatisticsDTO getUserJoiningStatistics(StatisticsDTO dto, Date changeDate, Date date) {
		List<User> usersBetweenNowAnd2DaysBefore = this.userRepository.findAllByDateBetween(changeDate, date);
		dto.getUsersJoiningDate().add(Utility.dateString(changeDate) + "-" + Utility.dateString(date));
		dto.getUsersJoiningDateCount().add(usersBetweenNowAnd2DaysBefore.size());
		return dto;
	}

	@Override
	public StatisticsDTO getOrdersStatistics(StatisticsDTO dto) {
		Date date = new Date();
		Date changeDate = new Date();
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getOrderPurchaseStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getOrderPurchaseStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getOrderPurchaseStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getOrderPurchaseStatistics(dto, changeDate, date);
		date.setTime(changeDate.getTime());
		changeDate.setTime(date.getTime() - 172800000l);
		dto = this.getOrderPurchaseStatistics(dto, changeDate, date);
		return dto;
	}

	@Override
	public StatisticsDTO getOrderPurchaseStatistics(StatisticsDTO dto, Date changeDate, Date date) {
		List<Orders> ordersBetweenNowAnd2DaysBefore = this.orderRepository.findAllByOrderDateBetween(changeDate, date);
		dto.getPurchaseTimes().add(Utility.dateString(changeDate) + "-" + Utility.dateString(date));
		dto.getPurchaseTimesCount().add(ordersBetweenNowAnd2DaysBefore.size());
		return dto;
	}

}
