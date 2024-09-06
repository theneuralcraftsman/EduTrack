package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ProgressRequest;
import com.demo.service.ProgressService;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(allowCredentials = "")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping("/{userId}/{courseId}")
    public float getProgress(@PathVariable Long userId, @PathVariable Long courseId) {
        return progressService.getProgress(userId, courseId);
    }

    @PutMapping("/update-progress")
    public ResponseEntity<String> updateProgress(@RequestBody ProgressRequest request) {
        return progressService.updateProgress(request);
    }
    
    @PutMapping("/update-duration")
    public ResponseEntity<String> updateDuration(@RequestBody ProgressRequest request) {
        return progressService.updateDuration(request);
    }
}