package com.swipeup.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.swipeup.blog.security.CustomerUserDetailService;
import com.swipeup.blog.security.JWTAuthenticationEntryPoint;
import com.swipeup.blog.security.JWTAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomerUserDetailService customerUserDetailService;

	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JWTAuthenticationFilter authenticationFilter;

	@Bean
	SecurityFilterChain DefaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeRequests()
				.requestMatchers("/api/v1/auth/login").permitAll().anyRequest().authenticated().and()
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	

	void DefaultSecurityFilterChain(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());

	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(
	            UserDetailsService userDetailsService,
	            PasswordEncoder passwordEncoder) {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService);
	        authenticationProvider.setPasswordEncoder(passwordEncoder);

	        return new ProviderManager(authenticationProvider);
	    }
	

}
