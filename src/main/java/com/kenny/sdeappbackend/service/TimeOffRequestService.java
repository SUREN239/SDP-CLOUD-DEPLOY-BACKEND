package com.kenny.sdeappbackend.service;

import com.kenny.sdeappbackend.model.TimeOffRequest;
import com.kenny.sdeappbackend.model.User;
import com.kenny.sdeappbackend.repo.TimeOffRequestRepo;
import com.kenny.sdeappbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeOffRequestService {

    @Autowired
    private TimeOffRequestRepo timeOffRequestRepository;

    @Autowired
    private UserRepo userRepository;

    public TimeOffRequest submitRequest(TimeOffRequest request) {
        // Get the current logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Fetch the user from the database
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            request.setUser(user.get());
            return timeOffRequestRepository.save(request);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<TimeOffRequest> getAllTimeOffRequests() {
        return timeOffRequestRepository.findAll();
    }
}