package com.petmenow.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {
	private int statusCode;
	private String message;
	private String localizedMessage;

	public Status() {
	}

	public Status(int statusCode, String message, String localizedMessage) {
		this.statusCode = statusCode;
		this.message = message;
		this.localizedMessage = localizedMessage;
	}

	public Status(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
		this.localizedMessage = message;

	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLocalizedMessage() {
		return localizedMessage;
	}

	public void setLocalizedMessage(String localizedMessage) {
		this.localizedMessage = localizedMessage;
	}

	@Override
	public String toString() {
		return "Status [statusCode=" + statusCode + ", message=" + message + ", localizedMessage=" + localizedMessage
				+ "]";
	}

}
