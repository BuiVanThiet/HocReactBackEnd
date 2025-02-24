package org.example.backendshop.mappers;

import org.example.backendshop.dto.SigUpDTO;
import org.example.backendshop.dto.UserDTO;
import org.example.backendshop.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User sigUpToUser(SigUpDTO sigUpDTO);
}
