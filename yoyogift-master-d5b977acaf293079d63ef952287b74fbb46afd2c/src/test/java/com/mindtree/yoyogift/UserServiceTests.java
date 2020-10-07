package com.mindtree.yoyogift;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.serviceimpl.UserServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTests {

	@Autowired
	private UserServiceImplementation userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void getAllUsersNegativeTest() {
		List<User> usersMockData = new ArrayList<User>();
		Mockito.lenient().when(this.userRepository.findAll()).thenReturn(usersMockData);
		try {
			List<User> users = this.userService.getAllUsers();
			Assertions.fail();
			System.out.println(users);
		} catch (ServiceException e) {
			Assertions.assertEquals("No Users found...", e.getMessage());
		}
	}

	@Test
	public void getAllUsersPositiveTest() {
		List<User> usersMockData = new ArrayList<User>();
		usersMockData.add(new User());
		Mockito.lenient().when(this.userRepository.findAll()).thenReturn(usersMockData);
		try {
			List<User> users = this.userService.getAllUsers();
			Assertions.assertEquals(usersMockData, users);
		} catch (ServiceException e) {
			Assertions.fail();
		}
	}

}
