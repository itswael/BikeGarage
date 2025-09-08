package com.waelsworld.backend.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ServiceRequestDTO {
    private UUID vehicleId;
    private UUID serviceCentreId;
}
