package com.agilisium.spring.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationServiceImpl {
	
	public void addAuthentication(HttpServletResponse res, String username,String emailId) {
		String JWT = "";
		if (emailId != null) {
			 JWT = Jwts.builder().setSubject(emailId).setExpiration(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES))).signWith(SignatureAlgorithm.HS512, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InJhbSIsImlhdCI6MTUxNjIzOTAyMn0.MtjFHid4iB9y7fxwCi_N7qqkMRXMZNPlFYei0s5WQeg").compact();
		} else {
			 JWT = Jwts.builder().setSubject(username).setExpiration(Date.from(Instant.now().plus(60, ChronoUnit.MINUTES))).signWith(SignatureAlgorithm.HS512, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InJhbSIsImlhdCI6MTUxNjIzOTAyMn0.MtjFHid4iB9y7fxwCi_N7qqkMRXMZNPlFYei0s5WQeg").compact();
		}
		
		Cookie cookie = new Cookie("agilisiumToken", JWT);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		res.addCookie(cookie);
	}


}
