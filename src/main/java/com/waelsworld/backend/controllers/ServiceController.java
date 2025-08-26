package com.waelsworld.backend.controllers;

import com.waelsworld.backend.services.ServiceRecService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id")
    public String getServiceByVehicleId(@RequestParam UUID vehicleId) {
        return "Service details";
    }
}
