package com.petmenow.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmenow.repository.UserRepository;
import com.petmenow.request.SignUpRequest;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Object userSignUp(SignUpRequest signUpRequest) {
		
		return null;
	}

}
