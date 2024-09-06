package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.EnrollRequest;
import com.demo.entity.Course;
import com.demo.entity.Learning;
import com.demo.service.LearningService;

import java.util.List;

@RestController
@RequestMapping("/api/learning")
public class LearningController {

    @Autowired
    private LearningService learningService;

    @GetMapping("/{userId}")
    public List<Course> getLearningCourses(@PathVariable Long userId) {
        return learningService.getLearningCourses(userId);
    }
    
    @GetMapping
    public List<Learning> getEnrollments() {
        return learningService.getEnrollments();
    }

    @PostMapping
    public String enrollCourse(@RequestBody EnrollRequest enrollRequest) {
    	System.out.println(enrollRequest.getCourseId() +" = "+enrollRequest.getUserId());
        return learningService.enrollCourse(enrollRequest);
    }

    @DeleteMapping("/{id}")
    public void unenrollCourse(@PathVariable Long id) {
        learningService.unenrollCourse(id);
    }
}
