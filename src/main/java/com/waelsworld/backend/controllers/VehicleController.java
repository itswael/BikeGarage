package com.waelsworld.backend.controllers;

import com.waelsworld.backend.dtos.VehicleRequestDTO;
import com.waelsworld.backend.dtos.VehicleResponseDTO;
import com.waelsworld.backend.models.Vehicle;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/vehicles")
public class VehicleController {
    @PostMapping
    public VehicleResponseDTO addVehicle(@RequestBody VehicleRequestDTO vehicle) {
        return new VehicleResponseDTO(
        );
    }
}
