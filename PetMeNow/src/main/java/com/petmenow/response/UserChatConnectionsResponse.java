package com.petmenow.response;

import java.util.Date;
import java.util.List;

import com.petmenow.model.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserChatConnectionsResponse {

	private List<UserChatConnectionHistory> userChats;

	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	public static class UserChatConnectionHistory {

		private Long id;

		private UserDetails receiverDetails;

		private String channelName;

		private Date createdTimestamp;

		private Date updatedTimestamp;
	}

}
