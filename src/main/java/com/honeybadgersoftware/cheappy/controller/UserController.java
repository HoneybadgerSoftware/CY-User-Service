package com.honeybadgersoftware.cheappy.controller;

import com.honeybadgersoftware.cheappy.model.dto.UserDto;
import com.honeybadgersoftware.cheappy.repository.entity.UserEntity;
import com.honeybadgersoftware.cheappy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        Optional<UserDto> user = userService.getById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        Optional<UserEntity> user = userService.updateUser(id, updatedUser);
        return user.isPresent() ?
                ResponseEntity.ok("User with id: " + id + " successfully updated")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with provided id does not exists");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteById(id) == 1 ?
                ResponseEntity.status(HttpStatus.OK).body("Succesfully deleted entity with id: " + id)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
    }
}
