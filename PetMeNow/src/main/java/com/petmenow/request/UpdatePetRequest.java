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
public class UpdatePetRequest {
	
	@NotNull
	private Long id;
	
	@NotBlank
	private String petName;
	
	@NotBlank
	private String petType;
	
	@NotBlank
	private String petBreed;
	
	@PositiveOrZero
	private Integer age;
	
	private Boolean isVaccinated;
	
	@NotBlank
	private String description;
}
