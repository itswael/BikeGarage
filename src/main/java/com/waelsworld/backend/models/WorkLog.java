package com.waelsworld.backend.models;

import com.waelsworld.backend.models.enums.WorkLogType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "worklogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkLog extends BaseEntity{
    String name;

    @Enumerated(EnumType.STRING)
    WorkLogType workLogType;

    float cost;

    int quantity;

    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    ServiceRec serviceRec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    Invoice invoice;
}
