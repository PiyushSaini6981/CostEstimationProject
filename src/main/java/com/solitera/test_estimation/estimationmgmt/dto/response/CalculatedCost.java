package com.solitera.test_estimation.estimationmgmt.dto.response;

import java.util.List;

import com.solitera.test_estimation.estimationmgmt.dto.request.MiscellaneousCost;
import com.solitera.test_estimation.estimationmgmt.dto.request.TestResourcesAndCost;

import lombok.Data;

@Data
public class CalculatedCost {

	private Float totalNumberOfTestCase;
	private Float highComplexity;
	private Float mediumComplexity;
	private Float lowComplexity;
	private Float total;
	private Float totalNumberofFunctionalTestCases;
	private Float totalNumberofNonFunctionalTestCases;
	private Float totalofFunctionalAndNonFunctionalData;
	private Float estimatedTotalTestCase;
	private Float testCaseCreation;
	private Float testCaseExecution;
	private Float defectReset;
	private Float regression;
	private Float totalTestEfforts;
	private Float testManagement;
	private Float grandTotalManDays;
	private List<TestResourcesAndCost> testResource;
	/*
	 * private String resourceType; private String location; private Float manDays;
	 * private Float rate; private Float totalResoureCost;
	 */
	/*
	 * private String description; private Float quantity; private Float
	 * miscellaneousCostRate; private Float miscellaneousTotal;
	 */
	private List<MiscellaneousCost> miscellaneousCostList;
	private Float totalTestResourceCost;
	private Float miscellaneousCostTotal;
	private Float projectTestEstimate;
	
	public Float getTotalNumberOfTestCase() {
		return totalNumberOfTestCase;
	}
	public void setTotalNumberOfTestCase(Float totalNumberOfTestCase) {
		this.totalNumberOfTestCase = totalNumberOfTestCase;
	}
	public Float getHighComplexity() {
		return highComplexity;
	}
	public void setHighComplexity(Float highComplexity) {
		this.highComplexity = highComplexity;
	}
	public Float getMediumComplexity() {
		return mediumComplexity;
	}
	public void setMediumComplexity(Float mediumComplexity) {
		this.mediumComplexity = mediumComplexity;
	}
	public Float getLowComplexity() {
		return lowComplexity;
	}
	public void setLowComplexity(Float lowComplexity) {
		this.lowComplexity = lowComplexity;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Float getEstimatedTotalTestCase() {
		return estimatedTotalTestCase;
	}
	public void setEstimatedTotalTestCase(Float estimatedTotalTestCase) {
		this.estimatedTotalTestCase = estimatedTotalTestCase;
	}
	public Float getTestCaseCreation() {
		return testCaseCreation;
	}
	public void setTestCaseCreation(Float testCaseCreation) {
		this.testCaseCreation = testCaseCreation;
	}
	public Float getTestCaseExecution() {
		return testCaseExecution;
	}
	public void setTestCaseExecution(Float testCaseExecution) {
		this.testCaseExecution = testCaseExecution;
	}
	public Float getTotalTestEfforts() {
		return totalTestEfforts;
	}
	public void setTotalTestEfforts(Float totalTestEfforts) {
		this.totalTestEfforts = totalTestEfforts;
	}
	public Float getTestManagement() {
		return testManagement;
	}
	public void setTestManagement(Float testManagement) {
		this.testManagement = testManagement;
	}
	public Float getGrandTotalManDays() {
		return grandTotalManDays;
	}
	public void setGrandTotalManDays(Float grandTotalManDays) {
		this.grandTotalManDays = grandTotalManDays;
	}
//	public String getResourceType() {
//		return resourceType;
//	}
//	public void setResourceType(String resourceType) {
//		this.resourceType = resourceType;
//	}
//	public String getLocation() {
//		return location;
//	}
//	public void setLocation(String location) {
//		this.location = location;
//	}
//	public Float getManDays() {
//		return manDays;
//	}
//	public void setManDays(Float manDays) {
//		this.manDays = manDays;
//	}
//	public Float getRate() {
//		return rate;
//	}
//	public void setRate(Float rate) {
//		this.rate = rate;
//	}
//	public Float getTotalResoureCost() {
//		return totalResoureCost;
//	}
//	public void setTotalResoureCost(Float totalResoureCost) {
//		this.totalResoureCost = totalResoureCost;
//	}
	public Float getTotalTestResourceCost() {
		return totalTestResourceCost;
	}
	public void setTotalTestResourceCost(Float totalTestResourceCost) {
		this.totalTestResourceCost = totalTestResourceCost;
	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public Float getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(Float quantity) {
//		this.quantity = quantity;
//	}
//	public Float getMiscellaneousCostRate() {
//		return miscellaneousCostRate;
//	}
//	public void setMiscellaneousCostRate(Float miscellaneousCostRate) {
//		this.miscellaneousCostRate = miscellaneousCostRate;
//	}
//	public Float getMiscellaneousTotal() {
//		return miscellaneousTotal;
//	}
//	public void setMiscellaneousTotal(Float miscellaneousTotal) {
//		this.miscellaneousTotal = miscellaneousTotal;
//	}
	public Float getMiscellaneousCostTotal() {
		return miscellaneousCostTotal;
	}
	public void setMiscellaneousCostTotal(Float miscellaneousCostTotal) {
		this.miscellaneousCostTotal = miscellaneousCostTotal;
	}
	public Float getProjectTestEstimate() {
		return projectTestEstimate;
	}
	public void setProjectTestEstimate(Float projectTestEstimate) {
		this.projectTestEstimate = projectTestEstimate;
	}
}
