package com.swipeup.blog.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String resourceId;
	private Integer fieldValue;
	
	
	public ResourceNotFoundException(String resourceName,String resourceId , Integer fieldValue) {
		
		super(String.format("%s is not found with %s: %d",resourceName,resourceId,fieldValue));
		this.resourceName = resourceName;
		this.resourceId = resourceId;
		this.fieldValue = fieldValue;
		
	}

}
