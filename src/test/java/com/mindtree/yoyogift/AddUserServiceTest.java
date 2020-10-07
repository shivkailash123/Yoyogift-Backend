package com.mindtree.yoyogift;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AddUserServiceTest {
	
	
	@Autowired
	UserService userservice;
	
	@MockBean
	UserRepository userRepository;
	
	   @BeforeEach
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	   
	   
	   @Test
	   public void addUserTest()
	   {
		   //List<User> user=new ArrayList<User>();
		   User user=new User();
		   user.setEmail("ankit.prusty");
		   user.setName("Ankit Prusty");
		   user.setPhoneNo("9438359603");
		   user.setPassword("Ankit@1998");
		   String referral="ihcfdcgcdg";
		   try {
			userservice.addUser(user,referral);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	   }
		   @Test
		   public void addUserNegativeTest()
		   {
			   
			   User user =new User();
			   user.setEmail("ankit.prusty");
			   user.setName("Ankit Prusty@");
			   user.setPhoneNo("9438359603");
			   user.setPassword("Ankit@1998");
			   String referral="ihcfdcgcdg";
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   Assertions.assertEquals("NAME SHOULD NOT CONTAIN SPECIAL CHARACTERS ONLY ALAPHBETS", e.getMessage());
			   }
			   
			   user.setEmail("ankit.prusty");
			   user.setName("ahouytreasdfghjbnkoiuytrertuyrt");
			   user.setPhoneNo("9438359603");
			   user.setPassword("Ankit@1998");
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   //System.out.print(e.getMessage());
				   Assertions.assertEquals("NAME SHOULD NOT MORE 30 CHARACTERS", e.getMessage());
			   }
			   
			   user.setEmail("ankit.prusty123456yuoiuyrfgh");
			   user.setName("ankit prusty");
			   user.setPhoneNo("9438359603");
			   user.setPassword("Ankit@1998");
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   //System.out.print(e.getMessage());
				   Assertions.assertEquals("USERNAME SHOULD BE ATMOST 25 CHARACTERS", e.getMessage());
			   }
			   user.setEmail("@ankit.prusty");
			   user.setName("ankit prusty");
			   user.setPhoneNo("9438359603");
			   user.setPassword("Ankit@1998");
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   //System.out.print(e.getMessage());
				   Assertions.assertEquals("USERNAME SHOULD NOT START WITH SPECIAL CHARACTERS OR NUMBER OR UPPERCASE", e.getMessage());
			   }
			   
			   user.setEmail("ankit.prusty");
			   user.setName("ankit prusty");
			   user.setPhoneNo("94383596031");
			   user.setPassword("Ankit@1998");
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   //System.out.print(e.getMessage());
				   Assertions.assertEquals("PHONE NUMBER SHOULD BE 10-DIGITS", e.getMessage());
			   }
			   user.setEmail("ankit.prusty");
			   user.setName("ankit prusty");
			   user.setPhoneNo("1438359603");
			   user.setPassword("Ankit@1998");
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   //System.out.print(e.getMessage());
				   Assertions.assertEquals("PHONE NUMBER SHOULD START from 6,7,8,9", e.getMessage());
			   }
			   user.setEmail("ankit.prusty");
			   user.setName("ankit prusty");
			   user.setPhoneNo("9438359603");
			   user.setPassword("Ankit@1");
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   //System.out.print(e.getMessage());
				   Assertions.assertEquals("PASSWORD SHOULD BE MINIMUM 8 CHARACTERS", e.getMessage());
			   }
			   user.setEmail("ankit.prusty");
			   user.setName("ankit prusty");
			   user.setPhoneNo("9438359603");
			   user.setPassword("ankit@1998");
			   try
			   {
				   userservice.addUser(user,referral);
				   
			   }catch(ServiceException e)
			   {
				   //System.out.print(e.getMessage());
				   Assertions.assertEquals("SHOULD CONTAIN ATLEAST ONE UPPERCASE,LOWERCASE,NUMBER AND SPECIAL NUMBER", e.getMessage());
			   }
			   
			   
			   
		   }
		   
		   @Test
		   public void addUserNegativeTests()
		   {
			   String referral="ihcfdcgcdg";
			   User user=new User();
			   user.setEmail("ankit.prusty@yoyogift.com");
			   user.setName("Ankit Prusty");
			   user.setPhoneNo("9438359603");
			   user.setPassword("Ankit@1998");
			   User user1=new User();
			   user1.setEmail("ankit.prusty1998@yoyogift.com");
			   user1.setName("Ankit Prusty");
			   user1.setPhoneNo("9438359602");
			   user1.setPassword("Ankit@1998");
			   
			   List<User> u=new ArrayList<User>();
			   u.add(user);
			   u.add(user1);
			   Mockito.lenient().when(this.userRepository.findAll()).thenReturn(u);
			   
			   User user2=new User();
			   user2.setEmail("ankit.prusty");
			   user2.setName("Ankit Prusty");
			   user2.setPhoneNo("9438359612");
			   user2.setPassword("Ankit@1998");
			   try
			   {
				   userservice.addUser(user2,referral);
				   
			   }
			   catch(ServiceException e)
			   {
				  // System.out.println(e.getMessage());
				   Assertions.assertEquals("USER NAME ALREADY EXISTS", e.getMessage());
				   
			   }
			   
			   user2.setEmail("ankit.prusty123");
			   user2.setName("Ankit Prusty");
			   user2.setPhoneNo("9438359603");
			   user2.setPassword("Ankit@1998");
			   try
			   {
				   userservice.addUser(user2,referral);
				   
			   }
			   catch(ServiceException e)
			   {
				  // System.out.println(e.getMessage());
				   Assertions.assertEquals("PHONE NUMBER ALREADY EXISTS", e.getMessage());
				   
			   }
			   
			   
			   
		   }
		   
		  
		   
		  
		   
		    
	   

	 

}
