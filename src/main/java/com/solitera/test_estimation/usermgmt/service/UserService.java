package com.solitera.test_estimation.usermgmt.service;

import java.util.List;

import com.solitera.test_estimation.usermgmt.dto.request.AuthenticationDto;
import com.solitera.test_estimation.usermgmt.dto.request.ForgotPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.ResendOtpDto;
import com.solitera.test_estimation.usermgmt.dto.request.ResetPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.UpdatePasswordDTO;
import com.solitera.test_estimation.usermgmt.dto.request.UpdateProfileDto;
import com.solitera.test_estimation.usermgmt.dto.request.UserDto;
import com.solitera.test_estimation.usermgmt.dto.request.VerifyEmailDto;
import com.solitera.test_estimation.usermgmt.dto.response.UserProfileDto;
import com.solitera.test_estimation.usermgmt.dto.response.UserResponseDto;
import com.solitera.test_estimation.usermgmt.model.CountryModel;

public interface UserService {

	public List<UserResponseDto> getAllUser();
	public void registerUser(UserDto register);
	public String loginUser(AuthenticationDto authenticationDto);
	public void resendOtp(ResendOtpDto resendOtpDto);
	public void updateProfile(UpdateProfileDto updateProfileDto);
	public UserProfileDto userProfile();
	public void forgotPassword(ForgotPasswordDto forgotPasswordDto);
	public void resetpassword(String token,ResetPasswordDto resetPasswordDto);
	public boolean verifyOtp(String token, VerifyEmailDto verifyEmail);
	public void updatePassword(UpdatePasswordDTO updatePasswordDTO);
	public List<CountryModel> getCountryList();
}
