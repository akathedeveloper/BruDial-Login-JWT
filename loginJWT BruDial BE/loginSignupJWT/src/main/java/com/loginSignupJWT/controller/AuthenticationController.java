package com.loginSignupJWT.controller;

import com.loginSignupJWT.dto.JwtAuthenticationResponse;
import com.loginSignupJWT.dto.RefreshTokenRequest;
import com.loginSignupJWT.entities.CustomResponse;
import com.loginSignupJWT.repository.services.AuthenticationService;
import com.loginSignupJWT.utils.CustomResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/refresh")
    public ResponseEntity<CustomResponse<JwtAuthenticationResponse>> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtAuthenticationResponse jwtResponse = authenticationService.refreshToken(refreshTokenRequest);
        return CustomResponseUtil.createTokenResponse(jwtResponse, "Token refreshed successfully", jwtResponse.getToken());
    }



}
