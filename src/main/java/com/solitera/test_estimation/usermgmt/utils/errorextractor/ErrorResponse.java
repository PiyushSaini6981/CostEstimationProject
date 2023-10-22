package com.solitera.test_estimation.usermgmt.utils.errorextractor;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String errors;
	
	public ErrorResponse(String key, String value) {
		JSONObject json = new JSONObject();
		try {
			json.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		errors = json.toString();
	}
}