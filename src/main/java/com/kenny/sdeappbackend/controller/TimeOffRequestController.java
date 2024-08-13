package com.kenny.sdeappbackend.controller;

import com.kenny.sdeappbackend.model.TimeOffRequest;
import com.kenny.sdeappbackend.service.TimeOffRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/timeoff")
public class TimeOffRequestController {

    @Autowired
    private TimeOffRequestService service;

    @PostMapping("/submit")
    public ResponseEntity<TimeOffRequest> submitTimeOffRequest(@RequestBody Map<String, Object> requestBody) {
        TimeOffRequest request = new TimeOffRequest();
        request.setStartDateTime(LocalDateTime.parse((String) requestBody.get("startDateTime")));
        request.setEndDateTime(LocalDateTime.parse((String) requestBody.get("endDateTime")));
        request.setReason((String) requestBody.get("reason"));

        TimeOffRequest savedRequest = service.submitRequest(request);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TimeOffRequest>> getAllTimeOffRequests() {
        List<TimeOffRequest> requests = service.getAllTimeOffRequests();
        return ResponseEntity.ok(requests);
    }
}
