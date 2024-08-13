package com.kenny.sdeappbackend.controller;

import com.kenny.sdeappbackend.dto.EventRequest;
import com.kenny.sdeappbackend.dto.UserIdWrapper;
import com.kenny.sdeappbackend.model.Event;
import com.kenny.sdeappbackend.model.User;
import com.kenny.sdeappbackend.service.EventService;
import com.kenny.sdeappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }


    @PostMapping
    public Event createTask(@RequestBody EventRequest taskRequest) {
        List<User> users = new ArrayList<>();
        for (UserIdWrapper userIdWrapper : taskRequest.getAssignees()) {
            userService.getUserById(userIdWrapper.getId()).ifPresent(users::add);
        }

        // Get the current logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Event task = Event.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .priority(taskRequest.getPriority())
                .deadline(taskRequest.getDeadline())
                .tags(taskRequest.getTags())
                .start(LocalDateTime.now()) // Set start time as current time
                .end(taskRequest.getDeadline().withHour(17).withMinute(0).withSecond(0)) // Set end time to 5:00 PM of deadline
                .createdBy(currentUsername) // Set createdBy from SecurityContextHolder
                .isPublic(true) // Default to true
                .userList(users)
                .build();

        // Save the task
        Event savedTask = eventService.saveTask(task);

        // Update users to reference the saved Task
        for (User user : users) {
            user.setEvent(savedTask);
            userService.saveUser(user);
        }

        return savedTask;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
