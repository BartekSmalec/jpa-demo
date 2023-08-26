package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Course;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaDemoApplication.class)
@Slf4j
class PerformanceTuningTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    void creatinNPlusOneProblem() {
        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        courses.stream().forEach( c -> {
            log.info("Course {} -> Students -> {}", c, c.getStudents());
                }
        );
    }


    @Test
    @Transactional
    void solvingNPlusOneProblemEntityGraph() {
        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();
        courses.forEach(c -> {
                    log.info("Course {} -> Students -> {}", c, c.getStudents());
                }
        );
    }

    @Test
    @Transactional
    void solvingNPlusProblemJoinFetch() {
        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();

        courses.forEach(c -> {
                    log.info("Course {} -> Students -> {}", c, c.getStudents());
                }
        );
    }
}