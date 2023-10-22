package com.solitera.test_estimation.usermgmt.serviceimpl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.solitera.test_estimation.usermgmt.dto.request.MyuserDetails;
import com.solitera.test_estimation.usermgmt.model.UserModel;
import com.solitera.test_estimation.usermgmt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repository; 

	@Override
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
		
		Optional<UserModel> user = repository.findByEmail(email);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User does not found");
		}
		return new MyuserDetails(user.get());
	}

}

