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

import com.demo.dto.QuestionRequest;
import com.demo.entity.Course;
import com.demo.entity.Questions;
import com.demo.repository.CourseRepository;
import com.demo.repository.QuestionRepository;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository, CourseRepository courseRepository) {
        this.questionRepository = questionRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody QuestionRequest questionRequest) {
        Course course = courseRepository.findById(questionRequest.getCourseId()).orElse(null);

        Questions question = new Questions();
        question.setQuestion(questionRequest.getQuestion());
        question.setOption1(questionRequest.getOption1());
        question.setOption2(questionRequest.getOption2());
        question.setOption3(questionRequest.getOption3());
        question.setOption4(questionRequest.getOption4());
        question.setAnswer(questionRequest.getAnswer());
        question.setCourse(course);

        questionRepository.save(question);

        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }
    
    @GetMapping("/{courseId}")
    public ResponseEntity<List<Questions>> getAllQuestionsForCourse(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);

        if (course != null) {
            List<Questions> questions = questionRepository.findByCourse(course);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
