package com.honeybadgersoftware.cheappy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    @Column(name = "city", nullable = false)
    String city;
    @Column(name = "zipCode", nullable = false)
    String zipCode;
    @Column(name = "street", nullable = false)
    String street;
    @Column(name = "buildingNumber", nullable = false)
    String buildingNumber;
    @Column(name = "flatNumber", nullable = true)
    String flatNumber;
}
