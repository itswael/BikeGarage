package com.waelsworld.backend.services;

import com.waelsworld.backend.dtos.VehicleRequestDTO;
import com.waelsworld.backend.dtos.VehicleResponseDTO;
import com.waelsworld.backend.mapper.VehicleMapper;
import com.waelsworld.backend.models.Vehicle;
import com.waelsworld.backend.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VehicleService {
    VehicleRepository vehicleRepository;
    public VehicleResponseDTO createVehicle(VehicleRequestDTO vehicleDto) {
        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDto);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.from(savedVehicle);
    }

    public List<VehicleResponseDTO> getUserVehicles(UUID userId) {
        Optional<List<Vehicle>> vehicles = vehicleRepository.findAllByUserId(userId);
        return vehicles
                .map(vehicleList -> vehicleList.stream()
                        .map(VehicleMapper::from)
                        .toList())
                .orElseThrow(() -> new RuntimeException("No vehicles found for user with ID: " + userId));
    }
}
