package com.solitera.test_estimation.usermgmt.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solitera.test_estimation.usermgmt.dto.request.AuthenticationDto;
import com.solitera.test_estimation.usermgmt.dto.request.ForgotPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.ResendOtpDto;
import com.solitera.test_estimation.usermgmt.dto.request.ResetPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.UpdatePasswordDTO;
import com.solitera.test_estimation.usermgmt.dto.request.UpdateProfileDto;
import com.solitera.test_estimation.usermgmt.dto.request.UserDto;
import com.solitera.test_estimation.usermgmt.dto.request.VerifyEmailDto;
import com.solitera.test_estimation.usermgmt.dto.response.AuthenticationResponse;
import com.solitera.test_estimation.usermgmt.dto.response.MessageResponse;
import com.solitera.test_estimation.usermgmt.dto.response.UserProfileDto;
import com.solitera.test_estimation.usermgmt.dto.response.UserResponseDto;
import com.solitera.test_estimation.usermgmt.model.CountryModel;
import com.solitera.test_estimation.usermgmt.service.UserService;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;
import com.solitera.test_estimation.usermgmt.validators.globalvalidators.GlobalValidator;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private GlobalValidator handleValidation;
	
	@Autowired
	private UtilsFunc func;
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<UserResponseDto>> getAllUser(){
		List<UserResponseDto> userDto =  this.userservice.getAllUser();
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@GetMapping(path = "/country")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<CountryModel>> getCountryList(){
		List<CountryModel> response =  this.userservice.getCountryList();
		return new ResponseEntity<List<CountryModel>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> createUser(@RequestBody UserDto registration,BindingResult bres){
		handleValidation.validate(registration, bres);
		func.checkBindingResultError(bres);
		this.userservice.registerUser(registration);
		return new ResponseEntity<MessageResponse>(new MessageResponse("OTP sent to Email"), HttpStatus.OK);
	}
	
	@PostMapping("/register/verify")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> verification(@RequestParam("token") String token,@RequestBody VerifyEmailDto verifyEmail,BindingResult bres){
		handleValidation.validate(verifyEmail, bres);
		func.checkBindingResultError(bres);
		this.userservice.verifyOtp(token,verifyEmail);
		return new ResponseEntity<MessageResponse>(new MessageResponse("User registered successfully"),HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<AuthenticationResponse> authenticationUser(@RequestBody AuthenticationDto authenticationDto,BindingResult bres){
		handleValidation.validate(authenticationDto, bres);
		func.checkBindingResultError(bres);
		String jwtToken = this.userservice.loginUser(authenticationDto);
		return new ResponseEntity<AuthenticationResponse>
		(new AuthenticationResponse("User authenticated successfully",jwtToken),HttpStatus.OK);
	}

	@PostMapping("/resendOtp")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> resendOtp(@RequestBody ResendOtpDto resendOtpDto, BindingResult bres){
		handleValidation.validate(resendOtpDto, bres);
		func.checkBindingResultError(bres);
		this.userservice.resendOtp(resendOtpDto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("OTP has been resent to the registered email"), HttpStatus.OK);
	}
	
	@GetMapping("/userProfile")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<UserProfileDto> profile(){
		UserProfileDto responseDto = this.userservice.userProfile();
		return new ResponseEntity<UserProfileDto>(new UserProfileDto(responseDto.getName(),
				responseDto.getEmail(), responseDto.getCompanyName(),responseDto.getPhoneNumber()),HttpStatus.OK);
	}
	
	@PutMapping("/updateProfile")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> updateProfile(@RequestBody UpdateProfileDto updateProfileDto,BindingResult bres){
		handleValidation.validate(updateProfileDto, bres);
		func.checkBindingResultError(bres);
		this.userservice.updateProfile(updateProfileDto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Profile has been updated"),HttpStatus.OK);
	}
	
	@PostMapping("/updatePassword")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO,BindingResult bres){
		handleValidation.validate(updatePasswordDTO, bres);
		func.checkBindingResultError(bres);
		this.userservice.updatePassword(updatePasswordDTO);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Password has been updated"),HttpStatus.OK);
	}
	
	@PostMapping("/forgotPassword")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto,BindingResult bres){
		handleValidation.validate(forgotPasswordDto, bres);
		func.checkBindingResultError(bres);
		this.userservice.forgotPassword(forgotPasswordDto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Link send to user email with token"),HttpStatus.OK);
	}
	
	@PutMapping("/resetPassword")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> resetPassword(@RequestParam("token") String token, @RequestBody ResetPasswordDto  resetPasswordDto, BindingResult bres){
		handleValidation.validate(resetPasswordDto, bres);
		func.checkBindingResultError(bres);
		this.userservice.resetpassword(token,resetPasswordDto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Password has been Reseted"),HttpStatus.OK);
	}
}
