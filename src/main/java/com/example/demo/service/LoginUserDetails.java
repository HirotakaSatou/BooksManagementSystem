package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Users;

public class LoginUserDetails implements UserDetails {

	private Users users;
	
	public LoginUserDetails(Users users) {
		this.users = users;
	}
	
	public Users getUsers() {
		return users;
	}
	
	public Integer getUserId() {
		return users.getId();
	}
	
	public String getLastName() {
		return users.getLastName();
	}
	
	public String getFirstName() {
		return users.getFirstName();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_" + users.getRole());
	}
	
	@Override
	public String getPassword() {
		return users.getPassword();
	}
	
	@Override
	public String getUsername() {
		return users.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
