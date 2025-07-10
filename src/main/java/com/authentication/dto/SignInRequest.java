package com.authentication.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequest {

    private String name;
    private String email;
    private String password;
    private List<String> roles;
}
