package com.solitera.test_estimation.usermgmt.security.jwt;


import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.solitera.test_estimation.usermgmt.dto.request.MyuserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtUtils {
	
	public static final int EXPIRE_DURATION = 2 * 60 * 60 * 1000; 
	
	public static final String jwtSecret="abcdefghijklmnOPQRSTUVWXYZ";
	
	public String generateJwtToken(Authentication authentication) {
		
		MyuserDetails user = (MyuserDetails) authentication.getPrincipal();
		
		return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
	    return Jwts.parser()
	    		.setSigningKey(jwtSecret)
	    		.parseClaimsJws(token)
	    		.getBody()
	    		.getSubject();
	  }
	
	 public boolean validateJwtToken(String authToken) {
		    try {
		      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
		      return true;
		    } catch (SignatureException e) {
		      log.error("Invalid JWT signature: {}", e.getMessage());
		    } catch (MalformedJwtException e) {
		      log.error("Invalid JWT token: {}", e.getMessage());
		    } catch (ExpiredJwtException e) {
		      log.error("JWT token is expired: {}", e.getMessage());
		    } catch (UnsupportedJwtException e) {
		      log.error("JWT token is unsupported: {}", e.getMessage());
		    } catch (IllegalArgumentException e) {
		      log.error("JWT claims string is empty: {}", e.getMessage());
		    }
		    return false;
		  }

}
