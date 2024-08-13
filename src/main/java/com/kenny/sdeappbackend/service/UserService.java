package com.kenny.sdeappbackend.service;

import com.kenny.sdeappbackend.auth.AuthenticationFacade;
import com.kenny.sdeappbackend.model.User;
import com.kenny.sdeappbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public User getCurrentUser() {
        String email = authenticationFacade.getAuthentication().getName();
        return userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

}
