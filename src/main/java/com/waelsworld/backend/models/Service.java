package com.waelsworld.backend.models;

import com.waelsworld.backend.models.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
@Getter
@Setter
public class Service extends BaseEntity {
    @CreatedDate
    LocalDateTime serviceDate;

    @Enumerated(EnumType.STRING)
    ServiceStatus status;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    List<WorkLog> workLogs;

    @NonNull
    Invoice invoice;

    UUID customerId;

    UUID MechanicId;

}
