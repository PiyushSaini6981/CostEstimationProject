package com.solitera.test_estimation.usermgmt.dto.request;

import java.util.Collection;


import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.solitera.test_estimation.usermgmt.model.UserModel;


public class MyuserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String password;
	

	public MyuserDetails(UserModel model) {
		this.id = model.getId();
		this.email = model.getEmail();
		this.password = model.getPassword();
	}

//	public UserDetailsImpl(UserModel model) {
//		this.model = model;
//	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
