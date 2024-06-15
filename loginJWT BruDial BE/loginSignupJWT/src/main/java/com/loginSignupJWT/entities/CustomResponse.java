package com.loginSignupJWT.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse<T> {
    private String status;
    private int statusCode;
    private T data;
    private String message;
    private String error;
    private String token;
}
