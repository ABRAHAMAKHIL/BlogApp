package com.swipeup.blog.payload;

import lombok.Data;

@Data
public class JWTAuthRequest {
	
	
	private String username;
	private String password;

}
