package com.solitera.test_estimation.usermgmt.utils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.solitera.test_estimation.usermgmt.exception.InvalidRequestException;
import com.solitera.test_estimation.usermgmt.exception.ValidatationErrorException;
import com.solitera.test_estimation.usermgmt.utils.errorextractor.ErrorExtractor;

@Component
public class UtilsFunc {
	
	private static Integer resourceid = 234567;
	private static Integer misllaneousid = 0;
		
	public static String otpGenerator() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return Integer.toString(otp);
	}
	
	public static synchronized int getResourceId() {
		resourceid++;
		return resourceid;
	}
	
	public static synchronized int getMisllaneousId() {
		misllaneousid++;
		return misllaneousid;
	}
	public static boolean isNotNull(String string) {
		if(string != null && !string.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isNotNull(Integer integer) {
		if(integer != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isNotNull(Boolean bool) {
		if(bool != null ) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isNotNull(Object object) {
		if(object != null) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean isNotNull(List<?> list) {
		if(list != null && !list.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidPhoneNumber(String number) {
		
		if(number.matches("[0-9]+") && number.length() == 10) {
			return true;
		}else {
			throw new InvalidRequestException("Invalid phone number. Please enter a 10-digit number without any alphabets.");
		
		}
	}
	
	public static boolean isValidPassword(String password){
		 String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

		  Pattern p = Pattern.compile(regex);
		
		  if (password == null) {
		      return false;
		  }
		  Matcher m = p.matcher(password);
		  return m.matches();
	}
	
	public static boolean isValidEmail(String email){
		if(email == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();	
	}
	
	public void checkBindingResultError(BindingResult bres) {
		if(bres.hasErrors()) {
			Map<String,String> errorMap = ErrorExtractor.getErrorMap(bres);
			throw new ValidatationErrorException(errorMap);
		}
	}
}













//	public static boolean isValidPassword(String password) {

	    // Password length should be greater than 8 characters
//		if(10 <= password.length()  && password.length() <= 10) {
//			return true;
//		}
//		if (password == null) {
//		    return false;
//	   }
//		   
//		String regx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
//		
//		if(password.length() >= 8) {
//			return true;
//		}
//		
//		if(regx.matches(password)) {
//			return true;
//		}

//		return false;
//		Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
//		Matcher matcher = pattern.matcher(password);
//		return matcher.matches();
		
//	    // Password should contain at least one uppercase letter
//	    boolean hasUppercase = !password.equals(password.toLowerCase());
//	    if(!hasUppercase) {
//	        return false;
//	    }
//
//	    // Password should contain at least one lowercase letter
//	    boolean hasLowercase = !password.equals(password.toUpperCase());
//	    if(!hasLowercase) {
//	        return false;
//	    }
//
//	    // Password should contain at least one digit
//	    boolean hasDigit = false;
//	    for(char c : password.toCharArray()) {
//	        if(Character.isDigit(c)) {
//	            hasDigit = true;
//	            break;
//	        }
//	    }
//	    if(!hasDigit) {
//	        return false;
//	    }
//
//	    // Password should contain at least one special character
//	    String specialCharacter = "@#$%^&*+-=_()!";
//	    boolean hasSpecialCharacter = false;
//	    for(char c : password.toCharArray()) {
//	        if(specialCharacter.indexOf(c) != -1) {
//	            hasSpecialCharacter = true;
//	            break;
//	        }
//	    }
//	    if(!hasSpecialCharacter) {
//	        return false;
//	    }
//
//	    return false;
//	}

	 

