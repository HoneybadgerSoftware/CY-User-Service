package com.honeybadgersoftware.cheappy.utils.mapper;

import com.honeybadgersoftware.cheappy.model.dto.UserDto;
import com.honeybadgersoftware.cheappy.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity user);

    UserEntity toEntity(UserDto user);
}
