package com.petmenow.response;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse {

	private Status status;
	private Object responseData;

	public CommonResponse() {
		// TODO Public constructor
	}
	
	public CommonResponse(Status status, Object responseData) {
		this.status = status;
		if(responseData==null) {
			responseData = new HashMap<>();
		}
		this.responseData = responseData;
	}
	
	public CommonResponse(int statusCode, String message, String localizedMessage) {
		this.status = new Status(statusCode, message, localizedMessage);
		this.responseData = new HashMap<>();
	}
	
	public CommonResponse(int statusCode, String message) {
		this.status = new Status(statusCode, message);
		this.responseData = new HashMap<>();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "CommonResponse [status=" + status + ", responseData=" + responseData + "]";
	}

}
