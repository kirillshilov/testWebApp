package com.webapptest.webapptest.service.User;

import com.webapptest.webapptest.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserServiceInterface {
    ResponseEntity<User> addUser(Map<String, String> data);
}
