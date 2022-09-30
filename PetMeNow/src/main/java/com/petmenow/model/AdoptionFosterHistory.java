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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "adoption_foster_history")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdoptionFosterHistory implements Serializable {

	private static final long serialVersionUID = -682262384937619322L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "history_type", nullable = false)
	private String type;
	
	@Column(name = "start_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "duration_number")
	private Integer durationNumber;
	
	@Column(name = "duration_type")
	private String durationType;
	
	@Column(name = "history_status", nullable = false)
	private String status;
	
	@Column(name = "pet_id")
	private Long petId;
	
	@Column(name = "owner_id", nullable = false)
	private Long ownerId;
	
	@Column(name = "accepted_user_id", nullable = false)
	private Long acceptedUserId;
	
	@Column(name = "allowed_pets", nullable = false)
	private String allowedPets;
	
	@Column(name = "created_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	@Column(name = "updated_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTimestamp;

}
