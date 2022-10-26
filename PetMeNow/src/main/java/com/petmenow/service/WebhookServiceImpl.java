package com.petmenow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmenow.request.PusherEventMessage;
import com.petmenow.utilities.SQSUtils;
import com.petmenow.utilities.TypeConversionUtils;

@Service
@Transactional
public class WebhookServiceImpl implements WebhookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebhookServiceImpl.class);

	@Value("${amazonProperties.sqs.pusherQueueName}")
	private String pusherQueueName;

	@Autowired
	private SQSUtils sqsUtils;

	@Async
	@Override
	public void postPusherEventToSQS(PusherEventMessage pusherEventMessage) {
		try {
			LOGGER.info("Sending Pusher events to Queue for Event Message {}", pusherEventMessage);
			pusherEventMessage.getEvents().stream().forEach(
					event -> sqsUtils.sendMessage(pusherQueueName, TypeConversionUtils.convertObjectToString(event)));
		} catch (Exception e) {
			LOGGER.error("Exception in postPusherEventToSQS", e);
		}
	}

}
