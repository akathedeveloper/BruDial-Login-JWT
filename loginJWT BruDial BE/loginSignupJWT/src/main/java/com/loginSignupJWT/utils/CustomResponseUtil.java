package com.loginSignupJWT.utils;

import com.loginSignupJWT.entities.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseUtil {
    public static <T> ResponseEntity<CustomResponse<T>> createResponse(HttpStatus status, T data, String message, String error, String token){
        CustomResponse<T> response = new CustomResponse<>();
        response.setStatus(status.getReasonPhrase());
        response.setStatusCode(status.value());
        response.setData(data);
        response.setMessage(message);
        response.setError(error);
        response.setToken(token);

        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<CustomResponse<T>> createSuccessResponse(T data, String message) {
        return createResponse(HttpStatus.OK, data, message, null, null);
    }

    public static <T> ResponseEntity<CustomResponse<T>> createErrorResponse(HttpStatus status, String error, String message) {
        return createResponse(status, null, message, error, null);
    }

    public static <T> ResponseEntity<CustomResponse<T>> createTokenResponse(T data, String message, String accessToken) {
        return createResponse(HttpStatus.OK, data, message, null, accessToken);
    }

}
