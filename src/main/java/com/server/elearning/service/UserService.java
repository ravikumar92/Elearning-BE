package com.server.elearning.service;

import com.server.elearning.expection.UserNotFoundException;
import com.server.elearning.model.User;
import com.server.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void authenticateUser(User loggedInUser) {
        // Retrieve user by email from the database
        User user = userRepository.findByEmail(loggedInUser.getEmail());
        // Check if user exists
        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }
        // Check if the provided password matches the stored hashed password
        if (!passwordEncoder.matches(loggedInUser.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        //Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        //String jwtToken = jwtProvider.generateToken(authentication);
        //return jwtToken;
    }
    public void registerUser(User user) {
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new RuntimeException("Please provide email address");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new RuntimeException("Please provide password number");
        }
        if (StringUtils.isEmpty(user.getName())) {
            throw new RuntimeException("Please provide name number");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User Already Exists!");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
