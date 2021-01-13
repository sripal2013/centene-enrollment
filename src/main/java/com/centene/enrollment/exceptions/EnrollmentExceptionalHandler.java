package com.centene.enrollment.exceptions;

import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * @Description:  Exceptional handler for all end points
 * @author Srikanth Palutla
 *
 */
@ControllerAdvice
public class EnrollmentExceptionalHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EnrolleeNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(EnrolleeNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> handleCityNotFoundException(ConstraintViolationException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
