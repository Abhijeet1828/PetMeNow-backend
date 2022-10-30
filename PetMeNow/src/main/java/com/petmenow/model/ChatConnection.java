package com.petmenow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chat_connection")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatConnection implements Serializable {

	private static final long serialVersionUID = 6180218009147211772L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_user_id", nullable = false)
	private Long firstUserId;

	@Column(name = "second_user_id", nullable = false)
	private Long secondUserId;

	@Column(name = "channel_name", nullable = false, unique = true)
	private String channelName;

	@JsonIgnore
	@Column(name = "created_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	@JsonIgnore
	@Column(name = "updated_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTimestamp;

}
