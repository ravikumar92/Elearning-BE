package com.server.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.server.elearning")
public class ELearningApplication {
	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}
}
