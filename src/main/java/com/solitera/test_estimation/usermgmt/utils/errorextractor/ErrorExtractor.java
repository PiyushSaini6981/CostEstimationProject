package com.solitera.test_estimation.usermgmt.utils.errorextractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorExtractor {

	public static List<String> extract(BindingResult bRes) {

		List<String> errors = bRes.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

		return errors;
	}
	
	public static ErrorResponse getErrorResponse(BindingResult bRes) {
		JSONObject jsonObject = new JSONObject();
		
		for (ObjectError err : bRes.getAllErrors()) {
			try {
				jsonObject.append(err.getCode() , err.getDefaultMessage());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return new ErrorResponse(jsonObject.toString());
	}
	
	public static Map<String, String> getErrorMap(BindingResult bRes){
		List<FieldError> errorList = bRes.getFieldErrors();
		Map<String, String> errorMap = new HashMap<String, String>();
		for (FieldError fieldError : errorList) {
			errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errorMap;
	}

}