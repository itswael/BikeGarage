package com.waelsworld.backend.services;

import com.waelsworld.backend.dtos.VehicleRequestDTO;
import com.waelsworld.backend.dtos.VehicleResponseDTO;
import com.waelsworld.backend.mapper.VehicleMapper;
import com.waelsworld.backend.models.User;
import com.waelsworld.backend.models.Vehicle;
import com.waelsworld.backend.repositories.UserRepository;
import com.waelsworld.backend.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    public VehicleResponseDTO createVehicle(VehicleRequestDTO vehicleDto) {

        User user = userRepository.findById(vehicleDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + vehicleDto.getUserId()));
        Vehicle vehicle = VehicleMapper.toVehicle(vehicleDto, user);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        // TODO: add user details from the token -- if user is adding his vehivle
        // else fetch fromt he body if the admin is adding a vehicle for a user

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
