package com.waelsworld.backend.dtos;

import com.waelsworld.backend.models.User;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String username;
}