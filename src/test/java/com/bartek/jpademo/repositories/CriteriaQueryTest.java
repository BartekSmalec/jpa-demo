package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JpaDemoApplication.class)
@Slf4j
class CriteriaQueryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void criteria_query_basic() {
        // "Select c from Course c"

        // 1. User criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define root for tables which are invoclcev in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define predicate

        // 4. Add predicated to criteria query

        // 5 Build the TypedQuery using entity manager and cb

        List<Course> resultList = entityManager.createQuery(cq.select(courseRoot)).getResultList();
        log.info("Typed query -> {}", resultList);
    }

    @Test
    void all_courses_having_100steps() {
        // "Select c from Course c"

        // 1. User criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define root for tables which are invoclcev in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define predicate

        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

        // 4. Add predicated to criteria query

        cq.where(like100Steps);

        // 5 Build the TypedQuery using entity manager and cb

        List<Course> resultList = entityManager.createQuery(cq.select(courseRoot)).getResultList();
        log.info("Typed query -> {}", resultList);
    }

    @Test
    @Transactional
    void all_courses_without_students() {
        // "Select c from Course c"

        // 1. User criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define root for tables which are invoclcev in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define predicate

        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        // 4. Add predicated to criteria query

        cq.where(studentsIsEmpty);

        // 5 Build the TypedQuery using entity manager and cb

        List<Course> resultList = entityManager.createQuery(cq.select(courseRoot)).getResultList();
        log.info("Typed query -> {}", resultList);
    }

    @Test
    @Transactional
    void join() {
        // "Select c from Course c"

        // 1. User criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define root for tables which are invoclcev in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define join

        Join<Object, Object> joined = courseRoot.join("students");

        // 4. Add predicated to criteria query


        // 5 Build the TypedQuery using entity manager and cb

        List<Course> resultList = entityManager.createQuery(cq.select(courseRoot)).getResultList();
        log.info("Typed query -> {}", resultList);
    }

    @Test
    @Transactional
    void left_join() {
        // "Select c from Course c"

        // 1. User criteria builder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define root for tables which are invoclcev in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define join

        Join<Object, Object> joined = courseRoot.join("students", JoinType.LEFT);

        // 4. Add predicated to criteria query


        // 5 Build the TypedQuery using entity manager and cb

        List<Course> resultList = entityManager.createQuery(cq.select(courseRoot)).getResultList();
        log.info("Typed query -> {}", resultList);
    }
}