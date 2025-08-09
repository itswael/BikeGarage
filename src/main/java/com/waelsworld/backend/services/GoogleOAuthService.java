package com.waelsworld.backend.services;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleOAuthService {

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;

    private static final String[] SCOPES = {
            "openid",
            "email", 
            "profile"
    };

    /**
     * Generate Google OAuth authorization URL
     * This is what you were looking for!
     */
    public String generateAuthUrl() {
        try {
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    clientId,
                    clientSecret,
                    Arrays.asList(SCOPES))
                    .build();

            return flow.newAuthorizationUrl()
                    .setRedirectUri(redirectUri)
                    .setAccessType("offline")
                    .setApprovalPrompt("force")
                    .build();
                    
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Google auth URL", e);
        }
    }

    /**
     * Exchange authorization code for tokens
     */
    public GoogleTokenResponse exchangeCodeForTokens(String authorizationCode) {
        try {
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    clientId,
                    clientSecret,
                    Arrays.asList(SCOPES))
                    .build();

            return flow.newTokenRequest(authorizationCode)
                    .setRedirectUri(redirectUri)
                    .execute();
                    
        } catch (Exception e) {
            throw new RuntimeException("Failed to exchange code for tokens", e);
        }
    }

    /**
     * Verify Google ID token (your existing functionality)
     */
    public GoogleIdToken.Payload verifyIdToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(clientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            return (idToken != null) ? idToken.getPayload() : null;
        } catch (Exception e) {
            System.err.println("Google token verification failed: " + e.getMessage());
            return null;
        }
    }
}
