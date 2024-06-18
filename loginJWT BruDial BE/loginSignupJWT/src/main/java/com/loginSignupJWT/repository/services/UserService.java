package com.loginSignupJWT.repository.services;

import com.loginSignupJWT.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    User updateUser(User user);
}
