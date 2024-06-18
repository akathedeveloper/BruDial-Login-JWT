package com.loginSignupJWT.controller;

import com.loginSignupJWT.dto.JwtAuthenticationResponse;
import com.loginSignupJWT.dto.SigninRequest;
import com.loginSignupJWT.dto.UserDTO;
import com.loginSignupJWT.entities.CustomResponse;
import com.loginSignupJWT.entities.Role;
import com.loginSignupJWT.entities.User;
import com.loginSignupJWT.repository.UserRepository;
import com.loginSignupJWT.repository.services.AuthenticationService;
import com.loginSignupJWT.repository.services.UserService;
import com.loginSignupJWT.repository.services.impl.JWTServiceImpl;
import com.loginSignupJWT.utils.CustomResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
    }

    private final AuthenticationService authenticationService;
    private final JWTServiceImpl jwtService;

    @Autowired
    private UserService userService;



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
        String userEmail = jwtService.extractUserName(jwtToken);

        UserDTO userDTO = authenticationService.getUserDetails(userEmail);

        CustomResponse<UserDTO> response = new CustomResponse<>();
        response.setData(userDTO);
        response.setMessage("User details retrieved successfully");
        response.setStatus("success");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomResponse<User>> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
        String jwtToken = token.substring(7);
        String userEmail = jwtService.extractUserName(jwtToken);
        User existingUser = userRepository.findByEmail(userEmail).orElse(null);
        if (existingUser == null) {
            return CustomResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Username not found","Please give correct credentials");
        }

        user.setId(existingUser.getId());
        try {
            User updatedUser = userService.updateUser(user);
            return CustomResponseUtil.createSuccessResponse(updatedUser, "User updated successfully");
        } catch (Exception e) {
            return CustomResponseUtil.createErrorResponse( HttpStatus.INTERNAL_SERVER_ERROR,"Exception occurred","User details can not be updated");
        }
    }



}
