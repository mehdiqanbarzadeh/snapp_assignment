package com.assignment.usermng.service.user;

import com.assignment.usermng.domain.user.User;
import com.assignment.usermng.service.user.dto.AccessTokenResponse;
import com.assignment.usermng.service.user.dto.AuthenticationRequest;

public interface UserService {
    void register(User user);

    AccessTokenResponse authentication(AuthenticationRequest authRequest);
}
