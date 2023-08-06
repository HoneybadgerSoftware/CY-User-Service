package com.honeybadgersoftware.cheappy.controller;

import com.honeybadgersoftware.cheappy.model.UserEntity;
import com.honeybadgersoftware.cheappy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return userService.save(user);
    }

    @GetMapping(path = "/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        return userService.getById(id).get();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
}
