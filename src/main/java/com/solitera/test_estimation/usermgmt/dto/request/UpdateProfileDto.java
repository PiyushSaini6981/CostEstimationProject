package com.solitera.test_estimation.usermgmt.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProfileDto {

	private String phoneNumber;
	private String companyName;

	
}
