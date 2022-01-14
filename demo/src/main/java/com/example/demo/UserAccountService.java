package com.example.demo;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.*;

@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	//@Autowired
	//private PasswordEncoder PasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username == null || "".equals(username)) {
			throw new UsernameNotFoundException("Username is empty");
		}
		
		User ac = repository.findByName(username);
		
		if(ac == null) {
			throw new UsernameNotFoundException("Username not found:" + username);
		}
		
		if(!ac.isEnabled()) {
			throw new UsernameNotFoundException("User not found:" + username);
		}
		UserAccount user = new UserAccount(ac,getAuthorities(ac));
		
		return user;
	}
	
	private Collection<GrantedAuthority> getAuthorities(User user){
		if(user.isAdmin()) {
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
		}else{
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}
	}
}