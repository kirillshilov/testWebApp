package com.webapptest.webapptest.controller;

import com.webapptest.webapptest.model.User;

import com.webapptest.webapptest.service.User.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceInterface userService;

    @PostMapping("/auth")
    public ResponseEntity<User> auth(@RequestBody Map<String, String> data) {
        return userService.addUser(data);
    }
}
