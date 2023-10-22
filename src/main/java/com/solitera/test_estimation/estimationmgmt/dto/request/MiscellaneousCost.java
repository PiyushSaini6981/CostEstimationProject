package com.solitera.test_estimation.estimationmgmt.dto.request;

import lombok.Data;

@Data
public class MiscellaneousCost {

	private String description;
	private Float quantity;
	private Float miscellaneousCostRate;
	private Float miscellaneousTotal;
}
