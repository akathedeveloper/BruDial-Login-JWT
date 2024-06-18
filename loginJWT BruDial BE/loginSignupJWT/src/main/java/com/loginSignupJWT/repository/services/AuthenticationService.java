package com.loginSignupJWT.repository.services;

import com.loginSignupJWT.dto.JwtAuthenticationResponse;
import com.loginSignupJWT.dto.RefreshTokenRequest;
import com.loginSignupJWT.dto.SignUpRequest;
import com.loginSignupJWT.dto.SigninRequest;
import com.loginSignupJWT.dto.UserDTO;  // Import UserDTO
import com.loginSignupJWT.entities.Role;
import com.loginSignupJWT.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest, String registeredBy);
    JwtAuthenticationResponse signin(SigninRequest signinRequest, Role requiredRole);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    UserDTO getUserDetails(String email);  // Add the getUserDetails method
}
