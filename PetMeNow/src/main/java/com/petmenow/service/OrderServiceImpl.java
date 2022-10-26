package com.petmenow.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmenow.constants.Constants;
import com.petmenow.model.AdoptionFosterHistory;
import com.petmenow.repository.AdoptionFosterHistoryRepository;
import com.petmenow.repository.PetInformationRepository;
import com.petmenow.repository.UserRepository;
import com.petmenow.request.AcceptOrderRequest;
import com.petmenow.request.PlaceOrderRequest;
import com.petmenow.utilities.DateTimeUtilities;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PetInformationRepository petInformationRepository;

	@Autowired
	private AdoptionFosterHistoryRepository adoptionFosterHistoryRepository;

	@Override
	public Object placeOrder(PlaceOrderRequest placeOrderRequest) {
		try {
			if (!userRepository.existsById(placeOrderRequest.getUserId())) {
				LOGGER.error("No user found by ID {}", placeOrderRequest.getUserId());
				return 1;
			}

			if (placeOrderRequest.getType().equalsIgnoreCase(Constants.ORDER_TYPE_ADOPTION)
					&& (Objects.isNull(placeOrderRequest.getPetId())
							|| !petInformationRepository.existsById(placeOrderRequest.getPetId()))) {
				LOGGER.error("Either no petId provided or pet does not exist for order type {}",
						placeOrderRequest.getType());
				return 2;
			}

			String allowedPets = CollectionUtils.isNotEmpty(placeOrderRequest.getAllowedPets())
					? String.join(",", placeOrderRequest.getAllowedPets())
					: null;

			LocalDate endDate = DateTimeUtilities.convertToLocalDate(new Date(placeOrderRequest.getStartDate()));
			endDate = placeOrderRequest.getDurationType().equalsIgnoreCase(Constants.DURATION_DAYS)
					? endDate.plusDays(placeOrderRequest.getDurationNumber())
					: endDate.plusWeeks(placeOrderRequest.getDurationNumber());

			AdoptionFosterHistory history = AdoptionFosterHistory.builder().title(placeOrderRequest.getTitle())
					.type(placeOrderRequest.getType()).startDate(new Date(placeOrderRequest.getStartDate()))
					.durationNumber(placeOrderRequest.getDurationNumber())
					.durationType(placeOrderRequest.getDurationType()).status(Constants.ORDER_STATUS_REQUESTED)
					.petId(placeOrderRequest.getPetId()).ownerId(placeOrderRequest.getUserId())
					.createdTimestamp(new Date()).updatedTimestamp(new Date())
					.endDate(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
					.allowedPets(allowedPets).build();

			return adoptionFosterHistoryRepository.save(history);
		} catch (Exception e) {
			LOGGER.error("Exception in placeOrder", e);
			return 0;
		}
	}

	@Override
	public Object acceptOrder(AcceptOrderRequest acceptOrderRequest) {
		try {
			if (!userRepository.existsById(acceptOrderRequest.getUserId())) {
				LOGGER.error("No user found by ID {}", acceptOrderRequest.getUserId());
				return 1;
			}

			if (!adoptionFosterHistoryRepository.existsById(acceptOrderRequest.getOrderId())) {
				LOGGER.error("No order found by ID {}", acceptOrderRequest.getOrderId());
				return 2;
			}

			AdoptionFosterHistory adoptionFosterHistory = adoptionFosterHistoryRepository
					.findFirstById(acceptOrderRequest.getOrderId());

			if (acceptOrderRequest.getUserId().equals(adoptionFosterHistory.getOwnerId())) {
				LOGGER.error("Cannot accept own order request");
				return 3;
			}

			if (adoptionFosterHistory.getType().equalsIgnoreCase(Constants.ORDER_TYPE_FOSTER)
					&& Objects.isNull(acceptOrderRequest.getPetId())) {
				LOGGER.error("No pet defined for foster request");
				return 4;
			}

			adoptionFosterHistory.setAcceptedUserId(acceptOrderRequest.getUserId());
			adoptionFosterHistory.setPetId(acceptOrderRequest.getPetId());
			adoptionFosterHistory.setStatus(Constants.ORDER_STATUS_ACCEPTED);
			adoptionFosterHistory.setUpdatedTimestamp(new Date());

			return adoptionFosterHistoryRepository.save(adoptionFosterHistory);
		} catch (Exception e) {
			LOGGER.error("Exception in acceptOrder", e);
			return 0;
		}
	}

	@Override
	public int deleteOrder(Long orderId) {
		try {
			if (!adoptionFosterHistoryRepository.existsById(orderId)) {
				LOGGER.error("No order found by ID {}", orderId);
				return 1;
			}

			AdoptionFosterHistory adoptionFosterHistory = adoptionFosterHistoryRepository.findFirstById(orderId);

			adoptionFosterHistoryRepository.delete(adoptionFosterHistory);
			return 2;
		} catch (Exception e) {
			LOGGER.error("Exception in deleteOrder", e);
			return 0;
		}
	}

}
