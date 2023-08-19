package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JpaDemoApplication.class)
@Slf4j
class NativeQueriesTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void native_queries_basic() {
        List resultList = entityManager.createNativeQuery("select * from course", Course.class).getResultList();
        log.info("select * from course_details-> {}", resultList);
    }

    @Test
    void native_queries_with_parameter() {
        Query query = entityManager.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 10001L);

        List resultList = query.getResultList();
        log.info("select * from course_details where id = :id - > {}", resultList);
    }
}