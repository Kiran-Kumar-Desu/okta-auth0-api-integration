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

import com.example.okta_api_integration.models.LoginRequest;


@RestController
@RequestMapping("/auth")
public class LoginController { 
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
		String auth0tokenAPI = "https://dev-hp0f1begf5eypopg.us.auth0.com";
//		/oauth/token
		
//		OkHttpClient client = new OkHttpClient();

        String json;
		
		Map<String, String> payload = new HashMap<>();
	    payload.put("grant_type", "password");
	    payload.put("username", loginRequest.getUserName());
	    payload.put("password", loginRequest.getPassword());
	    payload.put("client_id", "DVEcIs1CmgqiWgl8fmvxk1qK6STKqSh9");
	    payload.put("client_secret", "N37UXqbuZUf3Eihj-SaNk6JTLL2mtqnaTyycw2XD6yfyvzh5PqCya_dtjv7ct2N6");
	    payload.put("scope", "openid profile email offline_access");
	    
	    
//        try {
//            json = mapper.writeValueAsString(payload);
//            RequestBody body = RequestBody.create(json, MediaType.get("application/json"));
//            Request request = new Request.Builder()
//                    .url("https://YOUR_DOMAIN.auth0.com/oauth/token")
//                    .post(body)
//                    .build();
//
//            try (Response response = client.newCall(request).execute()) {
//                return ResponseEntity.status(response.code()).body(response.body().string());
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login error");
//        }
		
		System.out.println(loginRequest.getUserName());
		System.out.println(loginRequest.getPassword());
		return new ResponseEntity<String>(loginRequest.getUserName() + loginRequest.getPassword(), HttpStatusCode.valueOf(200)) ;
	}

}
