package com.petmenow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails implements Serializable {
	
	private static final long serialVersionUID = 7590297035946935858L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NonNull
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NonNull
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@NonNull
	@Column(name = "user_password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@NonNull
	@Column(name = "phone_number", nullable = false)
	private Long phoneNumber;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "created_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	@Column(name = "updated_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTimestamp;
	
	@OneToMany(targetEntity = PetInformation.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<PetInformation> ownerPetList;

}
