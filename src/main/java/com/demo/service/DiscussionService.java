package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Course;
import com.demo.entity.Discussion;
import com.demo.repository.DiscussionRepository;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    public List<Discussion> getDiscussionsCourse(Course course) {
        return discussionRepository.findByCourse(course);
    }
    public Discussion createDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }
}
