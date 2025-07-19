package com.waelsworld.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_centres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCentre extends BaseEntity {
    private String name;
    @ManyToOne
    private User owner;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String openingHours;
    private String servicesOffered;
    private String description;
}
