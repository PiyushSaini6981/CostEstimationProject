package com.solitera.test_estimation.usermgmt.validators.globalvalidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.solitera.test_estimation.estimationmgmt.dto.request.EstimationDto;
import com.solitera.test_estimation.estimationmgmt.validators.EstimationDtoValidation;
import com.solitera.test_estimation.usermgmt.dto.request.AuthenticationDto;
import com.solitera.test_estimation.usermgmt.dto.request.ForgotPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.ResendOtpDto;
import com.solitera.test_estimation.usermgmt.dto.request.ResetPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.UpdatePasswordDTO;
import com.solitera.test_estimation.usermgmt.dto.request.UpdateProfileDto;
import com.solitera.test_estimation.usermgmt.dto.request.UserDto;
import com.solitera.test_estimation.usermgmt.dto.request.VerifyEmailDto;
import com.solitera.test_estimation.usermgmt.validators.AuthenticationDtoValidation;
import com.solitera.test_estimation.usermgmt.validators.ForgotPasswordDtoValidation;
import com.solitera.test_estimation.usermgmt.validators.ResendOtpDtoValidations;
import com.solitera.test_estimation.usermgmt.validators.ResetPasswordDtoValidation;
import com.solitera.test_estimation.usermgmt.validators.UpdatePasswordDtoValidation;
import com.solitera.test_estimation.usermgmt.validators.UpdateProfileDtoValidation;
import com.solitera.test_estimation.usermgmt.validators.UserDtoValidation;
import com.solitera.test_estimation.usermgmt.validators.VerifyEmailDtoValidations;
@Component
public class GlobalValidator implements Validator{

	@Autowired
	private UserDtoValidation uservalidation;
	
	@Autowired
	private AuthenticationDtoValidation authenticationValidation;
	
	@Autowired
	private VerifyEmailDtoValidations verifyEmailValidations;
	
	@Autowired
	private UpdateProfileDtoValidation updateProfileValidation;
	
	@Autowired
	private ForgotPasswordDtoValidation forgotPasswordValidation;
	
	@Autowired
	private ResetPasswordDtoValidation resetPasswordValidation;
	
	@Autowired
	private EstimationDtoValidation estimationDtoValidation;
	
	@Autowired
	private ResendOtpDtoValidations resendOtpDtoValidation;
	
	@Autowired
	private UpdatePasswordDtoValidation updatePasswordDtoValidation;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz) || AuthenticationDto.class.equals(clazz)
			 || UpdateProfileDto.class.equals(clazz)
				|| VerifyEmailDto.class.equals(clazz)|| ForgotPasswordDto.class.equals(clazz) || 
				ResendOtpDto.class.equals(clazz)||ResetPasswordDto.class.equals(clazz) 
				|| UpdatePasswordDTO.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		if(target instanceof UserDto){
			ValidationUtils.invokeValidator(uservalidation, target, errors);
		}
		
		if(target instanceof AuthenticationDto){
			ValidationUtils.invokeValidator(authenticationValidation, target, errors);
		}
		
		if(target instanceof UpdateProfileDto){
			ValidationUtils.invokeValidator(updateProfileValidation, target, errors);
		}
		
		if(target instanceof ForgotPasswordDto){
			ValidationUtils.invokeValidator(forgotPasswordValidation, target, errors);
		}
		
		if(target instanceof ResetPasswordDto){
			ValidationUtils.invokeValidator(resetPasswordValidation, target, errors);
		}
		if(target instanceof EstimationDto){
			ValidationUtils.invokeValidator(estimationDtoValidation, target, errors);
		}
		
		if(target instanceof VerifyEmailDto) {
			ValidationUtils.invokeValidator(verifyEmailValidations, target, errors);
		}
		if(target instanceof ResendOtpDto) {
			ValidationUtils.invokeValidator(resendOtpDtoValidation, target, errors);
		}
		if(target instanceof UpdatePasswordDTO) {
			ValidationUtils.invokeValidator(updatePasswordDtoValidation, target, errors);
		}
	}

}
