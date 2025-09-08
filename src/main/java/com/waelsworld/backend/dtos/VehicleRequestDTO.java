package com.waelsworld.backend.dtos;

import com.waelsworld.backend.models.ServiceRec;
import com.waelsworld.backend.models.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDTO {
    private String name;
    private int make;
    private String registrationNumber;
    private VehicleType type;
    private UUID userId;
    private List<ServiceRec> serviceRecs;
    private LocalDateTime lastServiceDate;
}
