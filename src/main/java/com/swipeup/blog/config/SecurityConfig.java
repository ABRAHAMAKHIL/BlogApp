package com.swipeup.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.swipeup.blog.security.CustomerUserDetailService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	@Bean
	SecurityFilterChain DefaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((request) -> {
			try {
				request.anyRequest().authenticated().and().httpBasic();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		});
		return http.build();
		
	}
	
	
	void DefaultSecurityFilterChain(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());
	
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
