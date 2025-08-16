package com.waelsworld.backend.repositories;

import com.waelsworld.backend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    /**
     * Repository for Vehicle entity
     * Provides methods to interact with the database
     */
    public Optional<List<Vehicle>> findAllByUserId(UUID userId);

}
