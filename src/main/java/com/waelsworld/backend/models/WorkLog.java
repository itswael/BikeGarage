package com.waelsworld.backend.models;

import com.waelsworld.backend.models.enums.WorkLogType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "work_logs")
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
}
