package com.swipeup.blog.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {
	
	
	private Integer id;
	
	@NotBlank(message="Title must not be Null ")
	private String title;

	private String description;

}
