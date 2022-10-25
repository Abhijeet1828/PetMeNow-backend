package com.petmenow.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateUserRequest {
	
	@NotNull
	@Positive
	private Long id;

	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String userName;
		
	@Email
	@NotBlank
	private String email;
	
	@NotNull
	@Positive
	private Long phoneNumber;
	
	@NotBlank
	private String address;
	
	@NotNull
	@Positive
	private Long dateOfBirth;

}
