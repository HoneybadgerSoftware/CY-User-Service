package com.honeybadgersoftware.userservice.model.request;

import com.honeybadgersoftware.userservice.model.dto.AddressDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestBody {
    @NonNull
    private String firstName;
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private AddressDto address;
}
