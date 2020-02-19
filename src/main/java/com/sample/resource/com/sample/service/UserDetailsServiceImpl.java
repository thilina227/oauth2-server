package com.sample.resource.com.sample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.resource.com.sample.dao.UserRepository;
import com.sample.resource.com.sample.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(username);
		
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Wrong username or password"));
		
		User user = optionalUser.get();
		new AccountStatusUserDetailsChecker().check(user);
		return user;
	}

}
