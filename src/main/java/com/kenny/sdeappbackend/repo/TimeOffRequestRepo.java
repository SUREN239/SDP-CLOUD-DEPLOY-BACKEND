package com.kenny.sdeappbackend.repo;

import com.kenny.sdeappbackend.model.TimeOffRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TimeOffRequestRepo extends JpaRepository<TimeOffRequest,Long> {
}
