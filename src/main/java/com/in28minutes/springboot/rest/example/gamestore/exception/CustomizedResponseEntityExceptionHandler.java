package com.in28minutes.springboot.rest.example.gamestore.exception;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GameStoreException.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(GameStoreException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getEnumeratedCause().getCode(), ex.getEnumeratedCause().getMessage(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ForbiddenException.class)
	public final ResponseEntity<ErrorDetails> handleForbiddenExceptions(ForbiddenException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getEnumeratedCause().getCode(), ex.getEnumeratedCause().getMessage(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(PaymentRequiredException.class)
	public final ResponseEntity<ErrorDetails> handleForbiddenExceptions(PaymentRequiredException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getEnumeratedCause().getCode(), ex.getEnumeratedCause().getMessage(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.PAYMENT_REQUIRED);
	}
	@ExceptionHandler(ClassNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleForbiddenExceptions(ClassNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getEnumeratedCause().getCode(), ex.getEnumeratedCause().getMessage(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorDetails> handleNullPointerExceptions(NullPointerException ex, WebRequest request,  HttpServletResponse response) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ExceptionEnum.MUST_NOT_NULL.getCode(), ex.getMessage(), ExceptionEnum.MUST_NOT_NULL.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UnauthorizedException.class)
	public final ResponseEntity<ErrorDetails> handleAuthenticationException(UnauthorizedException ex, WebRequest request,  HttpServletResponse response) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ExceptionEnum.UNAUTHORIZED.getCode(), ex.getMessage(), ExceptionEnum.UNAUTHORIZED.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), , ex.getMessage(), "Validation Error.", request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//	}
//	@ExceptionHandler(MultipartException.class)
//	public final ResponseEntity<ErrorDetails> handleDeniedExceptions(MultipartException ex, WebRequest request) {
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), "Invalid File.", request.getDescription(false));
//		;
//		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//	}
}