package com.swipeup.blog.payload;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostDto {
	
	private Integer id;
	@NotBlank(message="Post Title must not be null")
	private String title;
	@NotBlank(message="Post Content must not be null")
	private String content;

	private String imageName;
	private String createddate;
	private  CategoryDto category;
	private UserDto user;
	
	private Set<commentDto> comments = new HashSet<commentDto>();
	
	
	
}
