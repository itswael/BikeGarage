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

    @GetMapping("/{id")
    public String getServiceByVehicleId(@RequestParam UUID vehicleId) {
        return "Service details";
    }
}
