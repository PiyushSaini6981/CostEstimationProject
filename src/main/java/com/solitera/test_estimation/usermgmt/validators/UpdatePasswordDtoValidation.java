package com.solitera.test_estimation.usermgmt.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.solitera.test_estimation.usermgmt.dto.request.UpdatePasswordDTO;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@Component
public class UpdatePasswordDtoValidation implements Validator {
	


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UpdatePasswordDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		if(null == target) {
			errors.reject(null, "invalid UpdatePassword Details");
		}
		
		UpdatePasswordDTO user = (UpdatePasswordDTO) target;
		
		if(!UtilsFunc.isValidPassword(user.getOldPassword())) {
			errors.rejectValue("oldPassword", null, "Password should not be empty,and has atlest eight character such as Piy@1230");
		}
		if(!UtilsFunc.isValidPassword(user.getNewPassword())) {
			errors.rejectValue("newPassword", null, "Password should not be empty,and has atlest eight character such as Piy@1230");
		}
	}

}
