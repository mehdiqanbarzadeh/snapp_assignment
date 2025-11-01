package com.assignment.usermng.service.user.impl;

import com.assignment.usermng.domain.user.User;
import com.assignment.usermng.domain.user.dao.UserRepository;
import com.assignment.usermng.service.user.UserService;
import com.assignment.usermng.service.user.dto.AccessTokenResponse;
import com.assignment.usermng.service.user.dto.AuthenticationRequest;
import com.assignment.usermng.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public void register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use");//TODO fix exception with accure exception handling
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    @Override
    public AccessTokenResponse authentication(AuthenticationRequest authRequest) {
        User user =
                userRepository.findByUsername(authRequest.getUsername())
                        .orElseThrow(() -> new IllegalArgumentException("username or passowrd invalid"));//TODO fix exception handling
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("username or passowrd invalid");
        }

        AccessTokenResponse accessTokenResponse = new AccessTokenResponse(); // todo : do it with mapper
        accessTokenResponse.setAccessToken(jwtUtil.generateToken(user.getUsername(), user.getRole()));
        return accessTokenResponse;
    }
}
