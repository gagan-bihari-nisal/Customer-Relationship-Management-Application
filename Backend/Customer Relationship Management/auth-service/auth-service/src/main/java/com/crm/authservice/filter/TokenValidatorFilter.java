package com.crm.authservice.filter;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

public class TokenValidatorFilter extends OncePerRequestFilter {
	
	
	private String SECRET_KEY="Y3VzdG9tZXIgcmVsYXRpb25zaGlwIG1hbmFnZW1lbnQ=";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			final String authorizationHeader = request.getHeader("Authorization");

			String username = null;
			String jwt = null;
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7);
				username = extractUsername(jwt);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

				Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build()
						.parseClaimsJws(jwt).getBody();

				username = String.valueOf(claims.get("username"));
				String authorities = (String) claims.get("authorities");
				Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
						AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
				SecurityContextHolder.getContext().setAuthentication(auth);

			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException |SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {

			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Session Expired");
			response.setContentType("application/json");
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write(convertObjectToJson(errorResponse));
		}
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	public String extractUsername(String token) {
		//SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Claims claims = null;

		claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).build()
				.parseClaimsJws(token).getBody();

		String username = String.valueOf(claims.get("username"));
		return username;
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
	}
}