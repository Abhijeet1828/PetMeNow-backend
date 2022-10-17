package com.petmenow.service;

public interface HistoryService {

	public Object fetchUserHistory(Long userId);
	
	public Object fetchActiveRequests();
}
