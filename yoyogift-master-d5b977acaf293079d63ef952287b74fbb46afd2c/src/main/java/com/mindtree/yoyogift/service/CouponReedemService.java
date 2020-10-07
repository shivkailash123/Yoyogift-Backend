package com.mindtree.yoyogift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.GiftCardDto;
import com.mindtree.yoyogift.dto.RedeemableGiftCardDTO;
import com.mindtree.yoyogift.exception.service.ServiceException;
@Service
public interface CouponReedemService {

	String couponReedem(GiftCardDto giftCardDto)
			throws ServiceException;
	public List<RedeemableGiftCardDTO> sendGiftCardsByUser(long userId) throws ServiceException;
	public List<RedeemableGiftCardDTO> redeemGiftCardByUser(long userId, long giftCardId) throws ServiceException;

}
