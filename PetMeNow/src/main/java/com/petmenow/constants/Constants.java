package com.petmenow.constants;

public final class Constants {

	private Constants() {
		throw new IllegalStateException("Constants class cannot be instantiated");
	}
	
	public static final String ORDER_TYPE_ADOPTION = "ADOPTION";
	public static final String ORDER_TYPE_FOSTER = "FOSTER";
	
	public static final String ORDER_STATUS_REQUESTED = "REQUESTED";
	public static final String ORDER_STATUS_ACCEPTED = "ACCEPTED";
	
	public static final String DURATION_WEEKS = "WEEKS";
	public static final String DURATION_DAYS = "DAYS";

}
