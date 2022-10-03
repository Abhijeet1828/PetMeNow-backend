package com.petmenow.exception;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.petmenow.constants.FailureConstants;
import com.petmenow.response.CommonResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER_LOCAL = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(CommonException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommonResponse handleException(HttpServletResponse response, CommonException ex) {
		LOGGER_LOCAL.error("Global Common exception handled!   statusCode = {}, Message = {}", ex.getStatusCode(),
				ex.getLocalizedMessage());
		return new CommonResponse(ex.getStatusCode(), ex.getMessage(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody CommonResponse handleConstraintViolationException(ConstraintViolationException ex) {
		LOGGER_LOCAL.error("Global ConstraintViolationException Handled! Message = {} ", ex.getMessage(), ex);
		return new CommonResponse(FailureConstants.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getFailureCode(),
				FailureConstants.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getFailureMsg(), ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommonResponse handleException(Exception ex) {
		LOGGER_LOCAL.error("new Global exception handled!    Message {}", ex.getMessage(), ex);
		return new CommonResponse(FailureConstants.INTERNAL_SERVER_ERROR.getFailureCode(),
				FailureConstants.INTERNAL_SERVER_ERROR.getFailureMsg(), ex.getMessage());
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommonResponse handleException(NullPointerException ex) {
		LOGGER_LOCAL.error("NullPointerException handled!    Message {}", ex.getMessage(), ex);
		return new CommonResponse(FailureConstants.INTERNAL_SERVER_ERROR.getFailureCode(),
				FailureConstants.INTERNAL_SERVER_ERROR.getFailureMsg(), ex.getMessage());
	}

}
