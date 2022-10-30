package com.petmenow.service;

public interface ChatService {
	
	public int createOrUpdateChatConnection(Long firstUserId, Long secondUserId);
	
	public Object fetchUserChats(Long userId);
	
	public Object fetchChatHistory(String channelName);

}
