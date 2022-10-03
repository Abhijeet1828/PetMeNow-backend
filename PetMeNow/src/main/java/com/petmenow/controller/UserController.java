package com.petmenow.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmenow.constants.FailureConstants;
import com.petmenow.constants.SuccessConstants;
import com.petmenow.exception.CommonException;
import com.petmenow.request.SignUpRequest;
import com.petmenow.service.UserService;
import com.petmenow.utilities.ResponseHelper;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/sign-up")
	public ResponseEntity<Object> signUp(@RequestBody @Valid SignUpRequest signUpRequest) throws CommonException {
		Object response = userService.userSignUp(signUpRequest);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(FailureConstants.USER_ALREADY_EXISTS.getFailureCode(),
						FailureConstants.USER_ALREADY_EXISTS.getFailureMsg());
			} else {
				throw new CommonException(FailureConstants.USER_SIGN_UP_ERROR.getFailureCode(),
						FailureConstants.USER_SIGN_UP_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.USER_SIGN_UP_SUCCESS.getSuccessCode(),
				SuccessConstants.USER_SIGN_UP_SUCCESS.getSuccessMsg(), response);
	}
	
	@PostMapping(value = "/sign-in")
	public ResponseEntity<Object> signIn() {
		return null;
	}
	
	@PutMapping(value = "/update-details")
	public ResponseEntity<Object> updateUserDetails() {
		return null;
	}
	
	@GetMapping(value = "/details")
	public ResponseEntity<Object> getUserDetails() {
		return null;
	}
	
	

}
