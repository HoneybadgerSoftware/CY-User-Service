package com.honeybadgersoftware.userservice.service.impl;

import com.honeybadgersoftware.userservice.model.dto.UserDto;
import com.honeybadgersoftware.userservice.model.request.UserCreateRequestBody;
import com.honeybadgersoftware.userservice.repository.UserRepository;
import com.honeybadgersoftware.userservice.repository.entity.UserEntity;
import com.honeybadgersoftware.userservice.service.UserService;
import com.honeybadgersoftware.userservice.utils.mapper.UserMapper;
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
    public UserDto save(UserCreateRequestBody user) {
        return userMapper.entityToDto(userRepository.save(userMapper.creationRequestBodyToEntity(user)));
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::entityToDto);
    }

    @Override
    public int deleteById(Long id) {

        if (userRepository.existsById(id)) {

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
            return Optional.of(userRepository.save(userMapper.dtoToEntity(updatedUser)));
        }

        return Optional.empty();
    }
}
