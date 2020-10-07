package com.mindtree.yoyogift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.exception.service.NoSuchALgo;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.serviceimpl.UserServiceImplementation;
import com.mindtree.yoyogift.utility.Utility;

public class VerifyLoginUserCredentialsServiceTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImplementation userService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void UserCredentialsPositiveScenario() throws AppException {
		System.out.println("method1");
		User user1 = new User();
		user1.setEmail("ravi@yoyogift.com");
		user1.setName("ravi");
		user1.setWallet(1000.00);
		user1.setPhoneNo("9876543210");

		try {
			user1.setPassword(Utility.Encryption("As23456@"));
		} catch (NoSuchAlgorithmException e1) {
			assertEquals("no such algorithm exist", e1.getMessage());
		} catch (InvalidKeySpecException e) {
			assertEquals("Secret Key is invalid", e.getMessage());
		}
		String password = "As23456@";
		List<User> user = new ArrayList<User>();
		user.add(user1);
		when(userRepository.findByEmail("ravi@yoyogift.com")).thenReturn(user1);
		try {
			Assertions.assertTrue(userService.verifyUser("ravi", password, false).getEmail().equals(user1.getEmail()));
			System.out.println("Email success");
		} catch (ServiceException e) {
			System.out.println("failure");
			assertEquals("Invalid email address", e.getMessage());
		}
		try {
			Assertions.assertTrue(
					userService.verifyUser("ravi", password, false).getPassword().equals(user1.getPassword()));
			System.out.println("password success");
		} catch (ServiceException e) {
			System.out.println("failure");
			assertEquals("password Invalid", e.getMessage());
		}

	}

	@Test
	public void UserCredentialsNegativeScenario() throws AppException {
		User user1 = new User();
		user1.setEmail("ravi@yoyogift.com");
		user1.setName("ravi");
		user1.setWallet(1000.00);
		user1.setPhoneNo("9876543210");

		try {
			user1.setPassword(Utility.Encryption("As23456@"));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			throw new NoSuchALgo("no such algorithm exist");
		}
		String password = "As23456@";
		List<User> user = new ArrayList<User>();
		user.add(user1);
		when(userRepository.findByEmail("ravi@yoyogift.com")).thenReturn(user1);
		try { // invalid invalid userName scenario
			Assertions.assertTrue(userService.verifyUser("rav", password, false).getEmail().equals(user1.getEmail()));
			System.out.println("success");
		} catch (ServiceException e) {
			System.out.println("Email failure");
			assertEquals("Invalid email address", e.getMessage());
		}
		try { // testing invalid password scenario
			String pwd = "As23456";
			Assertions.assertTrue(userService.verifyUser("ravi", pwd, false).getPassword().equals(user1.getPassword()));
			System.out.println("success");
		} catch (ServiceException e) {
			System.out.println("Password failure");
			assertEquals("password Invalid", e.getMessage());
		}
	}

	@Test
	public void updateUserDataPositiveScenario() throws AppException {
		User user1 = new User(); // Creating Mock data for testing positive scenario
		user1.setEmail("ravi@yoyogift.com");
		user1.setName("ravi");
		user1.setUserId(1);
		user1.setWallet(1000.00);
		user1.setPhoneNo("9876543210");
		Optional<User> optUser = Optional.of(user1);
		List<User> user = new ArrayList<User>();
		user.add(user1);
		try {
			user1.setPassword(Utility.Encryption("As23456@"));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			throw new NoSuchALgo("no such algorithm exist");
		}
		String password = "As23456@";
		User updateUser = new User(); // Updating name of the user
		updateUser.setName("ravi sharma");
		updateUser.setPhoneNo("9876543210");
		updateUser.setEmail("ravi");
		updateUser.setPassword("");
		when(userRepository.findById(1L)).thenReturn(optUser);
		try {
			Assertions.assertTrue(userService.updateUserData(updateUser, 1L).equals("profile is updated"));
			System.out.println("success");
			user1.setName("ravi sharma");
		} catch (ServiceException e) {
			System.out.println("failure");
			Assertions.fail();
		}
		when(userRepository.findById(1L)).thenReturn(optUser);
		try {
			User updateUser2 = new User();
			updateUser2.setName("ravi");
			updateUser2.setPhoneNo("9876543223"); // updating phone No of user
			updateUser2.setEmail("ravi");
			updateUser2.setPassword("");
			Assertions.assertTrue(userService.updateUserData(updateUser2, 1L).equals("profile is updated"));
			System.out.println("success");
			System.out.println(updateUser2.toString());
			user1.setPhoneNo("9876543223");
			;
		} catch (ServiceException e) {
			System.out.println("failure");
			Assertions.fail();
		}
		when(userRepository.findById(1L)).thenReturn(optUser);
		try {
			User updateUser2 = new User();
			updateUser2.setName("ravi");
			updateUser2.setPhoneNo("9876543223");
			updateUser2.setEmail("rav"); // updating email of user
			updateUser2.setPassword("");
			Assertions.assertTrue(userService.updateUserData(updateUser2, 1L).equals("profile is updated"));
			System.out.println("success");
			String email1 = "rav" + "@yoyogift.com";
			user1.setEmail(email1);
			System.out.println(updateUser2.toString());
		} catch (ServiceException e) {
			System.out.println("failure");
			Assertions.fail();
		}
		try {
			User updateUser2 = new User();
			updateUser2.setName("ravi");
			updateUser2.setPhoneNo("9876543223");
			updateUser2.setEmail("rav");
			updateUser2.setPassword("As34356@"); // updating password of user
			Assertions.assertTrue(userService.updateUserData(updateUser2, 1L).equals("profile is updated"));
			System.out.println("success");
			user1.setPassword(Utility.Encryption("As23456@"));
			System.out.println(updateUser2.toString());
		} catch (ServiceException e) {
			System.out.println("failure");
			Assertions.fail();
		} catch (NoSuchAlgorithmException e) {
			assertEquals("no such algorithm exist", e.getMessage());
		} catch (InvalidKeySpecException e) {
			System.out.println("failure");
			assertEquals("password Invalid", e.getMessage());
		}

	}

	@Test
	public void updateUserDataNegativeScenario() throws AppException {
		User user1 = new User(); // Creating Mock data for testing negative scenario
		user1.setEmail("ravi@yoyogift.com");
		user1.setName("ravi");
		user1.setUserId(1);
		user1.setWallet(1000.00);
		user1.setPhoneNo("9876543234");
		User user2 = new User(); // Creating Mock data for testing negative scenario
		user2.setEmail("rohan@yoyogift.com");
		user2.setName("rohan");
		user2.setUserId(2);
		user2.setWallet(1000.00);
		user2.setPhoneNo("9876543212");
		Optional<User> optUser = Optional.of(user1);
		List<User> user = new ArrayList<User>();
		user.add(user1);
		user.add(user2);
		try {
			user1.setPassword(Utility.Encryption("As23456@"));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			throw new NoSuchALgo("no such algorithm exist");
		}
		String password = "As23456@";
		User updateUser = new User(); // name of the user not updated
		updateUser.setName("sjahdsjdhsdfkhhdjdsfkdjshfkjdshfkdjsfhksjdfhdksjfhdskjfhdkjhfksdjfh");
		updateUser.setPhoneNo("9876543234");
		updateUser.setEmail("ravi");
		updateUser.setPassword("");
		when(userRepository.findById(1L)).thenReturn(optUser);
		try {
			Assertions.assertTrue(userService.updateUserData(updateUser, 1L).equals("profile is updated"));
			System.out.println("success");
		} catch (ServiceException e) {
			System.out.println("Name with more than 30 character failure");
			assertEquals("NAME SHOULD NOT MORE 30 CHARACTERS", e.getMessage());
		}
		try {
			User updateUser2 = new User(); // name of the user not updated
			updateUser2.setName("ravi@12");
			updateUser2.setPhoneNo("9876543234");
			updateUser2.setEmail("ravi");
			updateUser2.setPassword("");
			when(userRepository.findById(1L)).thenReturn(optUser);
			Assertions.assertTrue(userService.updateUserData(updateUser2, 1L).equals("profile is updated"));
			System.out.println("success");
		} catch (ServiceException e) {
			System.out.println("name with specila character failure");
			assertEquals("NAME SHOULD NOT CONTAIN SPECIAL CHARACTERS ONLY ALAPHBETS", e.getMessage());
		}

		when(userRepository.findById(1L)).thenReturn(optUser);
		try {
			User updateUser3 = new User();
			updateUser3.setName("ravi");
			updateUser3.setPhoneNo("9876543234"); // phone No of user not updated
			updateUser3.setEmail("ravi");
			updateUser3.setPassword("");
			Assertions.assertTrue(userService.updateUserData(updateUser3, 1L).equals("profile is updated"));
			user1.setPhoneNo(updateUser3.getPhoneNo());
		} catch (ServiceException e) {
			System.out.println("failure");
			System.out.println(e.getMessage());
		}
		when(userRepository.findById(1L)).thenReturn(optUser);
		try {
			User updateUser4 = new User();
			updateUser4.setName("ravi");
			updateUser4.setPhoneNo("9876543234");
			updateUser4.setEmail("ravi"); // same password added by user failed
			updateUser4.setPassword("As23456@");
			Assertions.assertTrue(userService.updateUserData(updateUser4, 1L).equals("profile is updated"));
			System.out.println("success");
		} catch (ServiceException e) {
			System.out.println("same password failure");
			System.out.println(e.getMessage());
		}
		try {
			User updateUser5 = new User();
			updateUser5.setName("ravi");
			updateUser5.setPhoneNo("9876543223");
			updateUser5.setEmail("rav");
			updateUser5.setPassword("As34"); // password added by user failed due to providing pwd length less than 8
			Assertions.assertTrue(userService.updateUserData(updateUser5, 1L).equals("profile is updated"));
		} catch (ServiceException e) {
			System.out.println("failure");
			System.out.println(e.getMessage());
		}
		try {
			User updateUser6 = new User();
			updateUser6.setName("ravi");
			updateUser6.setPhoneNo("9876543223");
			updateUser6.setEmail("rav");
			updateUser6.setPassword("asaa4421"); // password added by user failed due to providing pwd length less than
													// 8
			Assertions.assertTrue(userService.updateUserData(updateUser6, 1L).equals("profile is updated"));
		} catch (ServiceException e) {
			System.out.println("failure");
			System.out.println(e.getMessage());
		}
	}
}
