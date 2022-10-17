package com.petmenow.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AcceptOrderRequest {

	@NotNull
	private Long orderId;
	
	@NotNull
	private Long userId;
	
	private Long petId;

}
