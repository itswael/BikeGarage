package com.waelsworld.backend.controllers;

import com.waelsworld.backend.dtos.*;
import com.waelsworld.backend.models.User;
import com.waelsworld.backend.models.enums.Role;
import com.waelsworld.backend.repositories.UserRepository;
import com.waelsworld.backend.services.UserService;
import com.waelsworld.backend.utils.GoogleTokenVerifier;
import com.waelsworld.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final GoogleTokenVerifier googleTokenVerifier;
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
                    newUser.setPhone("N/A");
                    newUser.setPassword("N/A"); // no password for Google user
                    newUser.setRole(Role.CUSTOMER);
                    return userRepository.save(newUser);
                });

        // Generate your own JWT
        String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}

