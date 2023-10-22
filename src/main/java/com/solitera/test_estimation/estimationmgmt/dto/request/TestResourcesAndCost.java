package com.solitera.test_estimation.estimationmgmt.dto.request;


import lombok.Data;

@Data
public class TestResourcesAndCost {

	private String resourceType;
    private String location;
    private double manDays;
    private double rate;
    private double totalResourceCost;
}
