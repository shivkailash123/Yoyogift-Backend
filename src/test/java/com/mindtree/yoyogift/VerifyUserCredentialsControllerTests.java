package com.mindtree.yoyogift;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.yoyogift.controller.YoYoGift;
import com.mindtree.yoyogift.dto.ProfileDTO;
import com.mindtree.yoyogift.dto.ResponseBody;
import com.mindtree.yoyogift.dto.UserCredentialsDTO;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.service.serviceimpl.UserServiceImplementation;
import com.mindtree.yoyogift.utility.Utility;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class VerifyUserCredentialsControllerTests {

	@Autowired
	private ModelMapper modelMapper;

	@MockBean
	private UserServiceImplementation userService;
	
	@Autowired
	private YoYoGift yoyogiftController;

//	@BeforeEach
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	public void checkUserLoginCredentialsPositiveTest() throws AppException {
		try {
			User user1 = new User();
			user1.setEmail("ss888@yoyogift.com");
			user1.setName("ravi");
			user1.setPhoneNo("9876543210");
			user1.setWallet(1000.00);
			user1.setPassword(Utility.Encryption("Abc123@"));
			UserCredentialsDTO userCredentials = new UserCredentialsDTO();
			userCredentials.setEmail("ss888");
			userCredentials.setPassword("Abc123@");

			userCredentials.setSocialFlag(false);

			Mockito.lenient().when(userService.verifyUser(userCredentials.getEmail(), userCredentials.getPassword(), false))
					.thenReturn(user1);

			ResponseBody<ProfileDTO> responseBody = new ResponseBody<ProfileDTO>(null, true,
					modelMapper.map(
							userService.verifyUser(userCredentials.getEmail(), userCredentials.getPassword(), false),
							new TypeToken<ProfileDTO>() {
							}.getType()));

			ResponseEntity<ResponseBody<ProfileDTO>> expected = new ResponseEntity<ResponseBody<ProfileDTO>>(
					responseBody, HttpStatus.OK);

			ResponseEntity<?> actual = yoyogiftController.verifyUser(userCredentials);

			System.out.println("success");

			Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
			
		} catch (Exception e) {
			System.err.println("failure");
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void updateUserDDataPositiveTest() throws AppException {
		try {
			User user1 = new User();
			user1.setEmail("ss888@yoyogift.com");
			user1.setName("ravi");
			user1.setPhoneNo("9876543210");
			user1.setWallet(1000.00);
			user1.setUserId(1L);
			user1.setPassword("Abc123@");
			user1.setCoupoun(null);
			user1.setOrders(null);
			ProfileDTO updateProfileData = new ProfileDTO();
			updateProfileData.setName("ravi sharma");
			updateProfileData.setEmail("ss888");
			updateProfileData.setPassword("");
			updateProfileData.setPhoneNo("9876543210");

			Mockito.lenient()
					.when(userService.updateUserData(modelMapper.map(updateProfileData, User.class), user1.getUserId()))
					.thenReturn("profile is updated");

			ResponseBody<String> responseBody = new ResponseBody<String>(null, true,
					userService.updateUserData(modelMapper.map(updateProfileData, User.class), user1.getUserId()));

			ResponseEntity<ResponseBody<String>> expected = new ResponseEntity<ResponseBody<String>>(responseBody,
					HttpStatus.OK);

			ResponseEntity<?> actual = yoyogiftController.updateUserData(updateProfileData, 1L);
			Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
			System.out.println("sucess");

		} catch (Exception e) {
			System.out.println("failure");
			System.out.println(e.getMessage());
		}
	}

}
