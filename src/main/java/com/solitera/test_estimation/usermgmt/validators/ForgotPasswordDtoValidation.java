package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.usermgmt.dto.request.ForgotPasswordDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class ForgotPasswordDtoValidation  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ForgotPasswordDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(null == target) {
			errors.reject(null, "invalid ForgotPassword Details");
		}
		ForgotPasswordDto user = (ForgotPasswordDto)target;
		if(!UtilsFunc.isValidEmail(user.getEmail())){
			errors.rejectValue("email", null, "Email should not be empty,and must be of type abc123@domain.com");
		}
	}
}
