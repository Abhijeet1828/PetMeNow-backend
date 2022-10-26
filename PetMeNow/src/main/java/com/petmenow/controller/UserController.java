package com.petmenow.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petmenow.constants.FailureConstants;
import com.petmenow.constants.SuccessConstants;
import com.petmenow.exception.CommonException;
import com.petmenow.request.SignInRequest;
import com.petmenow.request.SignUpRequest;
import com.petmenow.request.UpdateUserRequest;
import com.petmenow.service.UserService;
import com.petmenow.utilities.ResponseHelper;
import com.petmenow.utilities.ValidatorUtils;

@RestController
@Validated
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
	public ResponseEntity<Object> signIn(@RequestBody @Valid SignInRequest signInRequest) throws CommonException {
		Object response = userService.userSignIn(signInRequest);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			switch (error) {
			case 1:
				return ResponseHelper.generateResponse(FailureConstants.USER_DOES_NOT_EXIST.getFailureCode(),
						FailureConstants.USER_DOES_NOT_EXIST.getFailureMsg());
			case 2:
				return ResponseHelper.generateResponse(FailureConstants.USER_PASSWORD_WRONG.getFailureCode(),
						FailureConstants.USER_PASSWORD_WRONG.getFailureMsg());
			default:
				throw new CommonException(FailureConstants.USER_SIGN_IN_ERROR.getFailureCode(),
						FailureConstants.USER_SIGN_IN_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.USER_SIGN_IN_SUCCESS.getSuccessCode(),
				SuccessConstants.USER_SIGN_IN_SUCCESS.getSuccessMsg(), response);
	}

	@PutMapping(value = "/update-details")
	public ResponseEntity<Object> updateUserDetails(@RequestBody @Valid UpdateUserRequest updateUserRequest)
			throws CommonException {
		Object response = userService.updateUserDetails(updateUserRequest);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(FailureConstants.USER_DOES_NOT_EXIST.getFailureCode(),
						FailureConstants.USER_DOES_NOT_EXIST.getFailureMsg());
			} else {
				throw new CommonException(FailureConstants.USER_UPDATE_DETAILS_ERROR.getFailureCode(),
						FailureConstants.USER_UPDATE_DETAILS_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.USER_UPDATE_DETAILS_SUCCESS.getSuccessCode(),
				SuccessConstants.USER_UPDATE_DETAILS_SUCCESS.getSuccessMsg(), response);
	}

	@GetMapping(value = "/details/{id}")
	public ResponseEntity<Object> getUserDetails(@PathVariable Long id) throws CommonException {
		Object response = userService.getUserDetails(id);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(FailureConstants.USER_DOES_NOT_EXIST.getFailureCode(),
						FailureConstants.USER_DOES_NOT_EXIST.getFailureMsg());
			} else {
				throw new CommonException(FailureConstants.USER_DETAILS_ERROR.getFailureCode(),
						FailureConstants.USER_DETAILS_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.USER_FETCH_DETAILS.getSuccessCode(),
				SuccessConstants.USER_FETCH_DETAILS.getSuccessMsg(), response);
	}

	@PostMapping(value = "/upload-image/{id}")
	public ResponseEntity<Object> uploadUserImage(@RequestPart(value = "file") MultipartFile file,
			@PathVariable Long id) throws CommonException {
		ValidatorUtils.validateFile(file, ValidatorUtils.IMAGE_TYPES);

		Object response = userService.uploadUserImage(id, file);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(FailureConstants.USER_DOES_NOT_EXIST.getFailureCode(),
						FailureConstants.USER_DOES_NOT_EXIST.getFailureMsg());
			} else {
				throw new CommonException(FailureConstants.USER_UPLOAD_IMAGE_ERROR.getFailureCode(),
						FailureConstants.USER_UPLOAD_IMAGE_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.USER_UPLOAD_IMAGE_SUCCESS.getSuccessCode(),
				SuccessConstants.USER_UPLOAD_IMAGE_SUCCESS.getSuccessMsg(), response);
	}

}
