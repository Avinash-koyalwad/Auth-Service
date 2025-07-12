package com.authentication.controller;

import com.authentication.dto.LoginRequest;
import com.authentication.dto.SignInRequest;
import com.authentication.dto.VerifyAccountRequest;
import com.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
        String response = authService.signIn(signInRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginRequest loginRequest) {
        String response = authService.logIn(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestBody VerifyAccountRequest verifyAccountRequest) {
        String response = authService.verifyAccount(verifyAccountRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend")
    public ResponseEntity<String> resendCode(@RequestParam String email) {
        String response = authService.resendCode(email);
        return ResponseEntity.ok(response);
    }


}
