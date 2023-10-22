package com.solitera.test_estimation.estimationmgmt.utils.estimationcalculation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.solitera.test_estimation.estimationmgmt.dto.request.MiscellaneousCost;
import com.solitera.test_estimation.estimationmgmt.dto.request.RequirementDetailsDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.TestResourcesAndCost;
import com.solitera.test_estimation.estimationmgmt.dto.response.CalculatedCost;

@Component
public class EstimationCalculation {

	public CalculatedCost ideaPhase(RequirementDetailsDto requirementDetailsDto) {
		CalculatedCost ideaPhase = new CalculatedCost();
	    List<TestResourcesAndCost> resourceList = addTestResources(requirementDetailsDto);
	    List<MiscellaneousCost> miscellaneousResouceList = addMiscellaneousResurces(requirementDetailsDto);
		ideaPhase.setTotalNumberOfTestCase(requirementDetailsDto.getTotalNumberofRequirements()*requirementDetailsDto.getAverageNumberofTestCasesPerBusinessRequirement());
		ideaPhase.setHighComplexity(requirementDetailsDto.getNumberofHighComplexityBusinessRequirements()*requirementDetailsDto.getNumberofHighComplexityTestCases());
		ideaPhase.setMediumComplexity(requirementDetailsDto.getNumberofMediumComplexityBusinessRequirements()*requirementDetailsDto.getNumberofMediumComplexityTestCases());
		ideaPhase.setLowComplexity(requirementDetailsDto.getNumberofLowComplexityBusinessRequirements()*requirementDetailsDto.getNumberofLowComplexityTesCases());
		ideaPhase.setTotal(ideaPhase.getHighComplexity()+ideaPhase.getMediumComplexity()+ideaPhase.getLowComplexity());
		ideaPhase.setEstimatedTotalTestCase((ideaPhase.getTotalNumberOfTestCase()+ideaPhase.getTotal())/2);
		if(requirementDetailsDto.getAverageTestCaseCreationPerDay() == 0) {
			ideaPhase.setTestCaseCreation((float)0.0);
		}else {
			ideaPhase.setTestCaseCreation(ideaPhase.getEstimatedTotalTestCase()/requirementDetailsDto.getAverageTestCaseCreationPerDay());
		}
		if(requirementDetailsDto.getAverageTestCaseExecutionPerDay() == 0) {
			ideaPhase.setTestCaseExecution((float)0.0);
		}else {
			ideaPhase.setTestCaseExecution(ideaPhase.getEstimatedTotalTestCase()/requirementDetailsDto.getAverageTestCaseExecutionPerDay());
		}
		ideaPhase.setTotalTestEfforts(ideaPhase.getTestCaseCreation()+ideaPhase.getTestCaseExecution());
		ideaPhase.setTestManagement((ideaPhase.getTotalTestEfforts()*requirementDetailsDto.getTestManagement())/100);
		ideaPhase.setGrandTotalManDays(ideaPhase.getTotalTestEfforts() + ideaPhase.getTestManagement());
		ideaPhase.setTestResource(resourceList);
		float totalCost = 0.0f;
		for (TestResourcesAndCost cost : resourceList) {
		    Double totalResourceCost = cost.getTotalResourceCost();
		    if (totalResourceCost != null) {
		        totalCost += totalResourceCost.floatValue();
		    }
		}
		ideaPhase.setTotalTestResourceCost(totalCost);
		ideaPhase.setMiscellaneousCostList(miscellaneousResouceList);
		float miscellaneousCost = 0.0f;
		for(MiscellaneousCost miscellaneous:miscellaneousResouceList) {
			Float miscellaneousTotal = miscellaneous.getMiscellaneousTotal();
			if(miscellaneousTotal!= null) {
				miscellaneousCost += miscellaneousTotal.floatValue();
			}
		}
		ideaPhase.setMiscellaneousCostTotal(miscellaneousCost);
		ideaPhase.setProjectTestEstimate(ideaPhase.getTotalTestResourceCost()+ideaPhase.getMiscellaneousCostTotal());
		return ideaPhase;
	}	
	
	public CalculatedCost developmentPhase(RequirementDetailsDto requirementDetailsDto) {
		CalculatedCost developmentPhase = new CalculatedCost();
	    List<TestResourcesAndCost> resourceList = addTestResources(requirementDetailsDto);
	    List<MiscellaneousCost> miscellaneousResouceList = addMiscellaneousResurces(requirementDetailsDto);
		developmentPhase.setTotalNumberOfTestCase(requirementDetailsDto.getTotalNumberofRequirements() * requirementDetailsDto.getAverageNumberofTestCasesPerRequirement());
		developmentPhase.setTotalNumberofFunctionalTestCases(requirementDetailsDto.getNumberofFunctionalRequirements() * requirementDetailsDto.getAverageNumberofTestCasesPerFunctionalRequirements());
		developmentPhase.setTotalNumberofNonFunctionalTestCases(requirementDetailsDto.getNumberofNonFunctionalRequirements() * requirementDetailsDto.getAverageNumberofTestCasesPerNonFunctionalRequirements());
		developmentPhase.setTotalofFunctionalAndNonFunctionalData(developmentPhase.getTotalNumberofFunctionalTestCases() + developmentPhase.getTotalNumberofNonFunctionalTestCases());
		developmentPhase.setHighComplexity(requirementDetailsDto.getNumberofHighComplexityFunctionalRequirements() * requirementDetailsDto.getNumberofHighComplexityTestCases());
		developmentPhase.setMediumComplexity(requirementDetailsDto.getNumberofMediumComplexityFunctionalRequirements() * requirementDetailsDto.getNumberofMediumComplexityTestCases());
		developmentPhase.setLowComplexity(requirementDetailsDto.getNumberofLowComplexityFunctionalRequirements() * requirementDetailsDto.getNumberofLowComplexityTesCases());
		developmentPhase.setTotal(developmentPhase.getHighComplexity()+developmentPhase.getMediumComplexity()+developmentPhase.getLowComplexity());
		developmentPhase.setEstimatedTotalTestCase((developmentPhase.getTotalNumberOfTestCase()+developmentPhase.getTotalofFunctionalAndNonFunctionalData()+developmentPhase.getTotal())/3);
		if(requirementDetailsDto.getAverageTestCaseCreationPerDay() == 0) {
			developmentPhase.setTestCaseCreation((float)0.0);
		}else {
			developmentPhase.setTestCaseCreation(developmentPhase.getEstimatedTotalTestCase()/requirementDetailsDto.getAverageTestCaseCreationPerDay());
		}
		if(requirementDetailsDto.getAverageTestCaseExecutionPerDay() == 0) {
			developmentPhase.setTestCaseExecution((float)0.0);
		}else {			
			developmentPhase.setTestCaseExecution(developmentPhase.getEstimatedTotalTestCase()/requirementDetailsDto.getAverageTestCaseExecutionPerDay());
		}
		developmentPhase.setDefectReset((developmentPhase.getTestCaseExecution() * requirementDetailsDto.getDefectRatio())/100);
		developmentPhase.setRegression((developmentPhase.getTestCaseExecution() * requirementDetailsDto.getRegressionRatio())/100);
		developmentPhase.setTotalTestEfforts(developmentPhase.getTestCaseCreation()+developmentPhase.getTestCaseExecution()+developmentPhase.getDefectReset()+developmentPhase.getRegression());
		developmentPhase.setTestManagement((developmentPhase.getTotalTestEfforts()*requirementDetailsDto.getTestManagement())/100);
		developmentPhase.setGrandTotalManDays(developmentPhase.getTotalTestEfforts()+developmentPhase.getTestManagement());
		developmentPhase.setTestResource(resourceList);
		float totalCost = 0.0f;
		for (TestResourcesAndCost cost : resourceList) {
		    Double totalResourceCost = cost.getTotalResourceCost();
		    if (totalResourceCost != null) {
		        totalCost += totalResourceCost.floatValue();
		    }
		}
		developmentPhase.setTotalTestResourceCost(totalCost);
		developmentPhase.setMiscellaneousCostList(miscellaneousResouceList);
		float miscellaneousCost = 0.0f;
		for(MiscellaneousCost miscellaneous:miscellaneousResouceList) {
			Float miscellaneousTotal = miscellaneous.getMiscellaneousTotal();
			if(miscellaneousTotal!= null) {
				miscellaneousCost += miscellaneousTotal.floatValue();
			}
		}
		developmentPhase.setMiscellaneousCostTotal(miscellaneousCost);
		developmentPhase.setProjectTestEstimate(developmentPhase.getTotalTestResourceCost()+developmentPhase.getMiscellaneousCostTotal());
		return developmentPhase;
	}
	
	public CalculatedCost inceptionPhase(RequirementDetailsDto requirementDetailsDto) {
		CalculatedCost inceptionPhase = new CalculatedCost();
	    List<TestResourcesAndCost> resourceList = addTestResources(requirementDetailsDto);
	    List<MiscellaneousCost> miscellaneousResouceList = addMiscellaneousResurces(requirementDetailsDto);
		inceptionPhase.setTotalNumberOfTestCase(requirementDetailsDto.getTotalNumberofRequirements() * requirementDetailsDto.getAverageNumberofTestCasesPerRequirement());
		inceptionPhase.setTotalNumberofFunctionalTestCases(requirementDetailsDto.getNumberofFunctionalRequirements() * requirementDetailsDto.getAverageNumberofTestCasesPerFunctionalRequirements());
		inceptionPhase.setTotalNumberofNonFunctionalTestCases(requirementDetailsDto.getNumberofNonFunctionalRequirements() * requirementDetailsDto.getAverageNumberofTestCasesPerNonFunctionalRequirements());
		inceptionPhase.setTotalofFunctionalAndNonFunctionalData(inceptionPhase.getTotalNumberofFunctionalTestCases() + inceptionPhase.getTotalNumberofNonFunctionalTestCases());
		inceptionPhase.setHighComplexity(requirementDetailsDto.getNumberofHighComplexityFunctionalRequirements() * requirementDetailsDto.getNumberofHighComplexityTestCases());
		inceptionPhase.setMediumComplexity(requirementDetailsDto.getNumberofMediumComplexityFunctionalRequirements() * requirementDetailsDto.getNumberofMediumComplexityTestCases());
		inceptionPhase.setLowComplexity(requirementDetailsDto.getNumberofLowComplexityFunctionalRequirements() * requirementDetailsDto.getNumberofLowComplexityTesCases());
		inceptionPhase.setTotal(inceptionPhase.getHighComplexity()+inceptionPhase.getMediumComplexity()+inceptionPhase.getLowComplexity());
		inceptionPhase.setEstimatedTotalTestCase((inceptionPhase.getTotalNumberOfTestCase()+inceptionPhase.getTotalofFunctionalAndNonFunctionalData()+inceptionPhase.getTotal())/3);
		if(requirementDetailsDto.getAverageTestCaseCreationPerDay() == 0) {
			inceptionPhase.setTestCaseCreation((float)0.0);
		}else {
			inceptionPhase.setTestCaseCreation(inceptionPhase.getEstimatedTotalTestCase()/requirementDetailsDto.getAverageTestCaseCreationPerDay());
		}
		if(requirementDetailsDto.getAverageTestCaseExecutionPerDay() == 0) {
			inceptionPhase.setTestCaseExecution((float)0.0);
		}else {
			inceptionPhase.setTestCaseExecution(inceptionPhase.getEstimatedTotalTestCase()/requirementDetailsDto.getAverageTestCaseExecutionPerDay());
		}
		inceptionPhase.setDefectReset((inceptionPhase.getTestCaseExecution() * requirementDetailsDto.getDefectRatio())/100);
		inceptionPhase.setRegression((inceptionPhase.getTestCaseExecution() * requirementDetailsDto.getRegressionRatio())/100);
		inceptionPhase.setTotalTestEfforts(inceptionPhase.getTestCaseCreation()+inceptionPhase.getTestCaseExecution()+inceptionPhase.getDefectReset()+inceptionPhase.getRegression());
		inceptionPhase.setTestManagement((inceptionPhase.getTotalTestEfforts()*requirementDetailsDto.getTestManagement())/100);
		inceptionPhase.setGrandTotalManDays(inceptionPhase.getTotalTestEfforts()+inceptionPhase.getTestManagement());
		inceptionPhase.setTestResource(resourceList);
		float totalCost = 0.0f;
		for (TestResourcesAndCost cost : resourceList) {
		    Double totalResourceCost = cost.getTotalResourceCost();
		    if (totalResourceCost != null) {
		        totalCost += totalResourceCost.floatValue();
		    }
		}
		inceptionPhase.setTotalTestResourceCost(totalCost);
		inceptionPhase.setMiscellaneousCostList(miscellaneousResouceList);
		float miscellaneousCost = 0.0f;
		for(MiscellaneousCost miscellaneous:miscellaneousResouceList) {
			Float miscellaneousTotal = miscellaneous.getMiscellaneousTotal();
			if(miscellaneousTotal!= null) {
				miscellaneousCost += miscellaneousTotal.floatValue();
			}
		}
		inceptionPhase.setMiscellaneousCostTotal(miscellaneousCost);
		inceptionPhase.setProjectTestEstimate(inceptionPhase.getTotalTestResourceCost()+inceptionPhase.getMiscellaneousCostTotal());
		return inceptionPhase;
	}
	
	public List<TestResourcesAndCost> addTestResources(RequirementDetailsDto requirementDetailsDto) {
	    List<TestResourcesAndCost> resourceList = new ArrayList<>();
	      for(TestResourcesAndCost resource : requirementDetailsDto.getTestResourceList()) {
	        resource.setResourceType(resource.getResourceType());
	        resource.setLocation(resource.getLocation());
	        resource.setManDays(resource.getManDays());
	        resource.setRate(resource.getRate());
	        resource.setTotalResourceCost(resource.getManDays() * resource.getRate());
	    	resourceList.add(resource);
	      }
	    return resourceList;
	}
	
	public List<MiscellaneousCost> addMiscellaneousResurces(RequirementDetailsDto requirementDetailsDto){
		List<MiscellaneousCost> resourceList = new ArrayList<>();
		for(MiscellaneousCost resource: requirementDetailsDto.getMiscellaneousCostList()) {
			resource.setDescription(resource.getDescription());
			resource.setQuantity(resource.getQuantity());
			resource.setMiscellaneousCostRate(resource.getMiscellaneousCostRate());
			resource.setMiscellaneousTotal(resource.getQuantity()*resource.getMiscellaneousCostRate());
			resourceList.add(resource);
		}
		return resourceList;
	}
}
