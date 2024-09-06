package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Course;
import com.demo.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public Course searchCoursesByName(String course_name) {
        return courseRepository.findByCourseName(course_name);
    }

public Course updateCourse(Long id, Course updatedCourse) {
    Course existingCourse = courseRepository.findById(id).orElse(null);
    if (existingCourse != null) {
        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setPhoto(updatedCourse.getPhoto());
        existingCourse.setPrice(updatedCourse.getPrice());
        existingCourse.setTutor(updatedCourse.getTutor());
        existingCourse.setVideo(updatedCourse.getVideo());
        return courseRepository.save(existingCourse);
    }
    return null;
}

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}
}