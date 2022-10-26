package com.petmenow.service;

import com.petmenow.request.PusherEventMessage;

public interface WebhookService {
	
	public void postPusherEventToSQS(PusherEventMessage pusherEventMessage);

}
