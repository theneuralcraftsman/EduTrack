package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Assessment;
import com.demo.entity.Course;
import com.demo.entity.User;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    List<Assessment> findByUserAndCourse(User user, Course course);

	List<Assessment> findByUser(User user);
}
