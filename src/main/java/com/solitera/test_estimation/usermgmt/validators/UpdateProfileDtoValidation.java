package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.usermgmt.dto.request.UpdateProfileDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class UpdateProfileDtoValidation  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateProfileDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(null == target) {
			errors.reject(null, "invalid UpdateProfile Details");
		}
		
		UpdateProfileDto user = (UpdateProfileDto) target;

		if(!UtilsFunc.isNotNull(user.getPhoneNumber()) || !UtilsFunc.isValidPhoneNumber(user.getPhoneNumber())) {
			errors.rejectValue("phoneNumber", null, "phoneNumber sholud not be empty");
		}
		if(!UtilsFunc.isNotNull(user.getCompanyName())) {
			errors.rejectValue("companyName", null, "companyName sholud not be empty");
		}
	}
		
}
