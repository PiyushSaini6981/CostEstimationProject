package com.solitera.test_estimation.estimationmgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.estimationmgmt.dto.request.EstimationDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;
@Component
public class EstimationDtoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EstimationDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(null == target) {
			errors.reject(null, "Invalid Estimation Details");
		}
		EstimationDto estimation = (EstimationDto) target;
		if(!UtilsFunc.isNotNull(estimation.getEstimationName())) {
			errors.rejectValue("estimationName", null, "EstimationName should not empty");
		}
//		if(!UtilsFunc.isNotNull(estimation.getIdeaPhase())) {
//			errors.rejectValue("ideaPhase", null, "IdeaPhase should not empty");
//		}
//		if(!UtilsFunc.isNotNull(estimation.getDevelopementPhase())) {
//			errors.rejectValue("developementPhase", null, "DevelopementPhase should not empty");
//		}
//		if(!UtilsFunc.isNotNull(estimation.getInceptionPhase())) {
//			errors.rejectValue("inceptionPhase", null, "InceptionPhase should not empty");
//		}
	}

}
