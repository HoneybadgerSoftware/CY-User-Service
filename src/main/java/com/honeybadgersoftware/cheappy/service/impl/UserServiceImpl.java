package com.honeybadgersoftware.cheappy.service.impl;

import com.honeybadgersoftware.cheappy.model.dto.UserDto;
import com.honeybadgersoftware.cheappy.repository.UserRepository;
import com.honeybadgersoftware.cheappy.repository.entity.UserEntity;
import com.honeybadgersoftware.cheappy.service.UserService;
import com.honeybadgersoftware.cheappy.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity save(UserDto user) {
        return userRepository.save(userMapper.toEntity(user));
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::toDto);
    }

    @Override
    public int deleteById(Long id) {

        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return 1;
        }
        return 0;
    }


    @Override
    public Optional<UserEntity> updateUser(Long id, UserDto updatedUser) {

        updatedUser.setId(id);
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return Optional.of(userRepository.save(userMapper.toEntity(updatedUser)));
        }

        return Optional.empty();
    }
}
