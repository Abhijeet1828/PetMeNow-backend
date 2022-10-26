package com.petmenow.service;

import com.petmenow.request.AcceptOrderRequest;
import com.petmenow.request.PlaceOrderRequest;

public interface OrderService {

	public Object placeOrder(PlaceOrderRequest placeOrderRequest);
	
	public Object acceptOrder(AcceptOrderRequest acceptOrderRequest);
	
	public int deleteOrder(Long orderId);
}
