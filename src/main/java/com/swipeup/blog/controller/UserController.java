package com.swipeup.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swipeup.blog.payload.ApiResponse;
import com.swipeup.blog.payload.UserDto;
import com.swipeup.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		
		UserDto usrDto  = userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(usrDto,HttpStatus.CREATED);
		
		
	}
	
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		
		
		UserDto usrDto = this.userService.updateUser(userDto, userId);
		
		return new  ResponseEntity<UserDto>(usrDto,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getuser/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		
		UserDto usr = this.userService.getUserById(userId);
		
		return new ResponseEntity<UserDto>(usr,HttpStatus.FOUND);
		
		
	}
	
	public ResponseEntity<List<UserDto>> getAllUser(){
		
		return ResponseEntity.ok(this.userService.getAllUsers()) ;
		
		
	}
	
	@RequestMapping("delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId){
		
		this.userService.deleteUserById(userId);
		return new ResponseEntity(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
		
	}
	
	

}
