package com.solitera.test_estimation.estimationmgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.estimationmgmt.dto.request.RenameEstimationDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class UpdateEstimationDtoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RenameEstimationDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(null == target) {
			errors.reject(null, "Invalid UpdateEstimationDto Details");
		}
		RenameEstimationDto estimation = (RenameEstimationDto) target;
		
//		if(!UtilsFunc.isNotNull(estimation.getId())) {
//			errors.rejectValue("id", null, "EstimationId should not empty");
//		}
		if(!UtilsFunc.isNotNull(estimation.getEstimationName())) {
			errors.rejectValue("estimationName", null, "EstimationName should not empty");
		}
	}
}
