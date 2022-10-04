package com.petmenow.constants;

public enum SuccessConstants {
	
	USER_SIGN_UP_SUCCESS(2000, "User created successfully"),
	USER_FETCH_DETAILS(2001, "User details fetched successfully"),
	USER_SIGN_IN_SUCCESS(2002, "User signed in successfully"),
	USER_UPDATE_DETAILS_SUCCESS(2003, "User details updated successfully");
	
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
