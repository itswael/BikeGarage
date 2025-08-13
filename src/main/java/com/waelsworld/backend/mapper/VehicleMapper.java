package com.waelsworld.backend.mapper;

import com.waelsworld.backend.dtos.VehicleRequestDTO;
import com.waelsworld.backend.dtos.VehicleResponseDTO;
import com.waelsworld.backend.models.Vehicle;

public class VehicleMapper {
    public static Vehicle toVehicle(VehicleRequestDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(dto.getMake());
        vehicle.setName(dto.getName());
        vehicle.setRegistrationNumber(dto.getRegistrationNumber());
        vehicle.setType(dto.getType());
        vehicle.setUser(dto.getUser());
        vehicle.setServices(dto.getServices());
        vehicle.setLastServiceDate(dto.getLastServiceDate());
        return vehicle;
    }

    public static VehicleResponseDTO from(Vehicle vehicle) {
        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setId(vehicle.getId());
        dto.setName(vehicle.getName());
        dto.setMake(vehicle.getMake());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
        dto.setType(vehicle.getType());
        dto.setUser(vehicle.getUser());
        dto.setServices(vehicle.getServices());
        dto.setLastServiceDate(vehicle.getLastServiceDate());
        return dto;
    }
}
