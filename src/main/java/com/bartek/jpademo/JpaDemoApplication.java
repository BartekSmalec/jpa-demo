package com.bartek.jpademo;

import com.bartek.jpademo.entity.*;
import com.bartek.jpademo.repositories.CourseRepository;
import com.bartek.jpademo.repositories.EmployeeRepository;
import com.bartek.jpademo.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class JpaDemoApplication implements CommandLineRunner {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
/*        long id = 10001L;
        Course course = courseRepository.findById(id);
        log.info("Course 1001 is: {}", course);
        log.info("Course save: {}", courseRepository.save(new Course(null, "Microservices in 50 steps")));*/

        //studentRepository.saveStudentWithPassport();

        //courseRepository.playWithEntityManager();


   /*     List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("5", "Great, I learned a lot"));
        reviews.add(new Review("4", "Great course it helped me getting my first job"));

        courseRepository.addReviewsForCourse(10003L, reviews);


        Student student = new Student("Jack");
        Course course = new Course("Microservices in 100 steps");

        studentRepository.insertStudentAndCourse(student, course);*/

        employeeRepository.save(new FullTimeEmployee("Jack", new BigDecimal(10000)));

        employeeRepository.save(new PartTimeEmployee("Jill", new BigDecimal(50)));

        log.info("All employees ->  {} ", employeeRepository.retrieveAllEmployees());
    }
}
