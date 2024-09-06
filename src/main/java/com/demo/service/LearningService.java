package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.EnrollRequest;
import com.demo.entity.Course;
import com.demo.entity.Learning;
import com.demo.entity.Progress;
import com.demo.entity.User;
import com.demo.repository.CourseRepository;
import com.demo.repository.LearningRepository;
import com.demo.repository.ProgressRepository;
import com.demo.repository.UserRepository;
@Service
public class LearningService {

    @Autowired
    private LearningRepository learningRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private ProgressRepository progressRepository;

    public List<Course> getLearningCourses(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Course> learningCourses = new ArrayList<>();

            for (Learning learning : user.getLearningCourses()) {
                Course course = learning.getCourse();
                learningCourses.add(course);
            }

            return learningCourses;
        }

        return null;
    }
    
    public List<Learning> getEnrollments() {
    	return learningRepository.findAll();
    }

    public String enrollCourse(EnrollRequest enrollRequest) {
        User user = userRepository.findById(enrollRequest.getUserId()).orElse(null);
        Course course = courseRepository.findById(enrollRequest.getCourseId()).orElse(null);

        if (user != null && course != null) {
            Learning existingLearning = learningRepository.findByUserAndCourse(user, course);
            if (existingLearning != null) {
                return "Course already enrolled";
            }

            Progress progress = new Progress();
            progress.setUser(user);
            progress.setCourse(course);
            progressRepository.save(progress);

            Learning learning = new Learning();
            learning.setUser(user);
            learning.setCourse(course);
            learningRepository.save(learning);

            return "Enrolled successfully";
        }

        return "Failed to enroll";
    }


    public void unenrollCourse(Long id) {
        learningRepository.deleteById(id);
    }
}

