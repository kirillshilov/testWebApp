package com.webapptest.webapptest.controller;

import com.webapptest.webapptest.model.User;

import com.webapptest.webapptest.service.User.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceInterface userService;

    @GetMapping("/")
    public String index(@RequestParam Map<String, String> data, Model model) {
        User user = userService.addUser(data).getBody();
        model.addAttribute("user", user);
        return "index";
    }
}
