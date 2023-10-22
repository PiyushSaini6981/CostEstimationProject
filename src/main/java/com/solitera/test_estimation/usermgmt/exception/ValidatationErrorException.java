package com.solitera.test_estimation.usermgmt.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ValidatationErrorException extends RuntimeException  {
	
	private static final long serialVersionUID = -7435160798746864437L;
	

	private static String message = "Validation Error in following fields";
	
	private Map<String,String> fields;
	
	public ValidatationErrorException() {
		super(message);
	}
	
	public ValidatationErrorException(Map<String, String> fields) {
		super(message);
		this.setFields(fields);
	}
	
	public ValidatationErrorException(String message) {
		super(message);
	}
	
	public ValidatationErrorException(Throwable cause) {
		super(cause);
	}
	
	public ValidatationErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public Map<String,String> getFields() {
		return fields;
	}

	public void setFields(Map<String,String> fields) {
		this.fields = fields;
	}
}
