package com.server.elearning.controller;


import com.server.elearning.dto.LoginResponseDTO;
import com.server.elearning.dto.ResponseDTO;
import com.server.elearning.model.User;
import com.server.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody User user) {
        try {
            userService.authenticateUser(user);
            LoginResponseDTO loggedInResponse = new LoginResponseDTO();
            loggedInResponse.setMessage("User loggedIn successfully");
            loggedInResponse.setStatus("Success");
            loggedInResponse.setSuccess(true);
            loggedInResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK).body(loggedInResponse);
        } catch (RuntimeException e) {
            LoginResponseDTO errorResponse = new LoginResponseDTO();
            errorResponse.setStatus("Error");
            errorResponse.setMessage(e.getMessage());
            errorResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            LoginResponseDTO loggedInResponse = new LoginResponseDTO();
            loggedInResponse.setMessage("User registered successfullyy");
            loggedInResponse.setStatus("Success");
            loggedInResponse.setSuccess(true);
            loggedInResponse.setToken("");
            return ResponseEntity.status(HttpStatus.CREATED).body(loggedInResponse);
        } catch (RuntimeException e) {
            LoginResponseDTO errorResponse = new LoginResponseDTO();
            errorResponse.setStatus("Error");
            errorResponse.setMessage(e.getMessage());
            errorResponse.setSuccess(false);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
    }

}
