package com.mindtree.yoyogift.service.serviceimpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.GiftCardDto;
import com.mindtree.yoyogift.dto.RedeemableGiftCardDTO;
import com.mindtree.yoyogift.entity.Coupouns;
import com.mindtree.yoyogift.entity.Messages;
import com.mindtree.yoyogift.entity.Orders;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.EntityNotFoundException;
import com.mindtree.yoyogift.exception.service.InvalidException;
import com.mindtree.yoyogift.exception.service.InvalidValueException;
import com.mindtree.yoyogift.exception.service.ListEmptyException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.OrderIsNotFoundException;
import com.mindtree.yoyogift.exception.service.custom.ProductNotFoundException;
import com.mindtree.yoyogift.exception.service.custom.UserNotFoundException;
import com.mindtree.yoyogift.repository.CoupounRepository;
import com.mindtree.yoyogift.repository.MessageRepository;
import com.mindtree.yoyogift.repository.OrderRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.CouponReedemService;

@Service
public class CouponServiceImplementation implements CouponReedemService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CoupounRepository coupounRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public String couponReedem(GiftCardDto giftCardDto) throws ServiceException {
		// TODO Auto-generated method stub
		/*
		 * Fetching User Recevier and Product data from backend
		 */

		/*
		 * Checking if reciver is admin
		 */
		if (giftCardDto.getReciverEmail().equalsIgnoreCase(("admin@yoyogift.com"))) {
			throw new UserNotFoundException("Please send to valid user");
		}
		User recevierUser;
		int quantity = giftCardDto.getProductQuantity();
		User userdata = userRepository.findById(giftCardDto.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		if (userdata.getEmail().equals("admin@yoyogift.com")) {
			throw new UserNotFoundException("Please login as user");
		}
		try {
			recevierUser = userRepository.findByEmail(giftCardDto.getReciverEmail());
			if (recevierUser == null) {
				throw new UserNotFoundException(" Receiver User not found");
			}

		} catch (Exception e) {
			throw new UserNotFoundException(" Receiver User not found");
		}
		if (recevierUser.getEmail().equalsIgnoreCase(userdata.getEmail())) {
			throw new UserNotFoundException("Receiver Email and User are same");
		}
		Product productVar = productRepository.findById(giftCardDto.getProductId())
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

		/*
		 * Checking Balance is suffieent in the wallet
		 */

		if (userdata.getWallet() - giftCardDto.getVoucherAmount() < 0) {
			throw new InvalidValueException("Balance insufficent");
		} else {

			userdata.setWallet(userdata.getWallet() - giftCardDto.getVoucherAmount());
		}

		/*
		 * Checking Sufficent Quentity is present in the backend
		 */

		if (productVar.getStock() - quantity < 0) {
			throw new InvalidValueException("Out of Stock");
		} else {
			productVar.setStock(productVar.getStock() - quantity);
		}

		userRepository.save(userdata);
		userRepository.save(recevierUser);

		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy
		// HH:mm:ss");
		Date date = new Date();

		// dtf.format(now)
		/*
		 * Saving The message in Message table
		 */

		Messages varMessage = new Messages();

		varMessage.setContent(giftCardDto.getMessage());
		varMessage.setDate(date);
		varMessage.setReceiver(recevierUser);
		varMessage.setSender(userdata);

		messageRepository.save(varMessage);

		/*
		 * creating coupoun
		 */
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate dateVar = LocalDate.now().plusYears(1);

		Date dateVar1 = Date.from(dateVar.atStartOfDay(defaultZoneId).toInstant());

		Coupouns coupouns = new Coupouns();
		coupouns.setAmount(giftCardDto.getVoucherAmount());
		coupouns.setDateOfExpiry(dateVar1);

		coupouns.setCoupounCode(this.CoupounCode());

		coupouns.setRedeemed(false);
		coupouns.setReedemedBy(recevierUser);
		coupouns.setRedeemedOn(date);
		coupouns.setMessage(varMessage);
		try {
			coupounRepository.save(coupouns);
		} catch (Exception e) {
			throw new InvalidException("Sorry Data not get saved");
		}

		/*
		 * saving details in order table
		 */

		Orders varOrders = new Orders();
		varOrders.setAmount(giftCardDto.getVoucherAmount());
		varOrders.setProductQuantity(quantity);
		varOrders.setOrderDate(date);
		varOrders.setUser(userdata);
		varOrders.setProduct(productVar);

		Orders orders = orderRepository.save(varOrders);
		try {
			return "submitted Succesfully with order Id:" + orders.getOrderId();
		} catch (Exception e) {
			throw new OrderIsNotFoundException("Order Id is not present");
		}

	}
	/*
	 * Generating coupouncode and returning into the fuction
	 */

	public String CoupounCode() {

		int n = 20;

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));

		}
		return sb.toString();

	}

	@Override
	public List<RedeemableGiftCardDTO> sendGiftCardsByUser(long userId) throws ServiceException {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		List<Coupouns> giftCards = this.coupounRepository.findByReedemedByOrderByIsRedeemedAscDateOfExpiryAsc(user);
		if (giftCards.isEmpty()) {
			throw new ListEmptyException("No Gift Cards Found..");
		} else {
			List<RedeemableGiftCardDTO> dtos = new ArrayList<>();
			giftCards.forEach(giftCard -> {
				RedeemableGiftCardDTO dto = new RedeemableGiftCardDTO();
				dto.setId(giftCard.getId());
				dto.setCoupounCode(giftCard.getCoupounCode());
				dto.setAmount(giftCard.getAmount());
				dto.setDateOfExpiry(giftCard.getDateOfExpiry());
				dto.setRedeemed(giftCard.isRedeemed());
				dto.setRedeemedOn(giftCard.getRedeemedOn());
				dto.setWalletBalance(user.getWallet());
				dto.setSenderEmail(giftCard.getMessage().getSender().getEmail());
				dto.setMessage(giftCard.getMessage().getContent());
				dtos.add(dto);
			});
			return dtos;
		}
	}

	@Override
	public List<RedeemableGiftCardDTO> redeemGiftCardByUser(long userId, long giftCardId) throws ServiceException {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		Coupouns giftCard = this.coupounRepository.findById(giftCardId)
				.orElseThrow(() -> new EntityNotFoundException("Gift Card not found."));
		Date date = new Date();
		if (giftCard.isRedeemed()) {
			throw new InvalidException("Gift card already redeemed");
		} else if (!giftCard.getReedemedBy().equals(user)) {
			throw new InvalidException("Gift card does not belong to User ID.");
		} else if (date.after(giftCard.getDateOfExpiry())) {
			throw new InvalidException("Gift Card has Expired.");
		} else {
			giftCard.setRedeemed(true);
			user.setWallet(user.getWallet() + giftCard.getAmount());
			this.userRepository.save(user);
			this.coupounRepository.save(giftCard);
		}
		return this.sendGiftCardsByUser(userId);
	}
}
