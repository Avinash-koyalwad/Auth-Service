package com.authentication.controller;

import com.authentication.dto.SignInRequest;
import com.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/Sign-in")
    public String signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Authentication service is running";
    }


}
