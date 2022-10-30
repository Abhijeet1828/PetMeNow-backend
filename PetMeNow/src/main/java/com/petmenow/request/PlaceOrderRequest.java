package com.petmenow.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.petmenow.validators.FutureDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaceOrderRequest {

	@NotNull
	private Long userId;

	private Long petId;

	@NotNull
	@Pattern(regexp = "^ADOPTION$|^FOSTER$")
	private String type;

	@NotBlank
	private String title;

	@FutureDate
	@NotNull
	private Long startDate;

	@NotNull
	private Integer durationNumber;

	@NotBlank
	@Pattern(regexp = "^WEEKS$|^DAYS$")
	private String durationType;

	private List<String> allowedPets;

}
