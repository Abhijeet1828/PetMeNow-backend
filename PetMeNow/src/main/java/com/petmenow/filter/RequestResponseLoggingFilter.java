package com.petmenow.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.google.gson.JsonObject;
import com.petmenow.utilities.RequestResponse;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

		long startTime = System.currentTimeMillis();
		filterChain.doFilter(requestWrapper, responseWrapper);
		long timeTaken = System.currentTimeMillis() - startTime;

		String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

		Enumeration<String> headerNames = request.getHeaderNames();
		JsonObject jsonObject = new JsonObject();
		while (headerNames.hasMoreElements()) {
			String currentHeaderName = headerNames.nextElement();
			jsonObject.addProperty(currentHeaderName, request.getHeader(currentHeaderName));
		}

		RequestResponse requestResponse = RequestResponse.builder().path(request.getRequestURI())
				.queryString(request.getQueryString()).httpMethod(request.getMethod()).payload(requestBody)
				.httpStatus(response.getStatus()).response(responseBody).timeTakenMillis(timeTaken).headers(jsonObject)
				.build();
		LOGGER.info("{}", requestResponse);

		responseWrapper.copyBodyToResponse();
	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Exception in RequestResponseLoggingFilter.getStringValue", e);
		}
		return StringUtils.EMPTY;
	}

}
