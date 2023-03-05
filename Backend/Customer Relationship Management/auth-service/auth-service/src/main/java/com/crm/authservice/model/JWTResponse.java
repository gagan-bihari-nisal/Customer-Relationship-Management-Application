package com.crm.authservice.model;

public class JWTResponse {
	private final String token;

	public JWTResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
