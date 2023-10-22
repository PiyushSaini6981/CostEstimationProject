package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.solitera.test_estimation.usermgmt.dto.request.AuthenticationDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class AuthenticationDtoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return AuthenticationDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(null == target) {
			errors.reject(null, "invalid Authentication Details");
		}
		
		AuthenticationDto auth = (AuthenticationDto) target;
		if(!UtilsFunc.isValidEmail(auth.getUsername())){
			errors.rejectValue("username", null, "Email should not be empty,and must be of type abc123@domain.com");
		}
		if(!UtilsFunc.isValidPassword(auth.getPassword())) {
			errors.rejectValue("password", null, "Password should not be empty,and has atlest eight character such as Piy@1230");
		}
		
	}
}
