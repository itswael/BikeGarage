package com.waelsworld.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waelsworld.backend.dtos.AuthRequest;
import com.waelsworld.backend.dtos.AuthResponse;
import com.waelsworld.backend.dtos.GoogleAuthUrlResponse;
import com.waelsworld.backend.dtos.GoogleSignInRequest;
import com.waelsworld.backend.dtos.UserRequestDTO;
import com.waelsworld.backend.dtos.UserResponseDTO;
import com.waelsworld.backend.models.User;
import com.waelsworld.backend.models.enums.Role;
import com.waelsworld.backend.repositories.UserRepository;
import com.waelsworld.backend.services.GoogleOAuthService;
import com.waelsworld.backend.services.UserService;
import com.waelsworld.backend.utils.GoogleTokenVerifier;
import com.waelsworld.backend.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final GoogleTokenVerifier googleTokenVerifier;
    private final GoogleOAuthService googleOAuthService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            final UserDetails user = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtil.generateToken(user);

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleSignIn(@RequestBody GoogleSignInRequest request) {
        var payload = googleTokenVerifier.verify(request.getIdToken());

        if (payload == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google ID Token");
        }

        String email = payload.getEmail();
        String name = (String) payload.get("name");

        // Check if user exists, else create
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setUsername(email); // Use email as username for Google users
                    newUser.setPhone("N/A");
                    newUser.setPassword("N/A"); // no password for Google user
                    newUser.setRole(Role.CUSTOMER);
                    return userRepository.save(newUser);
                });

        // Generate your own JWT
        String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    /**
     * Generate Google OAuth authorization URL
     * GET /api/auth/google/url
     */
    @GetMapping("/google/url")
    public ResponseEntity<?> getGoogleAuthUrl() {
        try {
            String authUrl = googleOAuthService.generateAuthUrl();
            return ResponseEntity.ok(new GoogleAuthUrlResponse(authUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to generate Google auth URL");
        }
    }

    /**
     * Handle Google OAuth callback
     * GET /api/auth/google/callback?code=...
     */
    @GetMapping("/google/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam("code") String authorizationCode) {
        try {
            var tokenResponse = googleOAuthService.exchangeCodeForTokens(authorizationCode);
            var payload = googleOAuthService.verifyIdToken(tokenResponse.getIdToken());

            if (payload == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google ID Token");
            }

            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Check if user exists, else create
            User user = userRepository.findByEmail(email)
                    .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setName(name);
                        newUser.setUsername(email);
                        newUser.setPhone("N/A");
                        newUser.setPassword("N/A");
                        newUser.setRole(Role.CUSTOMER);
                        return userRepository.save(newUser);
                    });

            // Generate your own JWT
            String jwt = jwtUtil.generateToken(user);

            // Redirect to frontend with JWT (or return JSON based on your needs)
            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Google authentication failed");
        }
    }

}

