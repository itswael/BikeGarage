package com.waelsworld.backend.mapper;

import com.waelsworld.backend.dtos.VehicleRequestDTO;
import com.waelsworld.backend.dtos.VehicleResponseDTO;
import com.waelsworld.backend.models.User;
import com.waelsworld.backend.models.Vehicle;

public class VehicleMapper {
    public static Vehicle toVehicle(VehicleRequestDTO dto, User user) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(dto.getMake());
        vehicle.setName(dto.getName());
        vehicle.setRegistrationNumber(dto.getRegistrationNumber());
        vehicle.setType(dto.getType());
        vehicle.setUser(user);
        vehicle.setServiceRecs(dto.getServiceRecs());
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
        dto.setUserId(vehicle.getUser().getId());
        dto.setServiceRecs(vehicle.getServiceRecs());
        dto.setLastServiceDate(vehicle.getLastServiceDate());
        return dto;
    }
}
