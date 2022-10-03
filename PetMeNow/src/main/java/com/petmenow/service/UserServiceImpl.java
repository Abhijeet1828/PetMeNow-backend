package com.petmenow.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmenow.model.UserDetails;
import com.petmenow.repository.UserRepository;
import com.petmenow.request.SignUpRequest;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Object userSignUp(SignUpRequest signUpRequest) {
		try {
			if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
				LOGGER.error("User with phone number already exists");
				return 1;
			}

			UserDetails newUser = UserDetails.builder().firstName(signUpRequest.getFirstName())
					.lastName(signUpRequest.getLastName()).userName(signUpRequest.getUserName())
					.email(signUpRequest.getEmail()).password(signUpRequest.getPassword())
					.phoneNumber(signUpRequest.getPhoneNumber()).build();

			newUser = userRepository.save(newUser);
			LOGGER.info("User created with id {}", newUser.getId());

			return newUser;
		} catch (Exception e) {
			LOGGER.error("Exception in userSignUp", e);
			return 2;
		}
	}

}
