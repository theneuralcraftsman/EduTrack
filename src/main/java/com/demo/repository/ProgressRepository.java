package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Course;
import com.demo.entity.Progress;
import com.demo.entity.User;

public interface ProgressRepository extends JpaRepository<Progress, Long>{

	Progress findByUserAndCourse(User user, Course course);
}
