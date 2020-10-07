package com.mindtree.yoyogift.service;
import java.util.List;

import com.mindtree.yoyogift.dto.FeedBackDisplayDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.exception.service.ServiceException;

public interface FeedBackService 
{

	public FeedBack addFeedback(FeedBack feedBack, long productId, long userId,long orderId) throws ServiceException;

	public List<FeedBackDisplayDto> displayAllReviewsFromDb(long productId) throws ServiceException;

	

}
