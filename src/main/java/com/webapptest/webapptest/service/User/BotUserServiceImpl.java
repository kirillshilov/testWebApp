package com.webapptest.webapptest.service.User;

import com.webapptest.webapptest.model.User;
import com.webapptest.webapptest.service.TelegramAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BotUserServiceImpl implements UserServiceInterface {
    private final TelegramAuthService telegramAuthService;
    private final UserService userService;

    public ResponseEntity<User> addUser(Map<String, String> data) {
        telegramAuthService.checkHash(data);
        User user = telegramAuthService.getUserFromInitData(data);
        return ResponseEntity.ok().body(userService.save(user));
    }
}