package com.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.FeedbackRequest;
import com.demo.entity.Course;
import com.demo.entity.Feedback;
import com.demo.repository.CourseRepository;
import com.demo.repository.FeedbackRepository;


@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;


    @Autowired
    private CourseRepository courseRepository;

    public List<Feedback> getFeedbacksForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            return course.getFeedbacks();
        }
        return null;
    }

    public String submitFeedback(FeedbackRequest fr) {
        Course course = courseRepository.findById(fr.getCourse_id()).orElse(null);
        Feedback feedback = new Feedback();

        if (course != null) {
            feedback.setCourse(course);
            feedback.setComment(fr.getComment());
            feedbackRepository.save(feedback);
            return "feedback submitted successfully";
        }
        return "feedback submition failed";
    }
}
