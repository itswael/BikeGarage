package com.waelsworld.backend.dtos;

import com.waelsworld.backend.models.User;
import lombok.Data;
import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;

    public UserResponseDTO from (User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        return this;
    }
}
