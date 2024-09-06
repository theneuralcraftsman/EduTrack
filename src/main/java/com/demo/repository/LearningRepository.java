package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Course;
import com.demo.entity.Learning;
import com.demo.entity.User;

public interface LearningRepository extends JpaRepository<Learning, Long>{

	Learning findByUserAndCourse(User user, Course course);
}
