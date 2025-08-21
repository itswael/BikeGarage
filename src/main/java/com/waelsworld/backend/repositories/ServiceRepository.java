package com.waelsworld.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
}
