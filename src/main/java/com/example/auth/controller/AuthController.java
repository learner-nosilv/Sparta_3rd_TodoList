package com.example.auth.controller;

import com.example.auth.dto.AuthSignupRequestDto;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto authSignupRequestDto) {
        authService.signup(authSignupRequestDto);
    }

}
