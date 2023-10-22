package com.solitera.test_estimation.estimationmgmt.dto.request;

import lombok.Data;

@Data
public class CalculateEstimationDto {

	private RequirementDetailsDto requirementDetailsDto;
	private String estimationType;
}
