package com.tyss.survey.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.survey.dto.AdminResponse;
import com.tyss.survey.exception.AdminException;

@RestControllerAdvice
public class AdminControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put("Error","false");
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	@ExceptionHandler(AdminException.class)
	public AdminResponse handelException(AdminException e) {
		AdminResponse response = new AdminResponse();
		response.setError(true);
		response.setMessage(e.getMessage());
		return response;
	}
	
}
