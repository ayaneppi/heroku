package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder PasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username == null || "".equals(username)) {
			throw new UsernameNotFoundException("Username is empty");
		}
		User ac = repository.findByUsername(username);
		if(ac == null) {
			throw new UsernameNotFoundException("Username not found:" + username);
		}
		if(!ac.isEnable()) {
			throw new UsernameNotFoundException("User not found:" + username);
		}
		UserAccount user = new UserAccount(ac,getAuthorities(ac));
		
		return user;
	}
	private Collection<GrantesAuthority> getAuthorities(User user){
		if(user.isAdmin()) {
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
		}else {
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}
	}

	@Transactional
	public void registerAdmin(String username,String password) {
		User user = new User(username,passwordEncoder.encode(password),true);
		repository.save(user);
	}
	
	@Transactional
	public void registerUser(String username,String password) {
		User user = new User(username,passwordEncoder.encode(password),false);
		repository.save(user);
	}
}
