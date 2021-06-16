package com.agm.h2.springboot.demo.errorHandling.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.agm.h2.springboot.demo.errorHandling.apiError.ErrorCode;
import com.agm.h2.springboot.demo.errorHandling.dto.ServiceException;

@Component
public class CarRequestValidationUtil {

	public void verifyString(String carString) throws ServiceException{
		
		 if (StringUtils.isEmpty(carString) || carString.equals("undefined") || carString.equals("{model}") ||  carString.equals("{brand}") ) {
			 throw new ServiceException(ErrorCode.SERVICE_001, new Object[] {carString});
		 }
	 }
	
	public void verifyCarId(Long id) throws ServiceException{
		 if ( !(id instanceof Long) || id ==null) {
			 throw new ServiceException(ErrorCode.SERVICE_001, new Object[] {id});
		 }
	 }
}
