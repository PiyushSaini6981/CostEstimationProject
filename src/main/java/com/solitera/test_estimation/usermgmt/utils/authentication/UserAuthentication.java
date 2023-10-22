package com.solitera.test_estimation.usermgmt.utils.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.solitera.test_estimation.usermgmt.model.UserModel;
import com.solitera.test_estimation.usermgmt.repository.UserRepository;
import com.solitera.test_estimation.usermgmt.serviceimpl.UserDetailsServiceImpl;

@Component
public class UserAuthentication {

	@Autowired
	private UserDetailsServiceImpl userdetailsimpl;
	
	@Autowired
	private UserRepository repository;
	
	public Optional<UserModel> userAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Currentusername = authentication.getName();
		UserDetails loadusername = userdetailsimpl.loadUserByUsername(Currentusername);
		Optional<UserModel> user = repository.findByEmail(loadusername.getUsername());
		return user;
	}
}
