package com.kenny.sdeappbackend.auth;


import com.kenny.sdeappbackend.enums.Department;
import com.kenny.sdeappbackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private Role role;
    private Department department;
}