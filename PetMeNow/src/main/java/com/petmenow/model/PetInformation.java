package com.petmenow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pet_information")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PetInformation implements Serializable {
	
	private static final long serialVersionUID = -5904168363537111101L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pet_name", nullable = false)
	private String name;
	
	@Column(name = "pet_type", nullable = false)
	private String petType;
	
	@Column(name = "pet_breed", nullable = false)
	private String petBreed;
	
	@Column(name = "age", nullable = false)
	private Integer age;
	
	@Column(name = "is_vaccinated", nullable = false)
	private Boolean vaccinated;
	
	@Column(name = "image", nullable = false)
	private String image;
	
	@Column(name = "owner_id", nullable = false)
	private Long ownerId;
	
	@Column(name = "pet_description")
	private String description;
	
	@Column(name = "created_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	@Column(name = "updated_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTimestamp;
	
	@ManyToOne(targetEntity = UserDetails.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
	private UserDetails ownerDetails;
	
	
}
