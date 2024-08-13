package com.kenny.sdeappbackend.repo;

import com.kenny.sdeappbackend.enums.Role;
import com.kenny.sdeappbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    List<User> findByNameInAndRole(List<String> names, Role role);
    List<User> findByRole(Role role);
    List<User> findByIdIn(List<Long> ids);
}
