package com.petmenow.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chat_history")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "channel_name", nullable = false)
	private String channelName;
	
	@Column(name = "message_id", nullable = false)
	private String messageId;
	
	@Column(name = "message_type", nullable = false)
	private String messageType;
	
	@Column(name = "message_body", nullable = false)
	private String messageText;
	
	@Column(name = "message", nullable = false)
	private String message;
	
	@Column(name = "created_by", nullable = false)
	private Long createdBy;
	
	@Column(name = "message_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date messageTime;
	
	@Column(name = "created_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

}
