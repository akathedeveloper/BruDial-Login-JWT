package com.loginSignupJWT.controller;
import com.loginSignupJWT.dto.JwtAuthenticationResponse;
import com.loginSignupJWT.dto.SignUpRequest;
import com.loginSignupJWT.dto.SigninRequest;
import com.loginSignupJWT.entities.CustomResponse;
import com.loginSignupJWT.entities.Role;
import com.loginSignupJWT.entities.User;
import com.loginSignupJWT.repository.services.AuthenticationService;
import com.loginSignupJWT.repository.services.impl.JWTServiceImpl;
import com.loginSignupJWT.utils.CustomResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;
    private final JWTServiceImpl jwtService;

    @PostMapping("/register")
    public ResponseEntity<CustomResponse<User>> adminRegister(@RequestBody SignUpRequest signUpRequest) {
        User user = authenticationService.signup(signUpRequest, "admin");
        return CustomResponseUtil.createSuccessResponse(user, "Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse<JwtAuthenticationResponse>> adminLogin(@RequestBody SigninRequest signinRequest) {
        try {
            JwtAuthenticationResponse jwtResponse = authenticationService.signin(signinRequest, Role.ADMIN);
            return CustomResponseUtil.createTokenResponse(jwtResponse, "Login successful", jwtResponse.getToken());
        } catch (BadCredentialsException e) {
            JwtAuthenticationResponse emptyResponse = new JwtAuthenticationResponse();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomResponse<>("Unauthorized access", emptyResponse));
        }
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi Admin");
    }



}
