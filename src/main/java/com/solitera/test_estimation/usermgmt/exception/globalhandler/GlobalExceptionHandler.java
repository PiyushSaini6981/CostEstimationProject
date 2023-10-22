package com.solitera.test_estimation.usermgmt.exception.globalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.solitera.test_estimation.usermgmt.dto.response.MessageResponse;
import com.solitera.test_estimation.usermgmt.dto.response.ValidatorDTO;
import com.solitera.test_estimation.usermgmt.exception.AlreadyExistException;
import com.solitera.test_estimation.usermgmt.exception.BadRequestException;
import com.solitera.test_estimation.usermgmt.exception.InvalidRequestException;
import com.solitera.test_estimation.usermgmt.exception.NotFoundException;
import com.solitera.test_estimation.usermgmt.exception.ValidatationErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handelUserInfoNotSave(NotFoundException ex, WebRequest request) throws Exception{
		return new ResponseEntity<MessageResponse>(new MessageResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handelInvalidCredentialsException(BadRequestException ex, WebRequest request) throws Exception{
		return new ResponseEntity<MessageResponse>(new MessageResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ValidatationErrorException.class)
	public final ResponseEntity<?> handleValidationExceptions(ValidatationErrorException ex, WebRequest request) throws Exception {
		return new ResponseEntity<ValidatorDTO>(new ValidatorDTO(ex.getMessage(), ex.getFields()), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AlreadyExistException.class)
	public final ResponseEntity<?> handleAlreadyRegisterException(AlreadyExistException ex, WebRequest request) throws Exception {
		return new ResponseEntity<MessageResponse>(new MessageResponse(ex.getMessage()), HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(InvalidRequestException.class)
	public final ResponseEntity<?> handleInvalidPhoneNumber(InvalidRequestException ex, WebRequest request) throws Exception {
		return new ResponseEntity<MessageResponse>(new MessageResponse(ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
	}

}
