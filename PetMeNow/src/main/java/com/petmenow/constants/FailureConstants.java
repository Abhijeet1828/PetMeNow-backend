package com.petmenow.constants;

public enum FailureConstants {
	
	INTERNAL_SERVER_ERROR(-1000, "Oops! Something went wrong. Please try again later"),
	METHOD_ARGUMENT_NOT_VALID_EXCEPTION(-1001, "Invalid Request! Method Argument Not Valid"),
	FILE_TYPE_EXCEPTION(-1002, "Invalid file extension! Please try again with a valid file"),
	NO_HANDLER_FOUND_EXCEPTION(-1003, "Invalid Request! No Handler Found"),
	USER_ALREADY_EXISTS(-2000, "Oops! Looks like the user already exists"),
	USER_SIGN_UP_ERROR(-2001, "Oops! Something went wrong while creating user"),
	USER_DOES_NOT_EXIST(-2002, "Oops! User not found"),
	USER_DETAILS_ERROR(-2003, "Oops! Something went wrong while fetching user information"),
	USER_PASSWORD_WRONG(-2004, "Username or password does not match"),
	USER_SIGN_IN_ERROR(-2005, "Oops! Something went wrong while signing in. Please try again later!"),
	USER_UPDATE_DETAILS_ERROR(-2006, "Oops! Something went wrong while updating user information"),
	REGISTER_PET_ERROR(-2007, "Oops! Something went wrong while registering pet"),
	PET_NOT_FOUND(-2008, "Oops! Pet information not found"),
	DELETE_PET_ERROR(-2009, "Oops! Something went wrong while deleting the pet information"),
	UPDATE_PET_ERROR(-2010, "Oops! Something went wrong while updating the pet information"),
	FETCH_PET_ERROR(-2011, "Oops! Something went wrong while fetching the pet information"),
	PET_TYPE_FETCH_ERROR(-2012, "Oops! Something went wrong while fetching pet types"),
	PET_BREED_SEARCH_ERROR(-2013, "Oops! Something went wrong while searching pet breeds"),
	USER_UPLOAD_IMAGE_ERROR(-2014, "Oops! Something went wrong while upload user image"),
	PET_UPLOAD_IMAGE_ERROR(-2015, "Oops! Something went wrong while upload pet image"),
	PLACE_ORDER_REQUEST_ERROR(-2016, "Oops! Something went wrong while placing your request"),
	ORDER_NOT_FOUND(-2017, "Oops! No such order exists"),
	ACCEPT_ORDER_ERROR(-2018, "Oops! Something went wrong while accepting the request"),
	FETCH_ORDER_HISTORY_USER_ERROR(-2019, "Oops! Something went wrong while fetching order history for user"),
	OWN_ORDER_ACCEPT_ERROR(-2020, "Oops! Cannot accept your own order request"),
	NO_PET_FOR_FOSTER(-2021, "Oops! No pet selected for foster request"),
	DELETE_ORDER_ERROR(-2022, "Oops! Something went wrong while deleting order"),
	FETCH_USER_CHATS_ERROR(-2023, "Oops! Something went wrong while fetching user chats");
	
	private final int failureCode;
	private final String failureMsg;

	private FailureConstants(int failureCode, String failureMsg) {
		this.failureCode = failureCode;
		this.failureMsg = failureMsg;
	}

	public int getFailureCode() {
		return failureCode;
	}

	public String getFailureMsg() {
		return failureMsg;
	}

	@Override
	public String toString() {
		return Integer.toString(failureCode) + "-" + failureMsg;
	}

}
