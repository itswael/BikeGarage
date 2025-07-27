package com.waelsworld.backend.mapper;

import com.waelsworld.backend.dtos.UserRequestDTO;
import com.waelsworld.backend.dtos.UserResponseDTO;
import com.waelsworld.backend.models.User;

public class UserMapper {

    public static User toUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDTO from(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }
}