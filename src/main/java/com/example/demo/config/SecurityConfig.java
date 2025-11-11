package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error")
			.permitAll()
		).logout(logout -> logout
			.logoutUrl("/")
		).authorizeHttpRequests(authz -> authz
			.requestMatchers("/css/**").permitAll()
			.requestMatchers("/js/**").permitAll()
			.requestMatchers("/img/**").permitAll()
			.requestMatchers("/login").permitAll()
			.requestMatchers("/register").permitAll()
			.anyRequest().authenticated()
		);
		return http.build();
	}
	
	//パスワードのアルゴリズムをBCryptに設定
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
