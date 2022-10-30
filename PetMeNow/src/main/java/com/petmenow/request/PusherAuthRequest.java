package com.petmenow.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PusherAuthRequest {

	@JsonProperty(value = "socket_id")
	private String socketId;
	
	@JsonProperty(value = "channel_name")
	private String channelName;

}
