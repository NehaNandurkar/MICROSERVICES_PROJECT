package com.eazycode.loans.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public class ErrorResponseDto {

	@Schema(description = "API path invoked by client")
	private String apiPath;

	@Schema(description = "Error code representing the error happened")
	private HttpStatus errorCode;

	@Schema(description = "Error message representing the error happened")
	private String errorMessage;

	@Schema(description = "Time representing when the error happened")
	private LocalDateTime errorTime;

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

	@Override
	public String toString() {
		return "ErrorResponseDto [apiPath=" + apiPath + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ ", errorTime=" + errorTime + "]";
	}

	public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
		super();
		this.apiPath = apiPath;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}

}