package com.petmenow.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInRequest {

	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;

}
