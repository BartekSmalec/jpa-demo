package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Course;
import com.bartek.jpademo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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

    @Test
    void nameQuery_basic() {
        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List result = query.getResultList();
        log.info("Named query 'query_get_all_courses' -> {}", result);
    }

    @Test
    void namedQuery_second() {
        List<Course> resultList = entityManager.createNamedQuery("query_get_100_steps_courses", Course.class).getResultList();
        log.info("Select c from Course c where name like '%100 Steps'-> {}", resultList);
    }

    @Test
    @Transactional
    void findAllCoursesWithoutStudent() {
        List<Course> resultList = entityManager.createQuery("Select c from Course c where c.students is empty ", Course.class).getResultList();
        log.info("Courses without -> {}", resultList);
    }

    @Test
    @Transactional
    void findAllCoursesOrderByStudents() {
        List<Course> resultList = entityManager.createQuery("Select c from Course c order by size(c.students) desc ", Course.class).getResultList();
        log.info("Courses order by students desc -> {}", resultList);
    }

    @Test
    @Transactional
    void findAllStudentsWithPassportInACertainPattern() {
        List<Student> resultList = entityManager.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class).getResultList();
        log.info("Students with passport with a given pattern -> {}", resultList);
    }

    // like, BETWEEN 100 and 500, IS NULL, upper, lower, trim, length

    //JOIN => Select c, s from Course c JOIN c.students s
    //LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
    //CROSS JOIN => Select c, s from Course c, Student s
    @Test
    @Transactional
    public void join(){
        Query query = entityManager.createQuery("select c,s from Course c join c.students s");
        List<Object[]> resltList = query.getResultList();
        log.info("Results Size -> {}", resltList.size());

        for (Object[] result : resltList) {
            log.info("Course -> {} ", result[0]);
            log.info("Student -> {} ", result[1]);
        }
    }

    @Test
    @Transactional
    public void left_join(){
        Query query = entityManager.createQuery("select c,s from Course c left join c.students s");
        List<Object[]> resltList = query.getResultList();
        log.info("Results Size -> {}", resltList.size());

        for (Object[] result : resltList) {
            log.info("Course -> {} ", result[0]);
            log.info("Student -> {} ", result[1]);
        }
    }

    @Test
    @Transactional
    public void cross_join(){
        Query query = entityManager.createQuery("select c,s from Course c,Student s");
        List<Object[]> resltList = query.getResultList();
        log.info("Results Size -> {}", resltList.size());

        for (Object[] result : resltList) {
            log.info("Course -> {} ", result[0]);
            log.info("Student -> {} ", result[1]);
        }
    }

}