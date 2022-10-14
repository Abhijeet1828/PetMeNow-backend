package com.petmenow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@PostMapping(value = "/request")
	public ResponseEntity<Object> placeRequest() {
		return null;
	}
	
}
