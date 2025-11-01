package com.assignment.usermng.controller.auth;



import com.assignment.usermng.controller.auth.dto.UserAuthRequest;
import com.assignment.usermng.controller.auth.dto.UserRegisterRequest;
import com.assignment.usermng.controller.auth.mapper.UserMapper;
import com.assignment.usermng.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        userService.register(userMapper.toUser(request));
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthRequest request) {
        return ResponseEntity.ok(userService.authentication(userMapper.toAuthRequest(request)));
    }
}

