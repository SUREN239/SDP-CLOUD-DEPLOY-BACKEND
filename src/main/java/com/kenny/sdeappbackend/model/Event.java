package com.kenny.sdeappbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kenny.sdeappbackend.enums.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "event")
    @JsonManagedReference
    private List<User> userList;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime deadline;

    @ElementCollection
    private List<String> tags;

    private LocalDateTime start;
    private LocalDateTime end;

    private String createdBy;

    private boolean isPublic;
}

