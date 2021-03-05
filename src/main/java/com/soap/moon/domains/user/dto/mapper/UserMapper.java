package com.soap.moon.domains.user.dto.mapper;

import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mappings({
//        @Mapping(target = "status", constant = "ACTIVE"),
//        @Mapping(target = "field", constant = "NONE")
//    })
//    User userDtoToEntity(UserDto.SignInReq dto);
//
//    SelectUserRes userToDto(User user);

}