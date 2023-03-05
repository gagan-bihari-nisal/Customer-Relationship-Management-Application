package com.crm.authservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crm.authservice.exception.BadManagerException;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

	public GlobalExceptionControllerAdvice() {
	}
	
	@ExceptionHandler(BadManagerException.class)
	public ResponseEntity<?> handleBadProfileException(BadManagerException exp){
		Map<String,String> map=new HashMap<>();
		map.put("message",exp.getMessage());
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException exp){
		Map<String,String> map=new HashMap<>();
		map.put("message",exp.getMessage());
		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUnhandledExceptions(Exception exp){
		Map<String,String> map=new HashMap<>();
		map.put("message","Something went wrong.");
		return new ResponseEntity<>(map, 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}