package com.server.elearning.controller;

import com.server.elearning.dto.ResponseDTO;
import com.server.elearning.model.Content;
import com.server.elearning.model.Course;
import com.server.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public ResponseEntity<ResponseDTO> getCourseList() {
        try {
            List<Course> courseList = courseService.getAllCourses();
            ResponseDTO courseResponse = new ResponseDTO();
            courseResponse.setMessage("Fetched coursed successfully.");
            courseResponse.setStatus("Success");
            courseResponse.setData(courseList);
            courseResponse.setSuccess(true);
            return ResponseEntity.ok().body(courseResponse);
        } catch (RuntimeException e) {
            logger.debug("error: " + e);
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setStatus("Error");
            errorResponse.setMessage("Failed to retrieve courses: " + e);
            errorResponse.setSuccess(false);
            errorResponse.setData(Collections.emptyList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PostMapping()
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        try {
            courseService.addCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course Added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
