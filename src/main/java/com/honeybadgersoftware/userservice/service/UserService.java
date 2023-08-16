package com.honeybadgersoftware.userservice.service;

import com.honeybadgersoftware.userservice.model.dto.UserDto;
import com.honeybadgersoftware.userservice.repository.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity save(UserDto user);

    Optional<UserDto> getById(Long id);

    int deleteById(Long id);

    Optional<UserEntity> updateUser(Long id, UserDto updatedUser);
}
