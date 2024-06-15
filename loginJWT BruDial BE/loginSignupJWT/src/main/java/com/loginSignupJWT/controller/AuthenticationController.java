package com.loginSignupJWT.controller;


import com.loginSignupJWT.dto.JwtAuthenticationResponse;
import com.loginSignupJWT.dto.RefreshTokenRequest;
import com.loginSignupJWT.dto.SignUpRequest;
import com.loginSignupJWT.dto.SigninRequest;
import com.loginSignupJWT.entities.CustomResponse;
import com.loginSignupJWT.entities.User;
import com.loginSignupJWT.services.AuthenticationService;
import com.loginSignupJWT.utils.CustomResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<CustomResponse<User>> signup(@RequestBody SignUpRequest signUpRequest){
        // System.out.println("SignUpRequest: " + signUpRequest);
        User user = authenticationService.signup(signUpRequest);
        return CustomResponseUtil.createSuccessResponse(user, "User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<CustomResponse<JwtAuthenticationResponse>> signin(@RequestBody SigninRequest signinRequest){
        JwtAuthenticationResponse jwtResponse = authenticationService.signin(signinRequest);
        return CustomResponseUtil.createTokenResponse(jwtResponse, "Login successful", jwtResponse.getToken());
    }
    @PostMapping("/refresh")
    public ResponseEntity<CustomResponse<JwtAuthenticationResponse>> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        JwtAuthenticationResponse jwtResponse = authenticationService.refreshToken(refreshTokenRequest);
        return CustomResponseUtil.createTokenResponse(jwtResponse, "Token refreshed successfully", jwtResponse.getToken());
    }
}
