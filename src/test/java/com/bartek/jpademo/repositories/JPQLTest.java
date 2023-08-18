package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Course;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JpaDemoApplication.class)
@Slf4j
class JPQLTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void jpql_basic() {
        List resultList = entityManager.createQuery("Select c from Course c").getResultList();
        log.info("Select c from Course c -> {}", resultList);
    }

    @Test
    void jpql_typed() {
        List<Course> resultList = entityManager.createQuery("Select c from Course c", Course.class).getResultList();
        log.info("Select c from Course c -> {}", resultList);
    }

    @Test
    void jpql_where() {
        List<Course> resultList = entityManager.createQuery("Select c from Course c where name like '%100 Steps'", Course.class).getResultList();
        log.info("Select c from Course c where name like '%100 Steps'-> {}", resultList);
    }
}