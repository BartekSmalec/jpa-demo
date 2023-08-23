package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = JpaDemoApplication.class)
@Slf4j
class CourseSpringDataRepositoryTest {

    @Autowired
    CourseSpringDataRepository courseSpringDataRepository;

    @Test
    void findById_CoursePresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
        boolean isPresent = courseOptional.isPresent();
        assertTrue(isPresent);
        log.info("Course optional is present: {} ", isPresent);
    }

    @Test
    void findById_CourseNotPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(20001L);
        boolean isPresent = courseOptional.isPresent();
        assertFalse(isPresent);
        log.info("Course optional is present: {} ", isPresent);
    }

    @Test
    @Transactional
    void playingWIthSpringDataRepository() {
        Course course = new Course("Microservices in 100 steps");
        courseSpringDataRepository.save(course);

        course.setName("Microservices in 100 steps - Updated");
        courseSpringDataRepository.save(course);

        log.info("Courses -> {}, Count -> {}", courseSpringDataRepository.findAll(), courseSpringDataRepository.count());
    }

    @Test
    @Transactional
    void sort() {

        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        log.info("Sorted courses -> {}", courseSpringDataRepository.findAll(sort));
        log.info("Count -> {}", courseSpringDataRepository.count());
    }

    @Test
    @Transactional
    void pagination() {
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
        log.info("First page -> {}", firstPage.getContent());

        Pageable second = pageRequest.next();
        Page<Course> secondPage = courseSpringDataRepository.findAll(second);
        log.info("Second page -> {}", secondPage.getContent());
    }
}