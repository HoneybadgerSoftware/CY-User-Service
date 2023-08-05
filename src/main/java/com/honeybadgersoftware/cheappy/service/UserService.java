package com.honeybadgersoftware.cheappy.service;

import com.honeybadgersoftware.cheappy.model.UserEntity;

public interface UserService {
    UserEntity save(String firstName,
                    String lastName,
                    String email,
                    String password,
                    String city,
                    String zipCode,
                    String street,
                    String buildingNumber,
                    String flatNumber);
}
