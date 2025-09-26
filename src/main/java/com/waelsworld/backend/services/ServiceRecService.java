package com.waelsworld.backend.services;

import com.waelsworld.backend.dtos.ServiceRequestDTO;
import com.waelsworld.backend.dtos.ServiceResponseDTO;
import com.waelsworld.backend.mapper.ServiceMapper;
import com.waelsworld.backend.models.ServiceRec;
import com.waelsworld.backend.models.Vehicle;
import com.waelsworld.backend.repositories.ServiceRepository;
import com.waelsworld.backend.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceRecService {

    private final ServiceRepository serviceRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceResponseDTO createService(ServiceRequestDTO serviceRequestDTO) {
        Vehicle vehicle = vehicleRepository.getById(serviceRequestDTO.getVehicleId());
        ServiceRec service = ServiceMapper.from(vehicle);
        // Logic to set service properties from serviceRequestDTO
        service = serviceRepository.save(service);
        return ServiceMapper.to(service);
    }

    public List<ServiceResponseDTO> getService(UUID vehicleId) {
        Optional<ServiceRec> services= serviceRepository.findById(vehicleId);
        return services.stream().map(ServiceMapper::to).toList();
    }


}
