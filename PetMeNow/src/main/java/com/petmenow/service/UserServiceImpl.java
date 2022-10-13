package com.petmenow.service;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmenow.model.UserDetails;
import com.petmenow.repository.UserRepository;
import com.petmenow.request.SignInRequest;
import com.petmenow.request.SignUpRequest;
import com.petmenow.request.UpdateUserRequest;

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

			if (userRepository.existsByUserName(signUpRequest.getUserName())) {
				LOGGER.error("Username already exists");
				return 2;
			}

			UserDetails newUser = UserDetails.builder().firstName(signUpRequest.getFirstName())
					.lastName(signUpRequest.getLastName()).userName(signUpRequest.getUserName())
					.email(signUpRequest.getEmail()).password(signUpRequest.getPassword())
					.phoneNumber(signUpRequest.getPhoneNumber()).createdTimestamp(new Date())
					.updatedTimestamp(new Date()).build();

			newUser = userRepository.save(newUser);
			LOGGER.info("User created with id {}", newUser.getId());

			return newUser;
		} catch (Exception e) {
			LOGGER.error("Exception in userSignUp", e);
			return 0;
		}
	}

	@Override
	public Object userSignIn(SignInRequest signInRequest) {
		try {
			UserDetails userDetails = userRepository.findFirstByUserName(signInRequest.getUserName());
			if (Objects.isNull(userDetails)) {
				LOGGER.error("No user exists with username {}", signInRequest.getUserName());
				return 1;
			}

			if (!signInRequest.getPassword().equals(userDetails.getPassword())) {
				LOGGER.error("Wrong password for username {}", signInRequest.getUserName());
				return 2;
			}

			LOGGER.info("Successful signIn from username {}", signInRequest.getUserName());
			return userDetails;
		} catch (Exception e) {
			LOGGER.error("Exception in userSignIn", e);
			return 0;
		}
	}

	@Override
	public Object updateUserDetails(UpdateUserRequest updateUserRequest) {
		try {
			UserDetails userDetails = userRepository.findFirstById(updateUserRequest.getId());
			if (Objects.isNull(userDetails)) {
				LOGGER.error("No user found by ID {}", updateUserRequest.getId());
				return 1;
			}

			BeanUtils.copyProperties(updateUserRequest, userDetails, "dateOfBirth", "id");
			userDetails.setDateOfBirth(new Date(updateUserRequest.getDateOfBirth()));
			userDetails.setUpdatedTimestamp(new Date());

			userRepository.save(userDetails);

			return userDetails;
		} catch (Exception e) {
			LOGGER.error("Exception in updateUserDetails", e);
			return 0;
		}
	}

	@Override
	public Object getUserDetails(Long id) {
		try {
			UserDetails userDetails = userRepository.findFirstById(id);
			if (Objects.isNull(userDetails)) {
				LOGGER.error("No user found by ID {}", id);
				return 1;
			}

			return userDetails;
		} catch (Exception e) {
			LOGGER.error("Exception in getUserDetails", e);
			return 0;
		}
	}

}
