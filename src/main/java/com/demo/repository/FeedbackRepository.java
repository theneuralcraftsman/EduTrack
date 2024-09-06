package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

	
}
