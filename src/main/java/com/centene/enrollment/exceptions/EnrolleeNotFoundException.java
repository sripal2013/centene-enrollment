package com.centene.enrollment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description:  User defined exception when no record found
 * @author Srikanth Palutla
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnrolleeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EnrolleeNotFoundException(String message){
		super(message);
	}
	
	public EnrolleeNotFoundException(){
		super();
	}	
}
