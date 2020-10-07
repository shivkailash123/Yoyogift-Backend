package com.mindtree.yoyogift.service;

import java.util.Date;

import com.mindtree.yoyogift.dto.StatisticsDTO;
import com.mindtree.yoyogift.exception.service.ServiceException;

public interface CommonService {
	public StatisticsDTO generateStatistics() throws ServiceException;
	public StatisticsDTO getProductStatistics(StatisticsDTO dto);
	public StatisticsDTO getUserStatistics(StatisticsDTO dto);
	public StatisticsDTO getUserJoiningStatistics(StatisticsDTO dto, Date changeDate, Date date);
	public StatisticsDTO getOrdersStatistics(StatisticsDTO dto);
	public StatisticsDTO getOrderPurchaseStatistics(StatisticsDTO dto, Date changeDate, Date date);
}
