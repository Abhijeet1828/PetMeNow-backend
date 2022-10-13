package com.petmenow.constants;

public enum FailureConstants {
	
	INTERNAL_SERVER_ERROR(-1000, "Oops! Something went wrong. Please try again later"),
	METHOD_ARGUMENT_NOT_VALID_EXCEPTION(-1001, "Invalid Request! Method Argument Not Valid"),
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
	PET_BREED_SEARCH_ERROR(-2013, "Oops! Something went wrong while searching pet breeds");
	
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
