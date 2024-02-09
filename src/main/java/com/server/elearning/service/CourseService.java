package com.server.elearning.service;

import com.server.elearning.dto.ContentDTO;
import com.server.elearning.model.Content;
import com.server.elearning.model.Course;
import com.server.elearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public void addCourse(Course course) {
        if (StringUtils.isEmpty(course.getTitle())) {
            throw new RuntimeException("Please provide course title!");
        }
        courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return  courseRepository.findAll();
    }
}
