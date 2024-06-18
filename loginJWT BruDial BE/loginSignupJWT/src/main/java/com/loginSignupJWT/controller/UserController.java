package com.loginSignupJWT.controller;

import com.loginSignupJWT.dto.JwtAuthenticationResponse;
import com.loginSignupJWT.dto.SigninRequest;
import com.loginSignupJWT.dto.UserDTO;
import com.loginSignupJWT.entities.CustomResponse;
import com.loginSignupJWT.entities.Role;
import com.loginSignupJWT.repository.UserRepository;
import com.loginSignupJWT.repository.services.AuthenticationService;
import com.loginSignupJWT.repository.services.impl.JWTServiceImpl;
import com.loginSignupJWT.utils.CustomResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

//    @GetMapping("/home")
//    public ResponseEntity<UserDetailsDTO> getUserDetails() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
//
//        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user.getFirstname(), user.getSecondname(), user.getEmail());
//        return ResponseEntity.ok(userDetailsDTO);
//    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
    }

    private final AuthenticationService authenticationService;
    private final JWTServiceImpl jwtService;



    @PostMapping("/login")
    public ResponseEntity<CustomResponse<JwtAuthenticationResponse>> userLogin(@RequestBody SigninRequest signinRequest) {
        try {
            JwtAuthenticationResponse jwtResponse = authenticationService.signin(signinRequest, Role.USER);
            return CustomResponseUtil.createTokenResponse(jwtResponse, "Login successful", jwtResponse.getToken());
        } catch (BadCredentialsException e) {
            JwtAuthenticationResponse emptyResponse = new JwtAuthenticationResponse();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomResponse<>("Unauthorized access", emptyResponse));
        }
    }

    @GetMapping("/home")
    public ResponseEntity<CustomResponse<UserDTO>> getUserDetails(@RequestHeader("Authorization") String token) {
        // Extract the JWT token from the Authorization header
        String jwtToken = token.substring(7); // Remove "Bearer " prefix

        // Extract user details from the JWT token
        String userEmail = jwtService.extractUserName(jwtToken);

        // Retrieve user details from the authentication service
        UserDTO userDTO = authenticationService.getUserDetails(userEmail);

        // Prepare the response
        CustomResponse<UserDTO> response = new CustomResponse<>();
        response.setData(userDTO);
        response.setMessage("User details retrieved successfully");
        response.setStatus("success");

        return ResponseEntity.ok(response);
    }

}
