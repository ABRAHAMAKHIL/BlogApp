package com.swipeup.blog.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class commentDto {

	@NotBlank(message = "commnet_id  must not be Blank")
	private Integer id;

	@NotBlank(message = "content  must not be Blank")
	private String content;
	
}
