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
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Department department;
    private Role role;
}
