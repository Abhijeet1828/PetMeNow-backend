package com.petmenow.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PusherEventMessage implements Serializable {

	private static final long serialVersionUID = 2125717911230557490L;

	@JsonProperty("time_ms")
	private long time;

	private List<PusherEvent> events;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PusherEvent implements Serializable {

		private static final long serialVersionUID = 4495358242787997371L;

		private String name;

		private String channel;

		private String event;

		private String data;

		@JsonProperty(value = "socket_id")
		private String socketId;

	}

}
