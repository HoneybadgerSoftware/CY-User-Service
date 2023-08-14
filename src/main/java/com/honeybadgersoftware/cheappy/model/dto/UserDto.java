package com.honeybadgersoftware.cheappy.model.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NonNull
    private Long id;
    @NonNull
    private String firstName;
    private String lastName;
    @NonNull
    private String email;
    private String password;
    @NonNull
    private AddressDto address;
}
