package com.petmenow.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmenow.request.SignUpRequest;
import com.petmenow.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/hello")
	public String hello() {
		String message = "Hello AWS Elastic Beanstalk!";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message += " From host: " + ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return message;
	}
	
	
	public ResponseEntity<Object> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
		return null;
	}
	

}
