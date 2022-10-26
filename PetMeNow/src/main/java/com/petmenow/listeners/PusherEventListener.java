package com.petmenow.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import com.petmenow.request.PusherEventMessage.PusherEvent;

@Component
public class PusherEventListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PusherEventListener.class);

	@SqsListener(value = "pusher-event-queue", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
	public void listen(PusherEvent pusherEvent) {
		LOGGER.info("PusherEventQueue message {}", pusherEvent);
	}

}
