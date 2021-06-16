package com.agm.h2.springboot.demo.errorHandling.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.agm.h2.springboot.demo.errorHandling.apiError.ServiceError;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ServiceResponse {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private Object data;
	private String message;
	private ServiceError serviceError;

	public ServiceResponse() {
		timestamp = LocalDateTime.now();
	}

	public ServiceResponse(ServiceError serviceError, HttpStatus status) {
		super();
		this.status = status;
		this.serviceError = serviceError;
	}

	public ServiceResponse(HttpStatus status, Object data, LocalDateTime timestamp) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceError getServiceError() {
		return serviceError;
	}

	public void setServiceError(ServiceError serviceError) {
		this.serviceError = serviceError;
	}


}

