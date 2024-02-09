package com.server.elearning.service;

import com.server.elearning.dto.ContentDTO;
import com.server.elearning.model.Content;
import com.server.elearning.model.Course;
import com.server.elearning.model.User;
import com.server.elearning.repository.ContentRepository;
import com.server.elearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void addVideoCourseContent(ContentDTO contentDTO) {
        if (StringUtils.isEmpty(contentDTO.getLink())) {
            throw new RuntimeException("Please course content details!");
        }
        Course course = courseRepository.findActiveCourseById(contentDTO.getCourseId());
        if (course == null) throw new RuntimeException("Course not found!");
        Content content = new Content();
        content.setLink(contentDTO.getLink());
        content.setCourse(course);
        content.setTitle(contentDTO.getTitle());
        content.setDescription(contentDTO.getDescription());
        contentRepository.save(content);
    }

    public List<Content> getContentByCourseId(Long courseId) {
        return  contentRepository.findByCourseId(courseId);
    }
}
