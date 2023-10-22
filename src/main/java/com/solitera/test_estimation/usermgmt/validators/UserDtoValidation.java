package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.usermgmt.dto.request.UserDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class UserDtoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(null == target) {
			errors.reject(null, "invalid User Details");
		}
		UserDto user = (UserDto) target;
		
		if(!UtilsFunc.isNotNull(user.getName())) {
			errors.rejectValue("name", null, "name must not be empty");
		}
		if(!UtilsFunc.isValidEmail(user.getEmail())){
			errors.rejectValue("email", null, "email should not be empty,and must be of type abc123@domain.com");
		}
		if(!UtilsFunc.isValidPassword(user.getPassword())) {
			errors.rejectValue("password", null, "Password should not be empty,and has atlest eight character such as Piy@1230");
		}
		if(!UtilsFunc.isNotNull(user.getCompanyName())) {
			errors.rejectValue("companyName", null, "companyName must not be empty");
		}
		if(!UtilsFunc.isNotNull(user.getCountry())) {
			errors.rejectValue("country", null, "country must not be empty");
		}
	}

}
