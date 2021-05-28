package com.portfolio.springreactredditclone.service;

import com.portfolio.springreactredditclone.dto.RegisterRequest;
import com.portfolio.springreactredditclone.model.User;
import com.portfolio.springreactredditclone.model.VerificationToken;
import com.portfolio.springreactredditclone.repository.UserRepository;
import com.portfolio.springreactredditclone.repository.VerificationTokenRepository;
import com.portfolio.springreactredditclone.utli.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.portfolio.springreactredditclone.utli.Constants.ACTIVATION_EMAIL;
import static java.time.Instant.now;

@Service
@AllArgsConstructor
@Slf4j
public class SignupService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository verificationTokenRepository;
    private MailContentBuilder mailContentBuilder;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        var user = createUser(registerRequest);
        userRepository.save(user);
        var token = generateVerificationToken(user);
        mailContentBuilder.build(emailMessage(token));
    }

    private String generateVerificationToken(User user){
        var token = UUID.randomUUID().toString();
        var verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
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

    private String emailMessage(String token){
        var stringBuilder = new StringBuilder();
        var message = stringBuilder.append("Thank you for registering to the Spring Reddit Clone, please click on the url to activate your account : ")
                .append(ACTIVATION_EMAIL)
                .append("/")
                .append(token).toString();
        return message;
    }
}
