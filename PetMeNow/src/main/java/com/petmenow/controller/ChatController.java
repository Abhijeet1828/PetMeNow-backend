package com.petmenow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petmenow.constants.FailureConstants;
import com.petmenow.constants.SuccessConstants;
import com.petmenow.exception.CommonException;
import com.petmenow.request.PusherAuthRequest;
import com.petmenow.service.ChatService;
import com.petmenow.utilities.ResponseHelper;
import com.pusher.rest.Pusher;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private Pusher pusher;

	@PostMapping(value = "/pusher/authenticate")
	public @ResponseBody String pusherAuthenticate(@RequestBody PusherAuthRequest pusherAuthRequest) {
		return pusher.authenticate(pusherAuthRequest.getSocketId(), pusherAuthRequest.getChannelName());
	}

	@GetMapping(value = "/all/{id}")
	public ResponseEntity<Object> fetchUserChats(@PathVariable Long id) throws CommonException {
		Object response = chatService.fetchUserChats(id);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(SuccessConstants.NO_CHATS_FOUND.getSuccessCode(),
						SuccessConstants.NO_CHATS_FOUND.getSuccessMsg());
			} else {
				throw new CommonException(FailureConstants.FETCH_USER_CHATS_ERROR.getFailureCode(),
						FailureConstants.FETCH_USER_CHATS_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.USER_FETCH_CHATS_SUCCESS.getSuccessCode(),
				SuccessConstants.USER_FETCH_CHATS_SUCCESS.getSuccessMsg(), response);
	}
	
	@GetMapping(value = "/history/{channelName}")
	public ResponseEntity<Object> fetchChatHistory(@PathVariable String channelName) {
		return null;
	}

}
