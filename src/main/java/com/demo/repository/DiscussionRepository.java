package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Course;
import com.demo.entity.Discussion;

public interface DiscussionRepository extends JpaRepository<Discussion, Long>{

	List<Discussion> findByCourse(Course course);
}
