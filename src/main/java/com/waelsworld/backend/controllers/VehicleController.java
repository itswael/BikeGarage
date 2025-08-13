package com.waelsworld.backend.controllers;

import com.waelsworld.backend.dtos.VehicleRequestDTO;
import com.waelsworld.backend.dtos.VehicleResponseDTO;
import com.waelsworld.backend.models.Vehicle;
import com.waelsworld.backend.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/vehicles")
public class VehicleController {
    /**
     * Vehicle endpoints (protected by SecurityConfig)
     */

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> addVehicle(@RequestBody VehicleRequestDTO vehicle) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicle), HttpStatus.CREATED);
    }
}
