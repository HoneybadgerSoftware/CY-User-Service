package com.honeybadgersoftware.userservice.service;

import com.honeybadgersoftware.userservice.model.dto.UserDto;
import com.honeybadgersoftware.userservice.model.request.UserCreateRequestBody;
import com.honeybadgersoftware.userservice.repository.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserDto save(UserCreateRequestBody user);

    Optional<UserDto> getById(Long id);

    int deleteById(Long id);

    Optional<UserEntity> updateUser(Long id, UserDto updatedUser);
}
