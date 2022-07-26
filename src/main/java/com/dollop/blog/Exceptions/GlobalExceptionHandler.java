package com.dollop.blog.Exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dollop.blog.PayLoads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse =new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex)
   {
		Map<String,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->
		{
		   	String fieldName=((FieldError)error).getField();
		   	String message=error.getDefaultMessage();
		   	resp.put(fieldName, message);
		});
	  return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	  }
	
		/*
		 * @ExceptionHandler(ConstraintViolationException.class) public
		 * ResponseEntity<Map<String,String>>
		 * handleConstraintViolationException(ConstraintViolationException ex) {
		 * 
		 * return new ResponseEntity<Map<String,String>>(); }
		 */
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse> badCredentialsExceptionHandler(BadCredentialsException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse =new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
}
