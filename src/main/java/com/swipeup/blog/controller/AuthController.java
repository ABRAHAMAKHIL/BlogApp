package com.swipeup.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swipeup.blog.payload.JWTAuthRequest;
import com.swipeup.blog.security.JWTAuthResponse;
import com.swipeup.blog.security.JWTTokenHelp;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JWTTokenHelp help;

	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private AuthenticationManager manager;


	@PostMapping("/login") 
	ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request) {
		
		this.doAuthenticate(request.getUsername(),request.getPassword());
		
		UserDetails user = 	this.detailsService.loadUserByUsername(request.getUsername());
		String token =  this.help.generateToken(user);
		
		JWTAuthResponse res = new JWTAuthResponse();
		
		res.setToken(token);
		
		return new  ResponseEntity<JWTAuthResponse>(res,HttpStatus.OK);

	}

	private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

}
