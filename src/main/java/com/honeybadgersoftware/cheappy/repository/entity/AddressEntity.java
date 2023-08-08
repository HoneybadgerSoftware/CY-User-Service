package com.honeybadgersoftware.cheappy.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressEntity {
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "building_number", nullable = false)
    private String buildingNumber;
    @Column(name = "flat_number", nullable = true)
    private String flatNumber;
}
