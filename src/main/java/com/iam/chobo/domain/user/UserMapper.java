package com.iam.chobo.domain.user;

import com.iam.chobo.domain.user.dto.Container;

import org.mapstruct.Mapper;

@Mapper(
    componentModel="spring"
)
public interface UserMapper {
    User containertoUser(Container container);
    Container usertoContainer(User user);
}