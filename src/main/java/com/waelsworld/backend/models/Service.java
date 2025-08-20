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
    private LocalDateTime serviceDate;

    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkLog> workLogs;

    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL)
    private Invoice invoice;

    private UUID customerId;

    private UUID mechanicId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;  // Link to the vehicle being serviced

}
