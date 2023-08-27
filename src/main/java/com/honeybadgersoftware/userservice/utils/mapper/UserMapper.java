package com.honeybadgersoftware.userservice.utils.mapper;

import com.honeybadgersoftware.userservice.model.dto.UserDto;
import com.honeybadgersoftware.userservice.model.request.UserCreateRequestBody;
import com.honeybadgersoftware.userservice.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto entityToDto(UserEntity user);

    UserEntity dtoToEntity(UserDto user);

    UserEntity creationRequestBodyToEntity(UserCreateRequestBody user);
}
