package com.waelsworld.backend.models;

import com.waelsworld.backend.models.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "work_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity{
    @Column(nullable = false)
    UUID serviceId;
    @Column(nullable = false)
    float base;
    @Column(nullable = false)
    float tax;
    @Column(nullable = false)
    float total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    List<WorkLog> workLogs;

    @Enumerated(EnumType.STRING)
    InvoiceStatus status;

    UUID paymentId;
}
