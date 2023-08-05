package com.honeybadgersoftware.cheappy.service.impl;

import com.honeybadgersoftware.cheappy.model.AddressEntity;
import com.honeybadgersoftware.cheappy.model.UserEntity;
import com.honeybadgersoftware.cheappy.repository.UserRepository;
import com.honeybadgersoftware.cheappy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity save(String firstName,
                           String lastName,
                           String email,
                           String password,
                           String city,
                           String zipCode,
                           String street,
                           String buildingNumber,
                           String flatNumber) {
        return userRepository.save(UserEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .address(AddressEntity.builder()
                        .city(city)
                        .zipCode(zipCode)
                        .street(street)
                        .buildingNumber(buildingNumber)
                        .flatNumber(flatNumber)
                        .build())
                .build());
    }
}
