package com.kenny.sdeappbackend.dto;

import com.kenny.sdeappbackend.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String title;
    private String description;
    private Priority priority;
    private LocalDateTime deadline;
    private List<String> tags;
    private List<UserIdWrapper> assignees;
}