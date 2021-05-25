package com.portfolio.springreactredditclone.controller;

import com.portfolio.springreactredditclone.dto.RegisterRequest;
import com.portfolio.springreactredditclone.service.SignupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@AllArgsConstructor
public class SignupController {

    private SignupService signupService;

    @PostMapping(path = "/signup")
    public ResponseEntity<RegisterRequest> signup(@RequestBody RegisterRequest registerRequest) {
        signupService.signup(registerRequest);
        return ResponseEntity.ok(registerRequest);
    }
}
