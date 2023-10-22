package com.solitera.test_estimation.estimationmgmt.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class RequirementDetailsDto{
	
	private Float totalNumberofRequirements;
	private Float numberofFunctionalRequirements;
	private Float numberofNonFunctionalRequirements;
	private Float numberofHighComplexityBusinessRequirements;
	private Float numberofMediumComplexityBusinessRequirements;
	private Float numberofLowComplexityBusinessRequirements;
	private Float numberofHighComplexityFunctionalRequirements;
	private Float numberofMediumComplexityFunctionalRequirements;
	private Float numberofLowComplexityFunctionalRequirements;
	private Float averageNumberofTestCasesPerBusinessRequirement;
	private Float averageNumberofTestCasesPerRequirement ;
	private Float averageNumberofTestCasesPerFunctionalRequirements;
	private Float averageNumberofTestCasesPerNonFunctionalRequirements;
	private Float averageTestCaseCreationPerDay;
	private Float averageTestCaseExecutionPerDay;
	private Float numberofHighComplexityTestCases;
	private Float numberofMediumComplexityTestCases;
	private Float numberofLowComplexityTesCases;
	private Float defectRatio;
	private Float regressionRatio;
	private Float testManagement;
	private List<TestResourcesAndCost> testResourceList;
	private List<MiscellaneousCost> miscellaneousCostList;
	
	/*
	 * private String resourceType; private String location; private Float manDays;
	 * private Float rate;
	 */
	
	/*
	 * private String description; private Float quantity; private Float
	 * miscellaneousCostRate
	 */;
	
//	public Float getMiscellaneousCostRate() {
//		return miscellaneousCostRate;
//	}
//	public void setMiscellaneousCostRate(Float miscellaneousCostRate) {
//		this.miscellaneousCostRate = miscellaneousCostRate;
//	}
	 
	public Float getTotalNumberofRequirements() {
		return totalNumberofRequirements;
	}
	public void setTotalNumberofRequirements(Float totalNumberofRequirements) {
		this.totalNumberofRequirements = totalNumberofRequirements;
	}
	public Float getNumberofFunctionalRequirements() {
		return numberofFunctionalRequirements;
	}
	public void setNumberofFunctionalRequirements(Float numberofFunctionalRequirements) {
		this.numberofFunctionalRequirements = numberofFunctionalRequirements;
	}
	public Float getNumberofNonFunctionalRequirements() {
		return numberofNonFunctionalRequirements;
	}
	public void setNumberofNonFunctionalRequirements(Float numberofNonFunctionalRequirements) {
		this.numberofNonFunctionalRequirements = numberofNonFunctionalRequirements;
	}
	public Float getNumberofHighComplexityBusinessRequirements() {
		return numberofHighComplexityBusinessRequirements;
	}
	public void setNumberofHighComplexityBusinessRequirements(Float numberofHighComplexityBusinessRequirements) {
		this.numberofHighComplexityBusinessRequirements = numberofHighComplexityBusinessRequirements;
	}
	public Float getNumberofMediumComplexityBusinessRequirements() {
		return numberofMediumComplexityBusinessRequirements;
	}
	public void setNumberofMediumComplexityBusinessRequirements(Float numberofMediumComplexityBusinessRequirements) {
		this.numberofMediumComplexityBusinessRequirements = numberofMediumComplexityBusinessRequirements;
	}
	public Float getNumberofLowComplexityBusinessRequirements() {
		return numberofLowComplexityBusinessRequirements;
	}
	public void setNumberofLowComplexityBusinessRequirements(Float numberofLowComplexityBusinessRequirements) {
		this.numberofLowComplexityBusinessRequirements = numberofLowComplexityBusinessRequirements;
	}
	public Float getNumberofHighComplexityFunctionalRequirements() {
		return numberofHighComplexityFunctionalRequirements;
	}
	public void setNumberofHighComplexityFunctionalRequirements(Float numberofHighComplexityFunctionalRequirements) {
		this.numberofHighComplexityFunctionalRequirements = numberofHighComplexityFunctionalRequirements;
	}
	public Float getNumberofMediumComplexityFunctionalRequirements() {
		return numberofMediumComplexityFunctionalRequirements;
	}
	public void setNumberofMediumComplexityFunctionalRequirements(Float numberofMediumComplexityFunctionalRequirements) {
		this.numberofMediumComplexityFunctionalRequirements = numberofMediumComplexityFunctionalRequirements;
	}
	public Float getNumberofLowComplexityFunctionalRequirements() {
		return numberofLowComplexityFunctionalRequirements;
	}
	public void setNumberofLowComplexityFunctionalRequirements(Float numberofLowComplexityFunctionalRequirements) {
		this.numberofLowComplexityFunctionalRequirements = numberofLowComplexityFunctionalRequirements;
	}
	public Float getAverageNumberofTestCasesPerBusinessRequirement() {
		return averageNumberofTestCasesPerBusinessRequirement;
	}
	public void setAverageNumberofTestCasesPerBusinessRequirement(Float averageNumberofTestCasesPerBusinessRequirement) {
		this.averageNumberofTestCasesPerBusinessRequirement = averageNumberofTestCasesPerBusinessRequirement;
	}
	public Float getAverageNumberofTestCasesPerRequirement() {
		return averageNumberofTestCasesPerRequirement;
	}
	public void setAverageNumberofTestCasesPerRequirement(Float averageNumberofTestCasesPerRequirement) {
		this.averageNumberofTestCasesPerRequirement = averageNumberofTestCasesPerRequirement;
	}
	public Float getAverageNumberofTestCasesPerFunctionalRequirements() {
		return averageNumberofTestCasesPerFunctionalRequirements;
	}
	public void setAverageNumberofTestCasesPerFunctionalRequirements(
			Float averageNumberofTestCasesPerFunctionalRequirements) {
		this.averageNumberofTestCasesPerFunctionalRequirements = averageNumberofTestCasesPerFunctionalRequirements;
	}
	public Float getAverageNumberofTestCasesPerNonFunctionalRequirements() {
		return averageNumberofTestCasesPerNonFunctionalRequirements;
	}
	public void setAverageNumberofTestCasesPerNonFunctionalRequirements(
			Float averageNumberofTestCasesPerNonFunctionalRequirements) {
		this.averageNumberofTestCasesPerNonFunctionalRequirements = averageNumberofTestCasesPerNonFunctionalRequirements;
	}
	public Float getAverageTestCaseCreationPerDay() {
		return averageTestCaseCreationPerDay;
	}
	public void setAverageTestCaseCreationPerDay(Float averageTestCaseCreationPerDay) {
		this.averageTestCaseCreationPerDay = averageTestCaseCreationPerDay;
	}
	public Float getAverageTestCaseExecutionPerDay() {
		return averageTestCaseExecutionPerDay;
	}
	public void setAverageTestCaseExecutionPerDay(Float averageTestCaseExecutionPerDay) {
		this.averageTestCaseExecutionPerDay = averageTestCaseExecutionPerDay;
	}
	public Float getNumberofHighComplexityTestCases() {
		return numberofHighComplexityTestCases;
	}
	public void setNumberofHighComplexityTestCases(Float numberofHighComplexityTestCases) {
		this.numberofHighComplexityTestCases = numberofHighComplexityTestCases;
	}
	public Float getNumberofMediumComplexityTestCases() {
		return numberofMediumComplexityTestCases;
	}
	public void setNumberofMediumComplexityTestCases(Float numberofMediumComplexityTestCases) {
		this.numberofMediumComplexityTestCases = numberofMediumComplexityTestCases;
	}
	public Float getNumberofLowComplexityTesCases() {
		return numberofLowComplexityTesCases;
	}
	public void setNumberofLowComplexityTesCases(Float numberofLowComplexityTesCases) {
		this.numberofLowComplexityTesCases = numberofLowComplexityTesCases;
	}
	public Float getDefectRatio() {
		return defectRatio;
	}
	public void setDefectRatio(Float defectRatio) {
		this.defectRatio = defectRatio;
	}
	public Float getRegressionRatio() {
		return regressionRatio;
	}
	public void setRegressionRatio(Float regressionRatio) {
		this.regressionRatio = regressionRatio;
	}
	public Float getTestManagement() {
		return testManagement;
	}
	public void setTestManagement(Float testManagement) {
		this.testManagement = testManagement;
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
}
