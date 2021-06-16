package com.agm.h2.springboot.demo.errorHandling.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.agm.h2.springboot.demo.errorHandling.apiError.ServiceError;
import com.agm.h2.springboot.demo.errorHandling.dto.ServiceResponse;

@Component
public class ServiceResponseHandler {

	public ResponseEntity<Object> buildErrorResponseEntity(ServiceError serviceError, HttpStatus status) {

		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setStatus(status);
		serviceResponse.setServiceError(serviceError);
		return new ResponseEntity<>(serviceResponse, status);
	}

	public ResponseEntity<Object> buildSuccessResponseEntity(Object data, HttpStatus status) {
		ServiceResponse ServiceResponse = new ServiceResponse();
		ServiceResponse.setStatus(status);
		ServiceResponse.setData(data);
		return new ResponseEntity<>(ServiceResponse, status);
	}

}
