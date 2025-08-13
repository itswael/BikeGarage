package com.waelsworld.backend.repositories;

import com.waelsworld.backend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class VehicleRepository extends JpaRepository<Vehicle, UUID> {

}
