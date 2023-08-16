package com.honeybadgersoftware.userservice.utils.mapper;

import com.honeybadgersoftware.userservice.model.dto.UserDto;
import com.honeybadgersoftware.userservice.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity user);

    UserEntity toEntity(UserDto user);
}
