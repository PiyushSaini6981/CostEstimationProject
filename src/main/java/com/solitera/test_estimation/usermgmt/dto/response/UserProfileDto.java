package com.solitera.test_estimation.usermgmt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {

	private String name;
	private String email;
	private String companyName;
	private String phoneNumber;

}
