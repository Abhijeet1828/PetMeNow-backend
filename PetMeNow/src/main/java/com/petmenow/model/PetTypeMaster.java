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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pet_type_master")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PetTypeMaster implements Serializable {

	private static final long serialVersionUID = -8917419209160777390L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "pet_type", nullable = false)
	private String petType;
	
	@JsonIgnore
	@Column(name = "created_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	@JsonIgnore
	@Column(name = "updated_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTimestamp;

}
