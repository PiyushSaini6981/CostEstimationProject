package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.usermgmt.dto.request.ResendOtpDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class ResendOtpDtoValidations implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ResendOtpDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(null == target) {
			errors.reject(null,"Invalid ResendOtpDto Details");
		}
		
		ResendOtpDto user = (ResendOtpDto) target;
		
		if(!UtilsFunc.isValidEmail(user.getEmail())){
			errors.rejectValue("email", null, "email should not be empty,and must be of type abc123@domain.com");
		}
		
	}

}
