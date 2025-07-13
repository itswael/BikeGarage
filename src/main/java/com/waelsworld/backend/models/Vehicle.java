package com.waelsworld.backend.models;

import com.waelsworld.backend.models.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int make;  // e.g. 2023 (year), consider using Year or LocalDate if needed

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Link to the owner/customer

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services = new ArrayList<>();

    @Column(name = "last_service_date")
    private LocalDateTime lastServiceDate;
}
