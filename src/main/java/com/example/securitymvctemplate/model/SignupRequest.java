package com.example.securitymvctemplate.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
