package com.mindtree.yoyogift.service.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.FeedBackDisplayDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.FeedBackAlreadyPresentException;
import com.mindtree.yoyogift.exception.service.custom.FeedBackIsNotFoundException;
import com.mindtree.yoyogift.exception.service.custom.OrderFeedbackIsAlreadyGivenException;
import com.mindtree.yoyogift.exception.service.custom.OrderIsNotFoundException;
import com.mindtree.yoyogift.exception.service.custom.ProductNotFoundException;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.FeedBackService;

@Service
public class FeedBackServiceImplementation implements FeedBackService {
	@Autowired
	FeedBackRepository feedbackRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderRepository orderRepository;

	@Override
	public FeedBack addFeedback(FeedBack feedBack, long productId, long userId, long orderId) throws ServiceException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User IS Not Found"));
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found "));
		Orders order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderIsNotFoundException("Order Is Not Found "));
		List<FeedBack> feed = feedbackRepository.findAll();
		for (FeedBack f : feed) {
			if (f.getOrder().getOrderId() == orderId) {
				throw new OrderFeedbackIsAlreadyGivenException("Order Feedback is Already Given:");
			} else if (order.getProduct().getProductId() != productId) {
				throw new ProductNotFoundException("Product is Not Found in this order: ");
			}
		}
		

//		if (feedbackRepository.findById(feedBack.getFeedBackId()).isPresent())
//			throw new FeedBackAlreadyPresentException(" FeedBack Is Already Present");
		feedBack.setProduct(product);
		feedBack.setUser(user);
		feedBack.setOrder(order);
		return feedbackRepository.save(feedBack);
	}

	@Override
	public List<FeedBackDisplayDto> displayAllReviewsFromDb(long productId) throws ServiceException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found Exception "));
		List<FeedBack> feed = feedbackRepository.findByProduct(product);
		if (feed == null) {
			throw new FeedBackIsNotFoundException("There is No feedback Available");
		}
		Collection<FeedBack> filteredFeedBack = feed.stream()
				.collect(Collectors.toMap(FeedBack::getUser, p -> p, (p, q) -> p)).values();
		feed = new ArrayList<FeedBack>(filteredFeedBack);
		Collections.sort(feed, new Comparator<FeedBack>() {
			@Override
			public int compare(FeedBack m1, FeedBack m2) {
				return -m1.getDate().compareTo(m2.getDate());
			}
		});
		List<FeedBackDisplayDto> feedDto = new ArrayList<FeedBackDisplayDto>();
		for (FeedBack f : feed) {
			FeedBackDisplayDto FeedbackDto = new FeedBackDisplayDto();
			FeedbackDto.setName(f.getUser().getName());
			FeedbackDto.setRatingPoint(f.getRatingPoint());
			FeedbackDto.setReview(f.getReview());
			FeedbackDto.setDate(f.getDate());
			feedDto.add(FeedbackDto);
		}
		return feedDto;
	}

}
