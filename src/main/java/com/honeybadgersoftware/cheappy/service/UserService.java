package com.honeybadgersoftware.cheappy.service;

import com.honeybadgersoftware.cheappy.model.dto.UserDto;
import com.honeybadgersoftware.cheappy.repository.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity save(UserDto user);

    Optional<UserDto> getById(Long id);

    int deleteById(Long id);

    Optional<UserEntity> updateUser(Long id, UserDto updatedUser);
}
