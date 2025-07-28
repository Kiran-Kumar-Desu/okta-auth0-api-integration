package com.example.okta_api_integration.controllers;
import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.TokenRequest;

public class Auth0RopgWithSdk {

    private static final String AUTH0_DOMAIN = "dev-hp0f1begf5eypopg.us.auth0.com"; // e.g., your-tenant.auth0.com
    private static final String CLIENT_ID = "DVEcIs1CmgqiWgl8fmvxk1qK6STKqSh9";
    private static final String CLIENT_SECRET = "N37UXqbuZUf3Eihj-SaNk6JTLL2mtqnaTyycw2XD6yfyvzh5PqCya_dtjv7ct2N6";
    private static final String API_IDENTIFIER = "{yourApiIdentifier}"; // Your Auth0 API's Identifier
    private static final String SCOPE = "openid profile email"; // Scopes you want to request

    public static void main(String[] args) {
        String username = "kiran0496@gmail.com";
        String password = "Nextgen@1234";

        // Create an instance of the AuthAPI
        AuthAPI auth = AuthAPI.newBuilder(AUTH0_DOMAIN, CLIENT_ID, CLIENT_SECRET).build();

        try {
        	
        	TokenRequest request = auth.login(username, password.toCharArray(), "okta-auth0-database");
        	request.setScope(SCOPE);
        	
        	TokenHolder holder = (TokenHolder) request.execute();
        	
        	
            // Make the ROPG request
//            TokenHolder holder = (TokenHolder) auth.login(username, password)
//                                     .setAudience(API_IDENTIFIER) // Audience of the API you want to call
//                                     .setScope(SCOPE)
//                                     .execute();

            System.out.println("Authentication successful!");
            System.out.println("Access Token: " + holder.getAccessToken());
            System.out.println("ID Token: " + holder.getIdToken());
            System.out.println("Expires In: " + holder.getExpiresIn());
            System.out.println("Token Type: " + holder.getTokenType());

            // You can now use the access token to call protected APIs
            // You might also want to store these tokens securely (e.g., in session or database)

        } catch (Auth0Exception e) {
            System.err.println("Authentication failed. Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
