package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.usermgmt.dto.request.ResetPasswordDto;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class ResetPasswordDtoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ResetPasswordDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(null == target) {
			errors.reject(null, "invalid ResetPassword Details");
		}
		
		ResetPasswordDto user = (ResetPasswordDto) target;
		
//		if(!UtilsFunc.isValidEmail(user.getEmail())){
//			errors.rejectValue("email", null, "Email should not be empty,and must be of type abc123@domain.com");
//		}
//		if(!UtilsFunc.isNotNull(user.getToken())){
//			errors.rejectValue("token", null, "Token should not be empty");
//		}
		
		if(!UtilsFunc.isValidPassword(user.getNewPassword())) {
			errors.rejectValue("newPassword", null, "Password should not be empty,and has atlest eight character such as Piy@1230");
		}
		if(!UtilsFunc.isValidPassword(user.getConfirmNewPassword())){
			errors.rejectValue("confirmNewPassword", null, "Password should not be empty,and has atlest eight character such as Piy@1230");
		}
	}
	
}
