package com.petmenow.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmenow.constants.FailureConstants;
import com.petmenow.constants.SuccessConstants;
import com.petmenow.exception.CommonException;
import com.petmenow.request.AcceptOrderRequest;
import com.petmenow.request.PlaceOrderRequest;
import com.petmenow.service.OrderService;
import com.petmenow.utilities.ResponseHelper;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/request")
	public ResponseEntity<Object> placeRequest(@RequestBody @Valid PlaceOrderRequest placeOrderRequest)
			throws CommonException {
		Object response = orderService.placeOrder(placeOrderRequest);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			switch (error) {
			case 1:
				return ResponseHelper.generateResponse(FailureConstants.USER_DOES_NOT_EXIST.getFailureCode(),
						FailureConstants.USER_DOES_NOT_EXIST.getFailureMsg());
			case 2:
				return ResponseHelper.generateResponse(FailureConstants.PET_NOT_FOUND.getFailureCode(),
						FailureConstants.PET_NOT_FOUND.getFailureMsg());
			default:
				throw new CommonException(FailureConstants.PLACE_ORDER_REQUEST_ERROR.getFailureCode(),
						FailureConstants.PLACE_ORDER_REQUEST_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.PLACE_ORDER_REQUEST_SUCCESS.getSuccessCode(),
				SuccessConstants.PLACE_ORDER_REQUEST_SUCCESS.getSuccessMsg(), response);
	}

	@PostMapping(value = "/accept")
	public ResponseEntity<Object> acceptRequest(@RequestBody @Valid AcceptOrderRequest acceptOrderRequest)
			throws CommonException {
		Object response = orderService.acceptOrder(acceptOrderRequest);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			switch (error) {
			case 1:
				return ResponseHelper.generateResponse(FailureConstants.USER_DOES_NOT_EXIST.getFailureCode(),
						FailureConstants.USER_DOES_NOT_EXIST.getFailureMsg());
			case 2:
				return ResponseHelper.generateResponse(FailureConstants.ORDER_NOT_FOUND.getFailureCode(),
						FailureConstants.ORDER_NOT_FOUND.getFailureMsg());
			case 3:
				return ResponseHelper.generateResponse(FailureConstants.OWN_ORDER_ACCEPT_ERROR.getFailureCode(),
						FailureConstants.OWN_ORDER_ACCEPT_ERROR.getFailureMsg());
			case 4:
				return ResponseHelper.generateResponse(FailureConstants.NO_PET_FOR_FOSTER.getFailureCode(),
						FailureConstants.NO_PET_FOR_FOSTER.getFailureMsg());
			default:
				throw new CommonException(FailureConstants.ACCEPT_ORDER_ERROR.getFailureCode(),
						FailureConstants.ACCEPT_ORDER_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.ACCEPT_ORDER_SUCCESS.getSuccessCode(),
				SuccessConstants.ACCEPT_ORDER_SUCCESS.getSuccessMsg(), response);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> deleteRequest(@PathVariable Long id) throws CommonException {
		int response = orderService.deleteOrder(id);
		switch (response) {
		case 1:
			return ResponseHelper.generateResponse(FailureConstants.ORDER_NOT_FOUND.getFailureCode(),
					FailureConstants.ORDER_NOT_FOUND.getFailureMsg());
		case 2:
			return ResponseHelper.generateResponse(SuccessConstants.DELETE_ORDER_SUCCESS.getSuccessCode(),
					SuccessConstants.DELETE_ORDER_SUCCESS.getSuccessMsg());
		default:
			throw new CommonException(FailureConstants.DELETE_ORDER_ERROR.getFailureCode(),
					FailureConstants.DELETE_ORDER_ERROR.getFailureMsg());
		}
	}
}
