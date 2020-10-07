package com.mindtree.yoyogift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.InvalidValueException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;

@Service
public interface UserService {
	public User addUser(User user,String referralCode) throws ServiceException;

	User verifyUser(String emailId, String password, boolean socialFlag) throws ServiceException;

	User getDetail(long userId) throws ServiceException;

	public User userDetail(String email) throws UserNotFoundException;
	

	public String updateUserData(User user, long id) throws ServiceException;

	public List<User> getAllUsers() throws ServiceException;

	public String addMoney(long id, int amount) throws UserNotFoundException, InvalidValueException;

}
