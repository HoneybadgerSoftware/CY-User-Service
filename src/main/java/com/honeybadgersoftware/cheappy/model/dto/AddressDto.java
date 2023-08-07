package com.honeybadgersoftware.cheappy.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NonNull
    private String city;
    @NonNull
    private String zipCode;
    @NonNull
    private String street;
    @NonNull
    private String buildingNumber;
    private String flatNumber;
}
