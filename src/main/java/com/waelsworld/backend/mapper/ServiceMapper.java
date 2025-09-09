package com.waelsworld.backend.mapper;

import com.waelsworld.backend.dtos.ServiceResponseDTO;
import com.waelsworld.backend.models.ServiceRec;

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
}
