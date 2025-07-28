package com.example.okta_api_integration.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.TokenRequest;
import com.example.okta_api_integration.models.LoginRequest;
import com.example.okta_api_integration.models.LoginResponse;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private static final String AUTH0_DOMAIN = "dev-hp0f1begf5eypopg.us.auth0.com"; // e.g., your-tenant.auth0.com
	private static final String CLIENT_ID = "DVEcIs1CmgqiWgl8fmvxk1qK6STKqSh9";
	private static final String CLIENT_SECRET = "N37UXqbuZUf3Eihj-SaNk6JTLL2mtqnaTyycw2XD6yfyvzh5PqCya_dtjv7ct2N6";
	private static final String API_IDENTIFIER = "https://api.mycorp.com/"; // Your Auth0 API's Identifier
	private static final String SCOPE = "openid profile email"; // Scopes you want to request

	private final AuthAPI authAPI = AuthAPI.newBuilder(AUTH0_DOMAIN, CLIENT_ID, CLIENT_SECRET).build();

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		TokenHolder tokenHolder = null;
		LoginResponse loginResponse = null;

		try {

			TokenRequest tokenRequest = authAPI.login(loginRequest.getUserName(),
					loginRequest.getPassword().toCharArray(), "okta-auth0-database");
			tokenRequest.setScope(SCOPE);

			tokenHolder = tokenRequest.execute().getBody();
			loginResponse = new LoginResponse(tokenHolder.getAccessToken(), tokenHolder.getIdToken(), tokenHolder.getExpiresIn(), tokenHolder.getTokenType());
			
			return ResponseEntity.ok(loginResponse);
			
		} catch (Auth0Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body("Authentication failed: " + e.getMessage());
		}

//		System.out.println("User authenticated successfully!");
//		System.out.println("Access Token: " + tokenHolder.getAccessToken());
//		System.out.println("ID Token: " + tokenHolder.getIdToken());
//		System.out.println(tokenHolder.getExpiresIn());
//		System.out.println(tokenHolder.getTokenType());
//		System.out.println(tokenHolder.getRefreshToken());
//
//		System.out.println(loginRequest.getUserName());
//		System.out.println(loginRequest.getPassword());
//		return new ResponseEntity<String>(loginRequest.getUserName() + loginRequest.getPassword(),
//				HttpStatusCode.valueOf(200));
	}

}
