package com.honeybadgersoftware.cheappy.model;

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
    String city;
    @Column(name = "zip_code", nullable = false)
    String zipCode;
    @Column(name = "street", nullable = false)
    String street;
    @Column(name = "building_number", nullable = false)
    String buildingNumber;
    @Column(name = "flat_number", nullable = true)
    String flatNumber;
}
