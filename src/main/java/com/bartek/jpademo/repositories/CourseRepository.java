package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Course;
import com.bartek.jpademo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
@Slf4j
public class CourseRepository {

    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
        log.info("playWithEntityManager - start");

        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);
        em.flush();

        course1.setName("Web services in 100 steps - Updated");
        //em.flush();

        // To refresh course1 with a values from database, it will revert course name to old value
        em.refresh(course1);

        // em.clear() // Will detach all from an entity manger course1 and course2

        Course course2 = new Course("AngularJS in 100 Steps");
        em.persist(course2);
        em.flush();

        em.detach(course2); // Will detach course 2 from an entity manager

        course2.setName("AngularJS in 100 Steps - Updated");
        em.flush();
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);

        log.info("course.getReviews() -> {}", course.getReviews());
        reviews.forEach(r -> addReview(course, r));

    }

    public void addReview(Course course, Review review) {
        review.setCourse(course);
        course.addReview(review);
        em.persist(review);
    }
}
