package com.petmenow.constants;

public enum SuccessConstants {
	
	USER_SIGN_UP_SUCCESS(2000, "User created successfully"),
	USER_FETCH_DETAILS(2001, "User details fetched successfully"),
	USER_SIGN_IN_SUCCESS(2002, "User signed in successfully"),
	USER_UPDATE_DETAILS_SUCCESS(2003, "User details updated successfully"),
	REGISTER_PET_SUCCESS(2004, "Pet registered successfully"),
	PET_DELETE_SUCCESS(2005, "Pet information deleted successfully"),
	PET_UPDATE_SUCCESS(2006, "Pet information updated successfully"),
	PET_FETCH_SUCCESS(2007, "Pet information fetched successfully"),
	PET_TYPE_FETCH_SUCCESS(2008, "Pet types fetched successfully"),
	PET_BREED_SEARCH_SUCCESS(2009, "Pet breeds searched successfully"),
	USER_UPLOAD_IMAGE_SUCCESS(2010, "User image uploaded successfully"),
	PET_UPLOAD_IMAGE_SUCCESS(2011, "Pet image uploaded successfully"),
	PLACE_ORDER_REQUEST_SUCCESS(2012, "Request placed successfully"),
	ACCEPT_ORDER_SUCCESS(2013, "Order accepted successfully"),
	NO_ORDER_HISTORY_FOUND(2014, "No order history found"),
	ORDER_HISTORY_FETCH_SUCCESS(2015, "Order history for user fetched successfully"),
	NO_ACTIVE_REQUESTS_FOUND(2016, "No active requests found"),
	ACTIVE_REQUESTS_FETCH_SUCCESS(2017, "Active requests fetched successfully");
	
	private final int successCode;
	private final String successMsg;

	private SuccessConstants(int successCode, String successMsg) {
		this.successCode = successCode;
		this.successMsg = successMsg;
	}

	public int getSuccessCode() {
		return successCode;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	@Override
	public String toString() {
		return Integer.toString(successCode) + "-" + successMsg;
	}

}
