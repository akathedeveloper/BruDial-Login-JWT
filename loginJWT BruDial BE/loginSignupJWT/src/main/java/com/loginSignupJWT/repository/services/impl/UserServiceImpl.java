package com.loginSignupJWT.repository.services.impl;


import com.loginSignupJWT.entities.User;
import com.loginSignupJWT.repository.UserRepository;
import com.loginSignupJWT.repository.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username){
                return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public User updateUser(User user) {
        if(user.getId() == null){
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(()-> new RuntimeException("User not Found"));
        if (user.getFirstname() != null) {
            existingUser.setFirstname(user.getFirstname());
        }
        if (user.getSecondname() != null) {
            existingUser.setSecondname(user.getSecondname());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }

        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }


        if (user.getProfilePicture() != null) {
            existingUser.setProfilePicture(user.getProfilePicture());
        }
        if (user.getCountry() != null) {
            existingUser.setCountry(user.getCountry());
        }

        return userRepository.save(existingUser);

    }
}
