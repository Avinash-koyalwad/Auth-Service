package com.authentication.service;

import com.authentication.dto.LoginRequest;
import com.authentication.dto.SignInRequest;
import com.authentication.dto.VerifyAccountRequest;
import com.authentication.entity.User;
import com.authentication.exception.*;
import com.authentication.repository.UserRepository;
import com.authentication.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Random random = new Random();

    public String signIn(SignInRequest signInRequest) {
        Optional<User> user = userRepository.findByEmail(signInRequest.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + signInRequest.getEmail());
        }
        User newUser = User.builder()
                .name(signInRequest.getName())
                .email(signInRequest.getEmail())
                .password(passwordEncoder.encode(signInRequest.getPassword()))
                .role(signInRequest.getRole())
                .isVerified(false)
                .verificationCode(generateVerificationCode())
                .verificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15))
                .build();
        userRepository.save(newUser);

        return "User signed in successfully";
    }

    private String generateVerificationCode() {
        int code = this.random.nextInt(999999) + 100000;
        return String.valueOf(code);
    }

    public String logIn(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isVerified()) {
            throw new AccountNotVerifiedException("Account not verified. Please verify your account.");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        return jwtService.generateToken(loginRequest.getEmail());
    }

    public String verifyAccount(VerifyAccountRequest verifyAccountRequest) {
        User user = userRepository.findByEmail(verifyAccountRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
            throw new VerificationCodeExpiredException("Verification code has expired");
        }

        if (!user.getVerificationCode().equals(verifyAccountRequest.getVerificationCode())) {
            throw new InvalidVerificationCodeException("Invalid verification code");
        }

        user.setVerified(true);
        user.setVerificationCode(null);
        user.setVerificationCodeExpiresAt(null);
        userRepository.save(user);
        return "Account verified successfully";
    }

    public String resendCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.isVerified()) {
            throw new AccountAlreadyVerifiedException("Account is already verified");
        }

        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);

        // Here you would typically send the new code via email
        return "Verification code resent successfully";
    }
}
