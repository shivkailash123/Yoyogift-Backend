package com.mindtree.yoyogift.controller;

import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.yoyogift.dto.AdminOrderDTO;
import com.mindtree.yoyogift.dto.FeedBackDisplayDto;
import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.dto.GiftCardDto;
import com.mindtree.yoyogift.dto.OrderDto;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.ProfileDTO;
import com.mindtree.yoyogift.dto.RedeemableGiftCardDTO;
import com.mindtree.yoyogift.dto.ResponseBody;
import com.mindtree.yoyogift.dto.StatisticsDTO;
import com.mindtree.yoyogift.dto.UpdateProductDto;
import com.mindtree.yoyogift.dto.UserCredentialsDTO;
import com.mindtree.yoyogift.dto.UserDTO;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.service.CommonService;
import com.mindtree.yoyogift.service.CouponReedemService;
import com.mindtree.yoyogift.service.EmailService;
import com.mindtree.yoyogift.service.FeedBackService;
import com.mindtree.yoyogift.service.ProductOrderService;
import com.mindtree.yoyogift.service.ProductService;
import com.mindtree.yoyogift.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/yoyogift")
public class YoYoGift {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommonService commonService;

	@Autowired
	private FeedBackService feedbackService;

	@Autowired
	private CouponReedemService couponReedemService;
	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailservice;

	@GetMapping
	public ResponseEntity<?> display() throws AppException {
		return new ResponseEntity<ResponseBody<List<ProductDto>>>(new ResponseBody<List<ProductDto>>(null, true,
				modelMapper.map(productService.findAll(), new TypeToken<List<ProductDto>>() {
				}.getType())), HttpStatus.OK);
	}

	@GetMapping("/hi")
	public String getHiAsOutput() throws AppException {
		return "Hi its working";
	}
	// Display Particular Product
	@GetMapping("/getProductDetails/{productId}")
	public ResponseEntity<?> getProductDetail(@PathVariable long productId) throws AppException {
		return new ResponseEntity<ResponseBody<ProductDto>>(
				new ResponseBody<ProductDto>(null, true, productService.getDetails(productId)), HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable long productId) throws AppException {
		return new ResponseEntity<ResponseBody<ProductDto>>(new ResponseBody<ProductDto>(null, true,
				modelMapper.map(productService.deleteProduct(productId), new TypeToken<ProductDto>() {
				}.getType())), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody Product product) throws AppException {
		return new ResponseEntity<>(this.productService.addProduct(product), HttpStatus.OK);
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> addUserDetails(@RequestBody UserDTO userDto) throws AppException {
		User user = userService.addUser(modelMapper.map(userDto, User.class),userDto.getReferralCode());
		//user.setPassword(null);
		return new ResponseEntity<ResponseBody<UserDTO>>(
				new ResponseBody<UserDTO>(null, true, modelMapper.map(user, UserDTO.class)), HttpStatus.OK);

	}

	@PostMapping("/couponRedeem") // couponservice
	public ResponseEntity<?> couponReedem(@RequestBody GiftCardDto giftCardDto) throws AppException {

		String varResponse = couponReedemService.couponReedem(giftCardDto);

		return new ResponseEntity<ResponseBody<String>>(new ResponseBody<String>(null, true, varResponse),
				HttpStatus.OK);

	}

	@PostMapping("/productorder/{productid}/{userid}/{quantity}")
	public ResponseEntity<?> productOrder(@PathVariable long productid, @PathVariable long userid,
			@PathVariable int quantity) throws AppException {

		String varResponse = productOrderService.productOrder(productid, userid, quantity);
		return new ResponseEntity<ResponseBody<String>>(new ResponseBody<String>(null, true, varResponse),
				HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<?> verifyUser(@RequestBody UserCredentialsDTO userCredentials) throws AppException {
		return new ResponseEntity<ResponseBody<ProfileDTO>>(
				new ResponseBody<ProfileDTO>(null, true,
						modelMapper.map(userService.verifyUser(userCredentials.getEmail(),
								userCredentials.getPassword(), userCredentials.isSocialFlag()),
								new TypeToken<ProfileDTO>() {
								}.getType())),
				HttpStatus.OK);
	}

	@GetMapping("/userdetail/{email}")
	public ResponseEntity<?> userDetail(@PathVariable String email) throws AppException {

		User varResponse = userService.userDetail(email);

		varResponse.setPassword(null);

		return new ResponseEntity<ResponseBody<User>>(new ResponseBody<User>(null, true, varResponse), HttpStatus.OK);

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() throws AppException {
		return new ResponseEntity<ResponseBody<List<User>>>(
				new ResponseBody<List<User>>(null, true, userService.getAllUsers()), HttpStatus.OK);
	}

	@PostMapping("/addMoney/{id}/{amount}")
	public ResponseEntity<?> addMoney(@PathVariable long id, @PathVariable int amount) throws AppException {

		String varResponse = userService.addMoney(id, amount);

		return new ResponseEntity<ResponseBody<String>>(new ResponseBody<String>(null, true, varResponse),
				HttpStatus.OK);

	}

	@GetMapping("/getGiftCardsByUser/{id}")
	public ResponseEntity<?> getGiftCardsByUser(@PathVariable long id) throws AppException {
		List<RedeemableGiftCardDTO> dtos = this.couponReedemService.sendGiftCardsByUser(id);
		return new ResponseEntity<ResponseBody<List<RedeemableGiftCardDTO>>>(
				new ResponseBody<List<RedeemableGiftCardDTO>>(null, true, dtos), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUserData(@RequestBody ProfileDTO updateProfileData, @PathVariable long id) throws AppException
	{
		String response=userService.updateUserData(modelMapper.map(updateProfileData,User.class),id);
		return new ResponseEntity<ResponseBody<String>>(
				new ResponseBody<String>(null,true,response),HttpStatus.OK);
		
	}

//Feedback Function
	@PostMapping("/feedback/{productId}/{userId}/{orderId}")
	public ResponseEntity<?> addFeedbackDetail(@PathVariable long productId, @PathVariable long userId,
			@PathVariable long orderId, @Valid @RequestBody FeedBackDto feedback) throws AppException {
		return new ResponseEntity<ResponseBody<FeedBackDto>>(new ResponseBody<FeedBackDto>(null, true, modelMapper.map(
				feedbackService.addFeedback(modelMapper.map(feedback, FeedBack.class), productId, userId, orderId),
				FeedBackDto.class)), HttpStatus.OK);
	}

	@GetMapping("/getReviewsRating/{productId}")
	public ResponseEntity<?> displayAllReviews(@PathVariable long productId) throws AppException {
		return new ResponseEntity<ResponseBody<List<FeedBackDisplayDto>>>(
				new ResponseBody<List<FeedBackDisplayDto>>(null, true, modelMapper.map(
						feedbackService.displayAllReviewsFromDb(productId), new TypeToken<List<FeedBackDisplayDto>>() {
						}.getType())),
				HttpStatus.OK);
	}

	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody UpdateProductDto product) throws AppException {
		Product prod = productService.updateProduct(modelMapper.map(product, Product.class));
		return new ResponseEntity<ResponseBody<UpdateProductDto>>(
				new ResponseBody<UpdateProductDto>(null, true, modelMapper.map(prod, UpdateProductDto.class)),
				HttpStatus.OK);

	}

	@GetMapping("/getOrders/{userEmail}")
	public ResponseEntity<List<OrderDto>> getOrders(@PathVariable String userEmail) {
		return new ResponseEntity<List<OrderDto>>(productOrderService.getOrders(userEmail), HttpStatus.OK);
	}

	@GetMapping("/redeemGiftCard/{userId}/{giftCardId}")
	public ResponseEntity<?> redeemGiftCardsByUser(@PathVariable long userId, @PathVariable long giftCardId)
			throws AppException {
		List<RedeemableGiftCardDTO> dtos = this.couponReedemService.redeemGiftCardByUser(userId, giftCardId);
		return new ResponseEntity<ResponseBody<List<RedeemableGiftCardDTO>>>(
				new ResponseBody<List<RedeemableGiftCardDTO>>(null, true, dtos), HttpStatus.OK);
	}

	@GetMapping("/getAllOrders")
	public ResponseEntity<?> getAllOrders() throws AppException {
		return new ResponseEntity<ResponseBody<List<AdminOrderDTO>>>(
				new ResponseBody<List<AdminOrderDTO>>(null, true, this.productOrderService.getAllOrders()),
				HttpStatus.OK);
	}

	@PostMapping("/emailservice/{email}/{id}")
	public ResponseEntity<?> emailService(@PathVariable String email,@PathVariable long id) throws AppException {
		System.out.println(email);
		System.out.println(id);
		String varResponse = emailservice.sendNotification(email, id);

		return new ResponseEntity<ResponseBody<String>>(new ResponseBody<String>(null, true, varResponse),
				HttpStatus.OK);

	}
	
	@GetMapping("/getStatisticsData")
	public ResponseEntity<?> getStatisticsData() throws AppException {
		return new ResponseEntity<ResponseBody<StatisticsDTO>>(new ResponseBody<StatisticsDTO>(null, true, this.commonService.generateStatistics()), HttpStatus.OK);
	}

}
