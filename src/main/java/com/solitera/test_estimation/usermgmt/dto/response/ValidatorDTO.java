package com.solitera.test_estimation.usermgmt.dto.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidatorDTO {
	
	@Builder.Default
	private String message = "Validator Error in fields";
	private Map<String,String> fields;
}