package com.waelsworld.backend.models;

import com.waelsworld.backend.models.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity{
    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal base;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal tax;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    List<WorkLog> workLogs;

    @Enumerated(EnumType.STRING)
    InvoiceStatus status;

    UUID paymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    Service service;
}
