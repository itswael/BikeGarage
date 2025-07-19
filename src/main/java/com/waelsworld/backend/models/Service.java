package com.waelsworld.backend.models;

import com.waelsworld.backend.models.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

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

    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL)
    Invoice invoice;

    UUID customerId;

    UUID mechanicId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    Vehicle vehicle;  // Link to the vehicle being serviced

}
