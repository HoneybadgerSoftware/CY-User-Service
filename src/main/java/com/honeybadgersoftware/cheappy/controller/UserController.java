package com.honeybadgersoftware.cheappy.controller;

import com.honeybadgersoftware.cheappy.model.UserEntity;
import com.honeybadgersoftware.cheappy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserEntity createUser(
            String firstName,
            String lastName,
            String email,
            String password,
            String city,
            String zipCode,
            String street,
            String buildingNumber,
            String flatNumber) {
        return userService.save(firstName, lastName, email, password, city, zipCode, street, buildingNumber, flatNumber);
    }
}
