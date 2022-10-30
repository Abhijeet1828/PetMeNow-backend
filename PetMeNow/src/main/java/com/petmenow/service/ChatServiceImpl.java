package com.petmenow.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmenow.model.ChatConnection;
import com.petmenow.repository.ChatConnectionRepository;
import com.petmenow.repository.UserRepository;
import com.petmenow.response.UserChatConnectionsResponse;
import com.petmenow.response.UserChatConnectionsResponse.UserChatConnectionHistory;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

	@Autowired
	private ChatConnectionRepository chatConnectionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public int createOrUpdateChatConnection(Long firstUserId, Long secondUserId) {
		try {
			List<ChatConnection> chatConnections = chatConnectionRepository.fetchConnectionExistForPair(firstUserId,
					secondUserId);
			if (CollectionUtils.isNotEmpty(chatConnections)) {
				ChatConnection existingConnection = chatConnections.get(0);
				existingConnection.setUpdatedTimestamp(new Date());
				chatConnectionRepository.save(existingConnection);
				return 1;
			} else {
				ChatConnection newConnection = ChatConnection.builder().firstUserId(firstUserId)
						.secondUserId(secondUserId).channelName(createChannelName(firstUserId, secondUserId))
						.createdTimestamp(new Date()).updatedTimestamp(new Date()).build();
				chatConnectionRepository.save(newConnection);
				return 2;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in createOrUpdateChatConnection", e);
			return 0;
		}
	}

	@Override
	public Object fetchUserChats(Long userId) {
		try {
			List<ChatConnection> userChatConnections = chatConnectionRepository.fetchUserChatConnections(userId);
			if (CollectionUtils.isEmpty(userChatConnections)) {
				LOGGER.info("No chats found for user {}", userId);
				return 1;
			}

			List<UserChatConnectionHistory> userChats = new ArrayList<>();
			userChatConnections.stream().forEach(chat -> {
				UserChatConnectionHistory userChatConnectionHistory = new UserChatConnectionHistory();
				userChatConnectionHistory.setId(chat.getId());
				userChatConnectionHistory.setChannelName(chat.getChannelName());
				userChatConnectionHistory.setCreatedTimestamp(chat.getCreatedTimestamp());
				userChatConnectionHistory.setUpdatedTimestamp(chat.getUpdatedTimestamp());

				if (chat.getFirstUserId().equals(userId)) {
					userChatConnectionHistory.setReceiverDetails(userRepository.findFirstById(chat.getSecondUserId()));
				} else {
					userChatConnectionHistory.setReceiverDetails(userRepository.findFirstById(chat.getFirstUserId()));
				}

				userChats.add(userChatConnectionHistory);
			});

			UserChatConnectionsResponse userChatConnectionsResponse = new UserChatConnectionsResponse();
			userChatConnectionsResponse.setUserChats(userChats);

			return userChatConnectionsResponse;
		} catch (Exception e) {
			LOGGER.error("Exception in fetchUserChats", e);
			return 0;
		}
	}

	@Override
	public Object fetchChatHistory(String channelName) {
		try {

		} catch (Exception e) {
			LOGGER.error("Exception in fetchChatHistory", e);
		}
		return null;
	}

	private String createChannelName(Long firstUserId, Long secondUserId) {
		StringBuilder stringBuilder = new StringBuilder("private-");
		stringBuilder.append(firstUserId).append("-").append(secondUserId);
		return stringBuilder.toString();
	}

}
