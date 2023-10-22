package com.solitera.test_estimation.estimationmgmt.validators.globalvalidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.estimationmgmt.dto.request.EstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.RenameEstimationDto;
import com.solitera.test_estimation.estimationmgmt.validators.EstimationDtoValidation;
import com.solitera.test_estimation.estimationmgmt.validators.UpdateEstimationDtoValidation;

@Component
public class EstimationGlobalValidator implements Validator{
	
	@Autowired
	private EstimationDtoValidation estimationDtoValidation;
	
	@Autowired
	private UpdateEstimationDtoValidation updateEstimationValidation;

	@Override
	public boolean supports(Class<?> clazz) {
		
		return EstimationDto.class.equals(clazz) || RenameEstimationDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(target instanceof EstimationDto){
			ValidationUtils.invokeValidator(estimationDtoValidation, target, errors);
		}
		if(target instanceof RenameEstimationDto){
			ValidationUtils.invokeValidator(updateEstimationValidation, target, errors);
		}
	}

}
