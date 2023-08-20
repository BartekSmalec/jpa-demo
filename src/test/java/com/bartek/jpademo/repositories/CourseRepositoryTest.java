package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Course;
import com.bartek.jpademo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaDemoApplication.class)
@Slf4j
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findById_basic() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
        log.info("Test is running");
    }

    @Test
    @DirtiesContext
    void deleteById_basic() {
        courseRepository.deleteById(10002L);
        assertNull(courseRepository.findById(10002L));
    }

    @Test
    void save_basic() {
        String courseTitle = "New course !!!";
        Course course = courseRepository.save(new Course(null, courseTitle));
        assertEquals(courseTitle, course.getName());
    }

    @Test
    @DirtiesContext
    void update_basic() {
        String courseTitle = "Spring Security in 50 steps";
        Course course = courseRepository.save(new Course(10002L, courseTitle));
        assertEquals(courseTitle, course.getName());
        assertEquals(10002L, course.getId());
    }

    @Test
    @DirtiesContext
    void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

    @Test
    @Transactional
    void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        //log.info("Reviews -> {}", course.getReviews());
    }

    @Test
    @Transactional
    void retrieveCourseForReview() {
        Review review = entityManager.find(Review.class, 50001L);
        log.info("Course -> {}", review.getCourse());
    }
}