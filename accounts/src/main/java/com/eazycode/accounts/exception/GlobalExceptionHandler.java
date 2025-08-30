package com.eazycode.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.eazycode.accounts.dto.ErrorResponseDto;

@RestControllerAdvice
//a global error handler for REST APIs that catches exceptions 
//from all controllers and gives clean JSON responses instead of ugly errors.

public class GlobalExceptionHandler {

	/**
	 * If the request was POST /api/customers/create,
	 * then webRequest.getDescription(false) → "uri=/api/customers/create"
	 * If you passed true → it would also include client details (like session ID).
	**/
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	//If this specific exception happens anywhere in my controllers, call this method to handle it
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
			CustomerAlreadyExistsException exception, WebRequest webRequest) {
		
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);//body+status
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> ResourceNotFoundException(
			ResourceNotFoundException exception,WebRequest webRequest){
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.NOT_FOUND, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
		
		
	}

}
