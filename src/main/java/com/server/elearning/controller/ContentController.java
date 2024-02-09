package com.server.elearning.controller;

import com.server.elearning.dto.ContentDTO;
import com.server.elearning.dto.ResponseDTO;
import com.server.elearning.model.Content;
import com.server.elearning.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/content")
@ControllerAdvice
public class ContentController {
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ResponseDTO> getContentByCourseId(@PathVariable Long courseId) {
        try {
            List<Content> contentList = contentService.getContentByCourseId(courseId);
            ResponseDTO contentResponse = new ResponseDTO();
            contentResponse.setMessage("Fetched content successfully.");
            contentResponse.setStatus("Success");
            contentResponse.setData(contentList);
            return ResponseEntity.ok().body(contentResponse);
        } catch (RuntimeException e) {
            logger.debug("error: " + e);
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setStatus("Error");
            errorResponse.setMessage("Failed to retrieve content: " + e);
            errorResponse.setData(Collections.emptyList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping()
    public ResponseEntity<String> loginUser(@RequestBody ContentDTO content) {
        try {
            contentService.addVideoCourseContent(content);
            return ResponseEntity.status(HttpStatus.CREATED).body("Content Added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
