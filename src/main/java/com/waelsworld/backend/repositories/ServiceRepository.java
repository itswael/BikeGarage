package com.waelsworld.backend.repositories;

import com.waelsworld.backend.models.ServiceRec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceRec, UUID> {

}
