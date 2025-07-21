package com.waelsworld.backend.services;

import com.waelsworld.backend.dtos.UserRequestDTO;
import com.waelsworld.backend.dtos.UserResponseDTO;
import com.waelsworld.backend.mapper.UserMapper;
import com.waelsworld.backend.models.User;
import com.waelsworld.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = UserMapper.toUser(request);

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
}

