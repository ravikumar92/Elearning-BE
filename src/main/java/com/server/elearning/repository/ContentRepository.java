package com.server.elearning.repository;

import com.server.elearning.model.Content;
import com.server.elearning.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository  extends JpaRepository<Content, Long> {
    @Query("select c from Content c where c.course.id = ?1 and c.isActive = true")
    List<Content> findByCourseId(Long courseId);
}
