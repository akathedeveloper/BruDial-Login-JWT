package com.loginSignupJWT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String country;
}
