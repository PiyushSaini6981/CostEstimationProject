package com.solitera.test_estimation.estimationmgmt.utils.authentication;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.solitera.test_estimation.usermgmt.dto.request.MyuserDetails;
import com.solitera.test_estimation.usermgmt.exception.BadRequestException;

@Component
public class EstimationAuthentication {

	public MyuserDetails authenticate() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof MyuserDetails)) {
		    throw new BadRequestException("User not authenticated");
		}
		MyuserDetails userDetails = (MyuserDetails) authentication.getPrincipal();
		return userDetails;
	}
}