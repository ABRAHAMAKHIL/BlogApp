package com.swipeup.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swipeup.blog.payload.JWTAuthRequest;
import com.swipeup.blog.payload.UserDto;
import com.swipeup.blog.security.JWTAuthResponse;
import com.swipeup.blog.security.JWTTokenHelp;
import com.swipeup.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JWTTokenHelp help;

	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserService userService;


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
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> RegisterUser(@RequestBody UserDto userDto){

		
		UserDto user = this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
		
		
		
	}

}
