package com.portfolio.springreactredditclone.service;

import com.portfolio.springreactredditclone.dto.RegisterRequest;
import com.portfolio.springreactredditclone.model.User;
import com.portfolio.springreactredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.Instant.now;

@Service
@AllArgsConstructor
@Slf4j
public class SignupService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        userRepository.save(createUser(registerRequest));
    }

    @NotNull
    private User createUser(RegisterRequest registerRequest) {
        var user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setCreated(now());
        user.setEnabled(false);
        return user;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
