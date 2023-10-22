package com.solitera.test_estimation.usermgmt.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.solitera.test_estimation.usermgmt.dto.request.AuthenticationDto;
import com.solitera.test_estimation.usermgmt.dto.request.ForgotPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.MyuserDetails;
import com.solitera.test_estimation.usermgmt.dto.request.ResendOtpDto;
import com.solitera.test_estimation.usermgmt.dto.request.ResetPasswordDto;
import com.solitera.test_estimation.usermgmt.dto.request.UpdatePasswordDTO;
import com.solitera.test_estimation.usermgmt.dto.request.UpdateProfileDto;
import com.solitera.test_estimation.usermgmt.dto.request.UserDto;
import com.solitera.test_estimation.usermgmt.dto.request.VerifyEmailDto;
import com.solitera.test_estimation.usermgmt.dto.response.UserProfileDto;
import com.solitera.test_estimation.usermgmt.dto.response.UserResponseDto;
import com.solitera.test_estimation.usermgmt.exception.AlreadyExistException;
import com.solitera.test_estimation.usermgmt.exception.BadRequestException;
import com.solitera.test_estimation.usermgmt.exception.NotFoundException;
import com.solitera.test_estimation.usermgmt.model.CountryModel;
import com.solitera.test_estimation.usermgmt.model.UserModel;
import com.solitera.test_estimation.usermgmt.repository.CountryRepository;
import com.solitera.test_estimation.usermgmt.repository.UserRepository;
import com.solitera.test_estimation.usermgmt.security.jwt.JwtUtils;
import com.solitera.test_estimation.usermgmt.service.UserService;
import com.solitera.test_estimation.usermgmt.utils.authentication.UserAuthentication;
import com.solitera.test_estimation.usermgmt.utils.mail.Emailsender;

@Service
public class UserServiceIMPL implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceIMPL.class.getName());
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserAuthentication authentication;
	
    @Autowired
	private JwtUtils jwtutils;
    
    @Autowired
    private Emailsender mailSender;
    
    @Override
	public List<UserResponseDto> getAllUser() {
    	List<UserModel> users = repository.findAll();
    	List<UserResponseDto> userDtos = new ArrayList<>();
	    for (UserModel user : users) {
	        UserResponseDto dto = new UserResponseDto();
	        dto.setId(user.getId());
	        dto.setName(user.getName());
	        dto.setCompanyName(user.getCompanyName());
	        dto.setEmail(user.getEmail());
	        dto.setPhoneNumber(user.getPhoneNumber());
	        dto.setCountry(user.getCountry());
	        userDtos.add(dto);
	    }
	    return userDtos;
	}
    
    @Override
    public List<CountryModel> getCountryList(){
    	List<CountryModel> countryList = countryRepository.findAll();
		return countryList;
    }
    
    @Override
    public void registerUser(UserDto register) {
        Optional<UserModel> existemail = repository.findByEmail(register.getEmail());
        if (existemail.isPresent()) {
            throw new AlreadyExistException("Account with the same email already exists. Please login instead.");
        } else {
            UserModel user = new UserModel();
            user.setName(register.getName());
            user.setEmail(register.getEmail());
            user.setPassword(passwordencoder.encode(register.getPassword()));
            user.setCompanyName(register.getCompanyName());
            Optional<CountryModel> findCountry = countryRepository.findByCountryName(register.getCountry());
            if(findCountry.isPresent()) {
            	user.setCountry(register.getCountry());            	
            }else {
            	throw new BadRequestException("Invalid country name");
            }
            repository.save(user);            
            mailSender.sendOtp(register.getEmail());
        }
    }
    
    @Override
	public void resendOtp(ResendOtpDto resendOtpDto) {
		Optional<UserModel> findMail = repository.findByEmail(resendOtpDto.getEmail());
		if(findMail.isPresent()) {
			mailSender.sendOtp(resendOtpDto.getEmail());
		}else {
			throw new NotFoundException("Email not Found");
		}
	}
    
    @Override
    public boolean verifyOtp(String token,VerifyEmailDto verifyEmail) {
        Optional<UserModel> findToken = repository.findByToken(token);
        if (findToken.isPresent()) {
        	UserModel model = findToken.get();
        	if (verifyEmail.getOtp().equals(model.getOtp())) {
                long otpTimestamp = model.getTimestamp();
                long currentTime = System.currentTimeMillis();
                long timeDiffMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime - otpTimestamp);
                long tokenTimestamp = model.getTimestamp();
                long tokenTimeDiffMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime - tokenTimestamp);
                if (timeDiffMinutes <= 15 || tokenTimeDiffMinutes <= 15) {
                    model.setEmailVarified(true);
                    model.setOtp(null);
                    model.setToken(null);
                    model.setTimestamp(0);
                    repository.save(model);
                    return true;
                }else {
                    model.setOtp(null);
                    model.setToken(null);
                    model.setTimestamp(0);
                    repository.save(model);
                    throw new BadRequestException("Token And Otp Expired");
                }
            }else {
    	        throw new BadRequestException("Invalid OTP entered. Please try again.");
            }
        }else {
            throw new NotFoundException("Token not found");
        }
    }
    
	@Override
	public String loginUser(AuthenticationDto authenticationDto){
		Optional<UserModel> user = repository.findByEmail(authenticationDto.getUsername());
		String Jwt = null;
		if(user.isPresent()) {
			UserModel model = user.get();
			if(model.getEmailVarified() != null && model.getEmailVarified()) {
				try {
			        Authentication authentication = authenticationManager.authenticate(
			            new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(),authenticationDto.getPassword()));
			        SecurityContextHolder.getContext().setAuthentication(authentication);
			        Jwt = jwtutils.generateJwtToken(authentication);
			    } catch (BadCredentialsException e) {
			        throw new BadRequestException("Invalid login credentials");
			    }
			}else{
				throw new BadRequestException("Email Not Verified");
			}
		}else {
			throw new NotFoundException("Email not Found");
		}
		return Jwt;
	}

	@Override
	public UserProfileDto userProfile() {
		Optional<UserModel> user =  authentication.userAuthentication();
		UserProfileDto dto = new UserProfileDto();
		if(user.isPresent()) {
			UserModel model = user.get();
			dto.setName(model.getName());
			dto.setEmail(model.getEmail());
			dto.setCompanyName(model.getCompanyName());
			dto.setPhoneNumber(model.getPhoneNumber());
		}
		return dto;
	}
	
	@Override
	public void updateProfile(UpdateProfileDto updateProfileDto) {
		Optional<UserModel> user =  authentication.userAuthentication();
		if(user.isPresent()) {
			UserModel model = user.get();
			model.setCompanyName(updateProfileDto.getCompanyName());
			model.setPhoneNumber(updateProfileDto.getPhoneNumber());
			repository.save(model);
//			Boolean check = passwordencoder.matches(updateProfileDto.getOldPassword(), model.getPassword());
//			if(check) {
//				model.setPassword(passwordencoder.encode(updateProfileDto.getNewPassword()));
//				repository.save(model);
//			}else {
//				throw new ChangePasswordException("Password does not updated");
//			}
		}else {
			throw new NotFoundException("Enable to update User Details!");
		}
	}
	
	@Override
	public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		Optional<UserModel> user =  authentication.userAuthentication();
		if(user.isPresent()) {
			UserModel model = user.get();
				  boolean check = passwordencoder.matches(updatePasswordDTO.getOldPassword(), model.getPassword());
			if(check) {
				model.setPassword(passwordencoder.encode(updatePasswordDTO.getNewPassword()));
				repository.save(model);
			}else {
				throw new BadRequestException("Password not match !");
			}
		}else {
			throw new NotFoundException("User not found Exception");
		}
	}
	
	@Override
	public void forgotPassword(ForgotPasswordDto forgotPasswordDto){
		Optional<UserModel> user = repository.findByEmail(forgotPasswordDto.getEmail());
		if(user.isPresent()) {
			UserModel model = user.get();
			String token = UUID.randomUUID().toString();
			model.setToken(token);
			model.setTimestamp(System.currentTimeMillis());
			repository.save(model);
			mailSender.sendVerificationToken(forgotPasswordDto.getEmail(),model.getToken());
		}else {
			throw new NotFoundException("Email not found");
		}
	}
	
	@Override
	public void resetpassword(String token, ResetPasswordDto resetPasswordDto) {
	    Optional<UserModel> findToken = repository.findByToken(token);
	    if (findToken.isPresent()) {
	        UserModel model = findToken.get();
	        boolean checkagain = resetPasswordDto.getNewPassword().equals(resetPasswordDto.getConfirmNewPassword());
	        if (checkagain) {
	            long tokenTimestamp = model.getTimestamp();
	            long currentTime = System.currentTimeMillis();
	            long tokenTimeDiffMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime - tokenTimestamp);
	            if (tokenTimeDiffMinutes <= 15) {
	                model.setPassword(passwordencoder.encode(resetPasswordDto.getConfirmNewPassword()));
	                model.setToken(null);
	                model.setTimestamp(0);
	                repository.save(model);
	            } else {
	               model.setToken(null);
		           model.setTimestamp(0);
	               repository.save(model);
	               throw new BadRequestException("Token Expired");
	            }
	        } else {
	            throw new BadRequestException("Password does not match");
	        }
	    }else {
	        throw new NotFoundException("Token not found");
	    }
	}
	
}



