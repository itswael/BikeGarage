package com.waelsworld.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.waelsworld.backend.dtos.UserRequestDTO;
import com.waelsworld.backend.dtos.UserResponseDTO;
import com.waelsworld.backend.errors.userErrors;
import com.waelsworld.backend.exceptions.DuplicateResourceException;
import com.waelsworld.backend.mapper.UserMapper;
import com.waelsworld.backend.models.User;
import com.waelsworld.backend.models.enums.Role;
import com.waelsworld.backend.repositories.UserRepository;
import com.waelsworld.backend.utils.userUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDTO createUser(UserRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException(userErrors.EMAIL_ALREADY_EXISTS.getMessage());
        }

        if (!userUtils.validateUserData(request)) {
            throw new IllegalArgumentException(userErrors.REQUIRED_FIELDS_MISSING.getMessage());
        }

        if (!userUtils.validatePassword(request.getPassword())) {
            throw new IllegalArgumentException(userErrors.PASSWORD_TOO_SHORT.getMessage());
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new DuplicateResourceException(userErrors.USERNAME_ALREADY_EXISTS.getMessage());
        }

        User user = UserMapper.toUser(request);

        // Set default role as CUSTOMER for new registrations
        if (user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);

        return UserMapper.from(saved);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public UserResponseDTO loginUser(UserRequestDTO request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException(userErrors.USER_NOT_FOUND.getMessage());
        }

        if(!passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            throw new IllegalArgumentException(userErrors.INVALID_LOGIN_CREDENTIALS.getMessage());
        }

        // user exists and password matches
        // create a jwt token



        return UserMapper.from(userOpt.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

