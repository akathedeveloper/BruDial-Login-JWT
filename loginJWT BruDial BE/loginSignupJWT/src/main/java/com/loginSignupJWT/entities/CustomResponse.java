package com.loginSignupJWT.entities;

import com.loginSignupJWT.dto.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    private String status;
    private int statusCode;
    private T data;
    private String message;
    private String error;
    private String token;

    public CustomResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
