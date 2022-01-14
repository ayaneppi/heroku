package com.example.demo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccount implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User us;
	private Collection<GrantedAuthority> authorities;
	
	protected UserAccount() {}
	public UserAccount(User user,Collection<GrantedAuthority> authorities) {
		this.us = user;
		this.authorities = authorities;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return us.getPassWord();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return us.getName();
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
		return us.isEnabled();
	}


}
