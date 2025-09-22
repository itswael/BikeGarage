package com.waelsworld.backend.mapper;

import com.waelsworld.backend.dtos.ServiceRequestDTO;
import com.waelsworld.backend.dtos.ServiceResponseDTO;
import com.waelsworld.backend.models.ServiceRec;
import com.waelsworld.backend.models.Vehicle;
import com.waelsworld.backend.models.enums.ServiceStatus;

public class ServiceMapper {
    public static ServiceResponseDTO to(ServiceRec serviceRec) {
        ServiceResponseDTO dto = new ServiceResponseDTO();
        dto.setVehicleId(serviceRec.getVehicle().getId());
        dto.setServiceCentreId(serviceRec.getServiceCentre().getId());
        dto.setServiceStatus(serviceRec.getStatus());
        dto.setServiceDate(serviceRec.getServiceDate());
        dto.setWorkLogs(serviceRec.getWorkLogs());
        dto.setInvoice(serviceRec.getInvoice());
        dto.setMechanicId(serviceRec.getMechanicId());
        return dto;
    }

    public static ServiceRec from(Vehicle vehicle) {
        ServiceRec serviceRec = new ServiceRec();
        // Logic to set serviceRec properties from dto
        serviceRec.setVehicle(vehicle);
        serviceRec.setServiceDate(java.time.LocalDateTime.now());
        serviceRec.setStatus(ServiceStatus.PENDING);
        serviceRec.setInvoice(null);
        serviceRec.setMechanicId(null);
        serviceRec.setWorkLogs(null);
        return serviceRec;
    }
}
