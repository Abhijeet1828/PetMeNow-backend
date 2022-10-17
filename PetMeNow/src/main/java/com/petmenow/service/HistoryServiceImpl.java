package com.petmenow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmenow.constants.Constants;
import com.petmenow.model.AdoptionFosterHistory;
import com.petmenow.model.UserDetails;
import com.petmenow.repository.AdoptionFosterHistoryRepository;
import com.petmenow.repository.PetInformationRepository;
import com.petmenow.repository.UserRepository;
import com.petmenow.response.ActiveRequestHistoryResponse;
import com.petmenow.response.ActiveRequestHistoryResponse.ActiveOrderDetails;
import com.petmenow.response.UserRequestHistoryResponse;
import com.petmenow.response.UserRequestHistoryResponse.OrderDetails;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HistoryServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdoptionFosterHistoryRepository adoptionFosterHistoryRepository;

	@Autowired
	private PetInformationRepository petInformationRepository;

	@Override
	public Object fetchUserHistory(Long userId) {
		try {
			UserDetails userDetails = userRepository.findFirstById(userId);
			if (Objects.isNull(userDetails)) {
				LOGGER.error("No user found by ID {}", userId);
				return 1;
			}

			List<AdoptionFosterHistory> userOrderHistory = adoptionFosterHistoryRepository.findByOwnerId(userId);
			if (CollectionUtils.isEmpty(userOrderHistory)) {
				LOGGER.info("No order history found for user {}", userId);
				return 2;
			}
			UserRequestHistoryResponse userRequestHistoryResponse = new UserRequestHistoryResponse();
			userRequestHistoryResponse.setUserDetails(userDetails);

			List<OrderDetails> orderDetailList = new ArrayList<>();
			userOrderHistory.stream().forEach(order -> {
				OrderDetails orderDetails = new OrderDetails();
				BeanUtils.copyProperties(order, orderDetails);

				if (Objects.nonNull(order.getPetId())) {
					orderDetails.setPetDetails(petInformationRepository.findFirstById(order.getPetId()));
				}

				if (Objects.nonNull(order.getAcceptedUserId())) {
					orderDetails.setAcceptedUserDetails(userRepository.findFirstById(order.getAcceptedUserId()));
				}

				orderDetailList.add(orderDetails);
			});

			userRequestHistoryResponse.setOrderDetails(orderDetailList);
			return userRequestHistoryResponse;
		} catch (Exception e) {
			LOGGER.error("Exception in fetchUserHistory", e);
			return 0;
		}
	}

	@Override
	public Object fetchActiveRequests() {
		try {
			LOGGER.info("Start Date Today {}", new Date());
			List<AdoptionFosterHistory> activeOrders = adoptionFosterHistoryRepository
					.findByStatusAndStartDateBefore(Constants.ORDER_STATUS_REQUESTED, new Date());
			if (CollectionUtils.isEmpty(activeOrders)) {
				LOGGER.error("No active orders found");
				return 1;
			}

			ActiveRequestHistoryResponse activeRequestHistoryResponse = new ActiveRequestHistoryResponse();
			List<ActiveOrderDetails> activeOrderDetailsList = new ArrayList<>();
			activeOrders.stream().forEach(order -> {
				ActiveOrderDetails activeOrderDetails = new ActiveOrderDetails();
				BeanUtils.copyProperties(order, activeOrderDetails);

				if (Objects.nonNull(order.getOwnerId())) {
					activeOrderDetails.setUserDetails(userRepository.findFirstById(order.getOwnerId()));
				}

				if (Objects.nonNull(order.getPetId())) {
					activeOrderDetails.setPetDetails(petInformationRepository.findFirstById(order.getPetId()));
				}

				activeOrderDetailsList.add(activeOrderDetails);
			});

			activeRequestHistoryResponse.setActiveOrders(activeOrderDetailsList);
			return activeRequestHistoryResponse;
		} catch (Exception e) {
			LOGGER.error("Exception in fetchActiveRequests", e);
			return 0;
		}
	}

}
