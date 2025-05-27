package com.webapptest.webapptest.service.User;

import com.webapptest.webapptest.model.User;
import com.webapptest.webapptest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
