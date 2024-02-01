package com.blogApp.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogApp.demo.entities.User;
import com.blogApp.demo.exception.ResourceNotFoundException;
import com.blogApp.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load user from DB
		User user = userRepository.findByName(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "with username: " + username, 0));
		return user;
	}

}
