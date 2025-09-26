package com.waelsworld.backend.services;

import com.waelsworld.backend.dtos.ServiceRequestDTO;
import com.waelsworld.backend.dtos.ServiceResponseDTO;
import com.waelsworld.backend.mapper.ServiceMapper;
import com.waelsworld.backend.models.ServiceRec;
import com.waelsworld.backend.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceRecService {

    private final ServiceRepository serviceRepository;

    public ServiceResponseDTO createService(ServiceRequestDTO serviceRequestDTO) {
        ServiceRec service = new ServiceRec();
        // Logic to set service properties from serviceRequestDTO
        service = serviceRepository.save(service);
        return ServiceMapper.to(service);
    }


}
