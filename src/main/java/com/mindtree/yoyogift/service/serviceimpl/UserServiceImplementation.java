package com.mindtree.yoyogift.service.serviceimpl;

import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.NewPasswordCantBeAOldPassword;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.UserService;
import com.mindtree.yoyogift.utility.ReferalCodeUtil;
import com.mindtree.yoyogift.utility.Utility;

import java.util.List;

import com.mindtree.yoyogift.exception.service.*;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;
	String encryptedPassword;

	@Override
	public User getDetail(long userId) throws ServiceException {
		User userVar = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not Present"));
		return userVar;
	}

	@SuppressWarnings("static-access")
	@Override
	public User addUser(User user, String referralCode) throws ServiceException {
		// TODO Auto-generated method stub
		List<User> allUser = userRepository.findAll();
		for (User u : allUser) {
			String temp = "";
			for (int i = 0; i < u.getEmail().length(); i++) {
				if (u.getEmail().charAt(i) == '@') {
					break;
				} else {
					temp = temp + u.getEmail().charAt(i);
				}
			}
			if (temp.equalsIgnoreCase(user.getEmail())) {
				throw new EmailAlreadyExistsException("USER NAME ALREADY EXISTS");
			}
		}
		for (User u : allUser) {
			if (u.getPhoneNo().equals(user.getPhoneNo())) {
				throw new PhoneNumberExistsException("PHONE NUMBER ALREADY EXISTS");
			}
		}
		String userName = user.getName();
		if (userName.length() > 30) {
			throw new InvalidNameException("NAME SHOULD NOT MORE 30 CHARACTERS");
		} else {
			for (int i = 0; i < userName.length(); i++) {
				if ((userName.charAt(i) >= 65 && userName.charAt(i) <= 90)
						|| (userName.charAt(i) >= 97 && userName.charAt(i) <= 122) || userName.charAt(i) == 32) {

				} else {
					throw new InvalidNameException("NAME SHOULD NOT CONTAIN SPECIAL CHARACTERS ONLY ALAPHBETS");
				}
			}
		}

		String email = user.getEmail();
		if (email.length() > 25) {
			throw new InvalidEmailException("USERNAME SHOULD BE ATMOST 25 CHARACTERS");
		} else {
			if ((email.charAt(0) >= 97 && email.charAt(0) <= 122)) {

			} else {
				throw new InvalidEmailException(
						"USERNAME SHOULD NOT START WITH SPECIAL CHARACTERS OR NUMBER OR UPPERCASE");
			}
		}

		email += "@yoyogift.com";
		user.setEmail(email);
		String phone = user.getPhoneNo();
		if (phone.length() != 10) {
			throw new InvalidPhoneNumberException("PHONE NUMBER SHOULD BE 10-DIGITS");
		} else {
			if ((phone.charAt(0) >= 54) && (phone.charAt(0) <= 57)) {

			} else {
				throw new InvalidPhoneNumberException("PHONE NUMBER SHOULD START from 6,7,8,9");

			}
		}

		String password = user.getPassword();
		int count_letter = 0;
		int count_special = 0;
		int count_number = 0;
		if (password.length() < 8) {
			throw new ServiceException("PASSWORD SHOULD BE MINIMUM 8 CHARACTERS");
		} else {
			for (int i = 0; i < password.length(); i++) {
				if (((password.charAt(i) >= 65) && (password.charAt(i) <= 90))
						|| ((password.charAt(i) >= 97 && password.charAt(i) <= 122))) {
					count_letter++;
				} else if ((password.charAt(i) >= 48) && (password.charAt(i) <= 57)) {
					count_number++;
				} else {
					count_special++;
				}

			}
			if ((count_letter == 0) || (count_special == 0) || (count_number == 0)) {
				throw new InvalidPasswordException(
						"SHOULD CONTAIN ATLEAST ONE UPPERCASE,LOWERCASE,NUMBER AND SPECIAL NUMBER");
			}

			else {

				try {
					String generatedSecuredPasswordHash;
					generatedSecuredPasswordHash = Utility.Encryption(password);
					user.setPassword(generatedSecuredPasswordHash);
				} catch (NoSuchAlgorithmException e) {
					throw new NoSuchALgo("no such algorithm exist");
				} catch (InvalidKeySpecException e) {
					throw new InvalidKey("Secret Key is invalid");

				}
			}
		}

		boolean flag = false;
		String decryptedString = ReferalCodeUtil.Encoder(referralCode);
		String newString = decryptedString.substring(1);
		for (User u : allUser) {
			if (u.getPhoneNo().equalsIgnoreCase(newString)) {
				System.out.println(u.getWallet());
				u.setWallet(u.getWallet() + 50.0);
				flag = true;
				System.out.println(u.getWallet());
				break;
			} else
				flag = false;
		}
		if (flag == true)
			user.setWallet(50.0);
		else
			user.setWallet(0.0);

		return userRepository.save(user);
	}

	@Override
	public User verifyUser(String email, String password, boolean socialFlag) throws ServiceException {
		String email1 = email + "@yoyogift.com";
		User userData = null;
		try {
			userData = userRepository.findByEmail(email1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (userData == null) {
			throw new EmailNotExist("Invalid email address");
		}
		boolean result = true;
		try {
			if (password != "") {
				result = Utility.Decryption(userData.getPassword(), password);
			} else {
				result = true;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchALgo("no such algorithm exist");

		} catch (InvalidKeySpecException e) {
			throw new InvalidKey("Secret Key is invalid");

		}
		if (result == true) {
			return userData;
		} else {
			throw new passwordNotMacthed("password Invalid");
		}

	}

	@Override
	public User userDetail(String email) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User userVar = userRepository.findByEmail(email);
		if (userVar == null) {
			throw new UserNotFoundException("User is not present");
		} else {
			userVar.setWallet((double) Math.round(userVar.getWallet()));
			return userVar;
		}
	}
	/*
	 * Converting amount in yoyo money and saving into user table(update user
	 * wallet)
	 */

	@Override
	public String addMoney(long id, int amount) throws UserNotFoundException, InvalidValueException {
		User userVar = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Present"));

		if (amount < 5 || (amount % 5) != 0) {
			System.err.println(amount);
			throw new InvalidValueException("Please Enter Amount Greater than 5 and multiple of five");
		} else {
			System.err.println("Data Added Sussesfully");
			userVar.setWallet(userVar.getWallet() + (amount / 5));
			userRepository.save(userVar);
		}

		return "Hi " + userVar.getName() + " " + (amount / 5) + " Yo Yo Point added to your account";
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new ListEmptyException("No Users found...");
		} else {
			users.forEach(user -> {
				user.setPassword(null);
			});
			return users;
		}
	}

	@Override
	public String updateUserData(User updateUserData, long id) throws ServiceException {
		User user1 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not Exist"));
		List<User> users = userRepository.findAll();
		String phoneNo = user1.getPhoneNo();
		String email1 = user1.getEmail() + "@yoyogift.com";
		String name1 = user1.getName();

		if (updateUserData.getPhoneNo().equals(phoneNo)) {
			user1.setPhoneNo(user1.getPhoneNo());// phone no is updated
		} else {
			for (User u : users) {
				if (u.getPhoneNo().equals(updateUserData.getPhoneNo())) {
					throw new PhoneNumberExistsException("PHONE NUMBER ALREADY EXISTS");
				}
			}
		}
		user1.setPhoneNo(updateUserData.getPhoneNo()); // phone no is updated
		String password = updateUserData.getPassword();
		int count_letter = 0;
		int count_special = 0;
		int count_number = 0;
		if (password.equals("")) {
			user1.setPassword(user1.getPassword());
		} else if (password.length() < 8) {
			throw new ServiceException("PASSWORD SHOULD BE MINIMUM 8 CHARACTERS");
		} else {
			for (int i = 0; i < password.length(); i++) {
				if (((password.charAt(i) >= 65) && (password.charAt(i) <= 90))
						|| ((password.charAt(i) >= 97 && password.charAt(i) <= 122))) {
					count_letter++;
				} else if ((password.charAt(i) >= 48) && (password.charAt(i) <= 57)) {
					count_number++;
				} else {
					count_special++;
				}

			}
			if ((count_letter == 0) || (count_special == 0) || (count_number == 0)) {
				throw new InvalidPasswordException(
						"SHOULD CONTAIN ATLEAST ONE UPPERCASE,LOWERCASE,NUMBER AND SPECIAL NUMBER");
			}

			else {

				try {
					boolean check = Utility.Decryption(user1.getPassword(), updateUserData.getPassword());
					if (check == true) {
						throw new NewPasswordCantBeAOldPassword("New password can't be same as old password");
					} else {
						String generatedSecuredPasswordHash;
						generatedSecuredPasswordHash = Utility.Encryption(password);
						user1.setPassword(generatedSecuredPasswordHash); // password is updated
					}
				} catch (NoSuchAlgorithmException e) {
					throw new NoSuchALgo("no such algorithm exist");
				} catch (InvalidKeySpecException e) {
					throw new InvalidKey("Secret Key is invalid");

				}
			}
		}

		String userName = updateUserData.getName();
		if (userName.equals(name1)) {
			user1.setName(user1.getName());
		} else {
			if (userName.length() > 30) {
				throw new InvalidNameException("NAME SHOULD NOT MORE 30 CHARACTERS");
			} else {
				for (int i = 0; i < userName.length(); i++) {
					if ((userName.charAt(i) >= 65 && userName.charAt(i) <= 90)
							|| (userName.charAt(i) >= 97 && userName.charAt(i) <= 122) || userName.charAt(i) == 32) {

					} else {
						throw new InvalidNameException("NAME SHOULD NOT CONTAIN SPECIAL CHARACTERS ONLY ALAPHBETS");
					}
				}
			}
			user1.setName(updateUserData.getName());
		}

//		String email = updateUserData.getEmail();
//		if (email.equals(email1)) {
//			user1.setEmail(user1.getEmail());
//		} else {
//			if (email.length() > 25) {
//				throw new InvalidEmailException("USERNAME SHOULD BE ATMOST 25 CHARACTERS");
//			} else {
//				if ((email.charAt(0) >= 97 && email.charAt(0) <= 122)) {
//
//				} else {
//					throw new InvalidEmailException(
//							"USERNAME SHOULD NOT START WITH SPECIAL CHARACTERS OR NUMBER OR UPPERCASE");
//				}
//			}
//			email += "@yoyogift.com";
//			user1.setEmail(email);
//		}
		userRepository.save(user1);
		return "profile is updated";
	}
}
