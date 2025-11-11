package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.LoginUserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	
	private final LoginUserRepository loginUserRepository;
	
	public LoginUserDetailsService(LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Users> usersOptional = loginUserRepository.findByEmail(email);
		return usersOptional.map(users -> new LoginUserDetails(users)).orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
}
