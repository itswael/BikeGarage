package com.waelsworld.backend.models;

import jakarta.persistence.*;
import lombok.*;

import com.waelsworld.backend.models.enums.Role;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

}

