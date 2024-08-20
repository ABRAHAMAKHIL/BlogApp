package com.swipeup.blog.payload;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
	
	
	private Integer id;
	
	
	@NotBlank(message="Name must not be Blank")
	
	private String name;
	@NotBlank(message="Email must not be Blank")
	@Email(message="Enter valid Email-id")
	private String email;
	@NotBlank(message="Password must not be Blank")
	private String password;
	@NotBlank(message="about must not be Blank")
	private String about;
	
	Set<RoleDto> userRole = new HashSet<>();
	

}
