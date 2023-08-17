package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@AllArgsConstructor
public class CourseRepository {

    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }
}
