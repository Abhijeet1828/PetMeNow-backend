package com.petmenow.response;

import java.util.Date;
import java.util.List;

import com.petmenow.model.PetInformation;
import com.petmenow.model.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ActiveRequestHistoryResponse {

	List<ActiveOrderDetails> activeOrders;

	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	public static class ActiveOrderDetails {

		private Long id;

		private String title;

		private String type;

		private Date startDate;

		private Date endDate;

		private Integer durationNumber;

		private String durationType;

		private String status;

		private Long petId;

		private Long ownerId;

		private Long acceptedUserId;

		private String allowedPets;

		private Date createdTimestamp;

		private Date updatedTimestamp;

		private PetInformation petDetails;

		private UserDetails userDetails;
	}

}
