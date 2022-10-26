package com.petmenow.controller;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petmenow.request.PusherEventMessage;
import com.petmenow.service.WebhookService;
import com.petmenow.utilities.TypeConversionUtils;
import com.pusher.rest.Pusher;
import com.pusher.rest.data.Validity;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebhookController.class);

	private static final String HEADER_PUSHER_KEY = "X-Pusher-Key";
	private static final String HEADER_PUSHER_SIGNATURE = "X-Pusher-Signature";

	@Autowired
	private WebhookService webhookService;

	@Autowired
	private Pusher pusher;

	@PostMapping(value = "/private")
	public @ResponseBody String privateWebhooks(@RequestBody String json,
			@RequestHeader(value = HEADER_PUSHER_KEY) String pusherKey,
			@RequestHeader(value = HEADER_PUSHER_SIGNATURE) String pusherSignature, ServletResponse response) {

		if (authenticate(json, pusherKey, pusherSignature)) {
			PusherEventMessage pusherEventMessage = TypeConversionUtils.convertToCustomClass(json,
					PusherEventMessage.class);
			webhookService.postPusherEventToSQS(pusherEventMessage);
		} else {
			LOGGER.error("Invalid Request for {}", json);
		}

		((HttpServletResponse) response).setStatus(HttpStatus.OK.value());
		return StringUtils.EMPTY;
	}

	/**
	 * This method authenticates the Webhook from Pusher.
	 * 
	 * @param request
	 * @param servletRequest
	 * @return
	 */
	private boolean authenticate(String json, String pusherKey, String pusherSignature) {
		Validity validity = pusher.validateWebhookSignature(pusherKey, pusherSignature, json);
		return Validity.VALID.equals(validity);
	}

}
