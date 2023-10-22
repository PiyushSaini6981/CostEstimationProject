package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.usermgmt.dto.request.VerifyEmailDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class VerifyEmailDtoValidations implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return VerifyEmailDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(null == target) {
			errors.reject(null,"Invalid VerifyEmailDto Details");
		}
		VerifyEmailDto user = (VerifyEmailDto) target;

		if(!UtilsFunc.isNotNull(user.getOtp())) {
			errors.rejectValue("otp", null, "Otp should not be null");
		}
	}

}
