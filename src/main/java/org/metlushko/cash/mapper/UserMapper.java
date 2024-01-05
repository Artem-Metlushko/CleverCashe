package org.metlushko.cash.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.entity.User;


/**
 * Mapper interface for converting between User and UserDto entities.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto The UserDto to be converted.
     * @return The converted User entity.
     */
    @Mapping(target = "userId", ignore = true)
    User toEntity(UserDto userDto);

    /**
     * Updates an existing User entity with the values from a UserDto.
     *
     * @param user     The existing User entity to be updated.
     * @param userDto  The UserDto containing updated information.
     * @return The updated User entity.
     */
    @Mapping(target = "userId", ignore = true)
    User toEntity(@MappingTarget User user, UserDto userDto);
}
