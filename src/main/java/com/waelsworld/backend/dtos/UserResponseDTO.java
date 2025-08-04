package com.waelsworld.backend.dtos;

import com.waelsworld.backend.models.User;
import com.waelsworld.backend.models.enums.Role;
import lombok.Data;
import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private Role role;
}
