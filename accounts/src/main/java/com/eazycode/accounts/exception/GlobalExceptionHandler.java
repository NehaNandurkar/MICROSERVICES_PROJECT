package com.eazycode.accounts.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eazycode.accounts.dto.ErrorResponseDto;

@RestControllerAdvice
//a global error handler for REST APIs that catches exceptions 
//from all controllers and gives clean JSON responses instead of ugly errors.

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	//To tell the spring what to do if validations fail 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		 Map<String, String> validationErrors = new HashMap<>();
	        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

	        validationErrorList.forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String validationMsg = error.getDefaultMessage();
	            validationErrors.put(fieldName, validationMsg);
	        });
	        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(
			Exception exception,WebRequest webRequest){
		ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
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
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
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
