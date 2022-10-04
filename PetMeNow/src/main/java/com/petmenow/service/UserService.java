package com.petmenow.service;

import com.petmenow.request.SignInRequest;
import com.petmenow.request.SignUpRequest;
import com.petmenow.request.UpdateUserRequest;

public interface UserService {
	
	public Object userSignUp(SignUpRequest signUpRequest);
	
	public Object userSignIn(SignInRequest signInRequest);
	
	public Object updateUserDetails(UpdateUserRequest updateUserRequest);
	
	public Object getUserDetails(Long id);

}
