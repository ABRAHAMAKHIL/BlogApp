package com.swipeup.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.swipeup.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionhandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExeptionHandler(ResourceNotFoundException ex) {
		
		
		String message = ex.getMessage();
		ApiResponse res = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ez){
		
		Map<String,String> res = new HashMap<String,String>();
		 ez.getBindingResult().getAllErrors().forEach((error)->{
			
			String FieldName = ((FieldError)error).getField();
			String Message = error.getDefaultMessage();
			
			res.put("Message", Message);
			res.put("FieldName", FieldName);
			
			
			
		});
		 
		 return new ResponseEntity<Map<String,String>>(res,HttpStatus.NOT_FOUND);
		
		
		
	}
	

}
