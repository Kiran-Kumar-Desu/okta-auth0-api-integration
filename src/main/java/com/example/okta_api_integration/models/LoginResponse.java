package com.example.okta_api_integration.models;

public class LoginResponse {
	
	private String accessToken;
	private String idToken;
	private long expiersIn;
	private String tokenType;
	
	
	
	public LoginResponse(String accessToken, String idToken, long expiersIn, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.idToken = idToken;
		this.expiersIn = expiersIn;
		this.tokenType = tokenType;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public LoginResponse(String accessToken, String idToken, long expiersIn) {
		super();
		this.accessToken = accessToken;
		this.idToken = idToken;
		this.expiersIn = expiersIn;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getIdToken() {
		return idToken;
	}
	
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	
	public long getExpiersIn() {
		return expiersIn;
	}
	
	public void setExpiersIn(long expiersIn) {
		this.expiersIn = expiersIn;
	}
	

}
