package com.petmenow.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegisterPetRequest {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String petType;
	
	@NotBlank
	private String petBreed;
	
	@PositiveOrZero
	private Integer age;
	
	private Boolean vaccinated;
	
	@NotNull
	private Long ownerId;
	
	@NotBlank
	private String description;

}
