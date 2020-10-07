package com.mindtree.yoyogift.service;

import com.mindtree.yoyogift.exception.service.ServiceException;

public interface EmailService {

	public String sendNotification(String email, long id) throws ServiceException;

}
