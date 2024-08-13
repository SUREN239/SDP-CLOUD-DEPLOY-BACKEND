package com.kenny.sdeappbackend.controller;

import com.kenny.sdeappbackend.dto.UserDTO;
import com.kenny.sdeappbackend.enums.Role;
import com.kenny.sdeappbackend.model.User;
import com.kenny.sdeappbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<User> getUsers()
    {
        return userRepo.findAll();
    }

    @GetMapping("/staff")
    public List<UserDTO> getStaffUsers() {
        return userRepo.findByRole(Role.USER)
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getName()))
                .collect(Collectors.toList());
    }



}
