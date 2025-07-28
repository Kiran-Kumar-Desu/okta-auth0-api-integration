package com.example.okta_api_integration.controllers;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.TokenRequest;

// ...

public class UserValidationService {

    private final AuthAPI authAPI;

    // Replace with your Auth0 domain, client ID, and client secret
    private static final String AUTH0_DOMAIN = "dev-hp0f1begf5eypopg.us.auth0.com"; // e.g., your-tenant.auth0.com
    private static final String CLIENT_ID = "DVEcIs1CmgqiWgl8fmvxk1qK6STKqSh9";
    private static final String CLIENT_SECRET = "N37UXqbuZUf3Eihj-SaNk6JTLL2mtqnaTyycw2XD6yfyvzh5PqCya_dtjv7ct2N6";
    private static final String API_IDENTIFIER = "https://api.mycorp.com/"; // Your Auth0 API's Identifier
    private static final String SCOPE = "openid profile email"; // Scopes you want to request


    public UserValidationService(String domain, String clientId, String clientSecret) {
        authAPI = AuthAPI.newBuilder(domain, clientId, clientSecret).build();
    }

    public TokenHolder validateUser(String username, char[] password, String realm) throws Auth0Exception {
        // Perform basic input validation (e.g., null checks, password length)
        if (username == null || username.isEmpty() || password == null || realm == null || realm.isEmpty()) {
            throw new IllegalArgumentException("Username, password, and realm cannot be null or empty.");
        }

        try {
            TokenRequest tokenRequest = authAPI.login(username, password, realm);
//            tokenRequest.setAudience(API_IDENTIFIER);
            tokenRequest.setScope(SCOPE);

            TokenHolder tokenHolder = tokenRequest.execute().getBody();
            return tokenHolder;
        } catch (Auth0Exception e) {
            // Handle authentication errors (e.g., invalid credentials, network issues)
            System.err.println("Authentication failed for user " + username + " in realm " + realm + ": " + e.getMessage());
            throw e; // Re-throw the exception or handle it as appropriate for your application
        } finally {
            // It's good practice to clear the password from memory after use
            if (password != null) {
                for (int i = 0; i < password.length; i++) {
                    password[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example usage in your main method or another part of your application
        UserValidationService validationService = new UserValidationService(AUTH0_DOMAIN, CLIENT_ID, CLIENT_SECRET);
        String username = "kiran0496@gmail.com";
        char[] password = "Nextgen@1234".toCharArray();
        String realm = "okta-auth0-database"; // Or the name of your specific connection/realm


        try {
            TokenHolder tokenHolder = validationService.validateUser(username, password, realm);
            System.out.println("User authenticated successfully!");
            System.out.println("Access Token: " + tokenHolder.getAccessToken());
            System.out.println("ID Token: " + tokenHolder.getIdToken());
            // Further processing with tokens
        } catch (Auth0Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
        }
    }
}

