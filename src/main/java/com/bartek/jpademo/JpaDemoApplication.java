package com.bartek.jpademo;

import com.bartek.jpademo.entity.Course;
import com.bartek.jpademo.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class JpaDemoApplication implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        long id = 10001L;
        Course course = courseRepository.findById(id);
        log.info("Course 1001 is: {}", course);
        log.info("Course save: {}", courseRepository.save(new Course(null, "Microservices in 50 steps")));
    }
}
