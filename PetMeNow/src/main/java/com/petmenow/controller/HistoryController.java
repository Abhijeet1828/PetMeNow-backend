package com.petmenow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmenow.constants.FailureConstants;
import com.petmenow.constants.SuccessConstants;
import com.petmenow.exception.CommonException;
import com.petmenow.service.HistoryService;
import com.petmenow.utilities.ResponseHelper;

@RestController
@RequestMapping(value = "/history")
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<Object> fetchUserRequestHistory(@PathVariable Long id) throws CommonException {
		Object response = historyService.fetchUserHistory(id);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			switch (error) {
			case 1:
				return ResponseHelper.generateResponse(FailureConstants.USER_DOES_NOT_EXIST.getFailureCode(),
						FailureConstants.USER_DOES_NOT_EXIST.getFailureMsg());
			case 2:
				return ResponseHelper.generateResponse(SuccessConstants.NO_ORDER_HISTORY_FOUND.getSuccessCode(),
						SuccessConstants.NO_ORDER_HISTORY_FOUND.getSuccessMsg());
			default:
				throw new CommonException(FailureConstants.FETCH_ORDER_HISTORY_USER_ERROR.getFailureCode(),
						FailureConstants.FETCH_ORDER_HISTORY_USER_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.ORDER_HISTORY_FETCH_SUCCESS.getSuccessCode(),
				SuccessConstants.ORDER_HISTORY_FETCH_SUCCESS.getSuccessMsg(), response);
	}

	@GetMapping(value = "/active")
	public ResponseEntity<Object> fetchActiveRequests() throws CommonException {
		Object response = historyService.fetchActiveRequests();
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(SuccessConstants.NO_ACTIVE_REQUESTS_FOUND.getSuccessCode(),
						SuccessConstants.NO_ACTIVE_REQUESTS_FOUND.getSuccessMsg());
			} else {
				throw new CommonException(FailureConstants.FETCH_ORDER_HISTORY_USER_ERROR.getFailureCode(),
						FailureConstants.FETCH_ORDER_HISTORY_USER_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.ACTIVE_REQUESTS_FETCH_SUCCESS.getSuccessCode(),
				SuccessConstants.ACTIVE_REQUESTS_FETCH_SUCCESS.getSuccessMsg(), response);
	}

}
