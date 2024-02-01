package com.blogApp.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogApp.demo.Payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> ExceptionHandler(Exception ex) {

		ApiResponse<Object> response = new ApiResponse<>();
		response.setMsg(ex.getMessage());
		response.setSuccess(false);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> ResourceNotFoudExceptionHandler(ResourceNotFoundException ex) {

		ApiResponse<Object> response = new ApiResponse<>();
		response.setMsg(ex.getMessage());
		response.setSuccess(false);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> ResourceNotFoudExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});

		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);

	}

}
