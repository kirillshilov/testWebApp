package com.webapptest.webapptest.controller;

import com.webapptest.webapptest.model.User;
import com.webapptest.webapptest.service.BotUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final BotUserService botUserService;

    @PostMapping("/auth")
    public ResponseEntity<User> auth(@RequestBody Map<String, String> data) {
        return botUserService.addUser(data);
    }
}
