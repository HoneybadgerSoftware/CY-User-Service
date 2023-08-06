package com.honeybadgersoftware.cheappy.service;

import com.honeybadgersoftware.cheappy.model.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    ResponseEntity<UserEntity> save(UserEntity user);

    Optional<UserEntity> getById(Long id);

    ResponseEntity<String> deleteById(Long id);

    ResponseEntity<UserEntity> updateUser(Long id, UserEntity updatedUser);
}
