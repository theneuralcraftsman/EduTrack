package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.DiscussionRequest;
import com.demo.entity.Course;
import com.demo.entity.Discussion;
import com.demo.service.CourseService;
import com.demo.service.DiscussionService;



@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;
    
    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<List<Discussion>> getDiscussionsAndCourse(
            @PathVariable Long courseId
    ) {
        Course course = courseService.getCourseById(courseId);
        List<Discussion> discussions = discussionService.getDiscussionsCourse(course);
        return ResponseEntity.ok(discussions);
    }
    @PostMapping("/addMessage")
    public ResponseEntity<Discussion> createDiscussion(
            @RequestBody DiscussionRequest discussionRequest
    ) {
    	System.out.println(discussionRequest.getCourse_id() +" "+discussionRequest.getName()+" "+discussionRequest.getContent());
        Course course = courseService.getCourseById(discussionRequest.getCourse_id());
        Discussion discussion = new Discussion();
        discussion.setuName(discussionRequest.getName());
        discussion.setCourse(course);
        discussion.setContent(discussionRequest.getContent());
        Discussion createdDiscussion = discussionService.createDiscussion(discussion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscussion);
    }
}
