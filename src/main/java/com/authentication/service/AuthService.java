package com.authentication.service;

import com.authentication.dto.SignInRequest;
import com.authentication.entity.User;
import com.authentication.exception.UserAlreadyExistsException;
import com.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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
                .build();
        userRepository.save(newUser);

        return "User signed in successfully";
    }
}
