package com.waelsworld.backend.controllers;

import com.waelsworld.backend.dtos.ServiceRequestDTO;
import com.waelsworld.backend.dtos.ServiceResponseDTO;
import com.waelsworld.backend.services.ServiceRecService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceRecService serviceRecService;

    /* TODO: create service endpoints
         - Get services by vehicle ID
         - calculate next service date based on last service and mileage
         - Add service record
         - Update service record
         - Delete service record
     */

    @GetMapping("/{id}")
    public ResponseEntity<List<ServiceResponseDTO>> getServiceByVehicleId(@RequestParam UUID id) {
        // Placeholder implementation
        List<ServiceResponseDTO> services = serviceRecService.getService(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping("/create")
    public ResponseEntity<ServiceResponseDTO> addServiceRecord(@RequestBody ServiceRequestDTO serviceRequestDTO) {
        ServiceResponseDTO serviceResponseDTO = serviceRecService.createService(serviceRequestDTO);
        return ResponseEntity.ok(serviceResponseDTO);
    }
}
