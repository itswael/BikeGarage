package com.waelsworld.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.waelsworld.backend.utils.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        
                        // Test endpoints for role verification
                        .requestMatchers("/api/test/owner-only").hasRole("OWNER")
                        .requestMatchers("/api/test/mechanic-access").hasAnyRole("MECHANIC", "OWNER")
                        .requestMatchers("/api/test/customer-access").hasAnyRole("CUSTOMER", "OWNER")
                        .requestMatchers("/api/test/authenticated-only").authenticated()
                        
                        // Owner-only endpoints
                        .requestMatchers("/api/admin/**").hasRole("OWNER")
                        .requestMatchers("/api/service-centres/**").hasRole("OWNER")
                        .requestMatchers("/api/mechanics/**").hasRole("OWNER")
                        
                        // Mechanic endpoints
                        .requestMatchers("/api/services/**").hasAnyRole("MECHANIC", "OWNER")
                        .requestMatchers("/api/appointments/mechanic/**").hasAnyRole("MECHANIC", "OWNER")
                        
                        // Customer endpoints
                        .requestMatchers("/api/bookings/**").hasAnyRole("CUSTOMER", "OWNER")
                        .requestMatchers("/api/vehicles/**").hasAnyRole("CUSTOMER", "OWNER")
                        .requestMatchers("/api/appointments/customer/**").hasAnyRole("CUSTOMER", "OWNER")
                        
                        // Shared endpoints (all authenticated users)
                        .requestMatchers("/api/profile/**").authenticated()
                        .requestMatchers("/api/notifications/**").authenticated()
                        
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}