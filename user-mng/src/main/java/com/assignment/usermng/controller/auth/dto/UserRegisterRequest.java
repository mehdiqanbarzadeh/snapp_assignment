package com.assignment.usermng.controller.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotNull(message = "username.must.not.be.null") // TODO : add this key into resource boundle with correct message to user
    private String username;

    @NotNull(message = "password.must.not.be.null") // TODO : add this key into resource boundle with correct message to user
    private String password;

}
