package com.authentication.controller;

import com.authentication.dto.LoginRequest;
import com.authentication.dto.SignInRequest;
import com.authentication.dto.VerifyAccountRequest;
import com.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/login")
    public String logIn(@RequestBody LoginRequest loginRequest) {
        return authService.logIn(loginRequest);
    }

    @PostMapping("/verify")
    public String verifyAccount(@RequestBody VerifyAccountRequest verifyAccountRequest) {
        return authService.verifyAccount(verifyAccountRequest);
    }

    @PostMapping("/resend")
    public String resendCode(@RequestBody String email) {
        return authService.resendCode(email);
    }


}
