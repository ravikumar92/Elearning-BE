package com.server.elearning.repository;

import com.server.elearning.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.id = ?1 and c.isActive = true")
    Course findActiveCourseById(Long id);
}
