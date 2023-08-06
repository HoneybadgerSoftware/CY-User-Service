package com.honeybadgersoftware.cheappy.service.impl;

import com.honeybadgersoftware.cheappy.model.AddressEntity;
import com.honeybadgersoftware.cheappy.model.UserEntity;
import com.honeybadgersoftware.cheappy.repository.UserRepository;
import com.honeybadgersoftware.cheappy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    //TODO
    // wyseparuj logike http od serwisu !!!
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<UserEntity> save(UserEntity user) {

        try {
            AddressEntity address = user.getAddress();
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    userRepository.save(
                            UserEntity.builder()
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .email(user.getEmail())
                                    .password(user.getPassword())
                                    .address(AddressEntity.builder()
                                            .city(address.getCity())
                                            .zipCode(address.getZipCode())
                                            .street(address.getStreet())
                                            .buildingNumber(address.getBuildingNumber())
                                            .flatNumber(address.getFlatNumber())
                                            .build())
                                    .build()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User with id: " + id + "has been deleted");
        }

        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<UserEntity> updateUser(Long id, UserEntity updatedUser) {

        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserEntity user = buildUpdatedUser(id, updatedUser);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }


    private UserEntity buildUpdatedUser(Long id, UserEntity updatedUser) {
        AddressEntity address = updatedUser.getAddress();

        return UserEntity.builder()
                .id(id)
                .firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName())
                .email(updatedUser.getEmail())
                .password(updatedUser.getPassword())
                .address(
                        AddressEntity.builder()
                                .city(address.getCity())
                                .zipCode(address.getZipCode())
                                .street(address.getStreet())
                                .buildingNumber(address.getBuildingNumber())
                                .flatNumber(address.getFlatNumber())
                                .build()
                )
                .build();
    }
}
