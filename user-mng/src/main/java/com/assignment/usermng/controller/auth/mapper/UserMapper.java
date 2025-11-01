package com.assignment.usermng.controller.auth.mapper;


import com.assignment.usermng.controller.auth.dto.UserAuthRequest;
import com.assignment.usermng.controller.auth.dto.UserRegisterRequest;
import com.assignment.usermng.domain.user.User;
import com.assignment.usermng.service.user.dto.AuthenticationRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRegisterRequest user);

    AuthenticationRequest toAuthRequest(UserAuthRequest request);
}
