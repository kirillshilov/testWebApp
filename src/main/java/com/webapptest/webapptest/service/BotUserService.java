package com.webapptest.webapptest.service;

import com.webapptest.webapptest.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BotUserService {
    private final TelegramAuthService telegramAuthService;

    public ResponseEntity<User> addUser(Map<String, String> data) {
        telegramAuthService.checkHash(data);
        User user = telegramAuthService.getUser(data);
    }
}
