package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JpaDemoApplication.class)
@Slf4j
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void retrieveStudentAndPassportTest() {
        Student student = em.find(Student.class, 20001L);
        log.info("Student -> {}", student);
        log.info("Passport -> {}", student.getPassport());
    }
}