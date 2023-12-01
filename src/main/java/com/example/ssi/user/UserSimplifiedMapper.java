package com.example.ssi.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSimplifiedMapper {
    UserSimplifiedMapper INSTANCE = Mappers.getMapper(UserSimplifiedMapper.class);
    UserSimplified userToSimplifiedUser(User user);
}
