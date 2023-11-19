package org.metlushko.cash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.entity.User;

@Mapper
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    User toEntity(UserDto userDto);
    @Mapping(target = "userId", ignore = true)
    User toEntity(@MappingTarget User user, UserDto userDto);


}
