package com.crm.authservice.filter;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenGeneratorFilter {

	@Value("${jwt.secret}")
	private String SECRET_KEY;
	@Value("${jwt.expiration}")
	private int jwtExpiration;

	

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails);
	}

	
	
	private String createToken(Map<String, Object> claims, UserDetails userDetails) {
	//	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder().setIssuer("Customer Relationship Management").setSubject("JWT Token")
				.claim("username", userDetails.getUsername())
				.claim("authorities", populateAuthorities(userDetails.getAuthorities())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration))
				.signWith(key)
				.compact();
		
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}

}
