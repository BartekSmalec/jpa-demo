package com.bartek.jpademo.repositories;

import com.bartek.jpademo.JpaDemoApplication;
import com.bartek.jpademo.entity.Passport;
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

    //Session & Session Factory
    //EntityManager & Persistence Context
    //Transaction

    @Autowired
    EntityManager em;

    @Test
    void testToUnderstandPersistenceContext() {
        // Transactional on repository level
        studentRepository.someOperationToUnderstandPersistenceContext();
    }

    @Test
    @Transactional
    void someTest() {
        //Database Operation 1 - Retrieve student
        Student student = em.find(Student.class, 20001L);
        //Persistence context (student)

        //Database Operation 2 - Retrieve passport
        Passport passport = student.getPassport();
        //Persistence context (student, passport)

        //Database Operation 3 - update passport
        passport.setNumber("E123456");
        //Persistence context (student, passport++)

        //Database Operation 4 - update student
        student.setName("Ranga - updated");
        //Persistence context (student++, passport++)
    }

    @Test
    @Transactional
    void retrieveStudentAndPassportTest() {
        Student student = em.find(Student.class, 20001L);
        log.info("Student -> {}", student);
        log.info("Passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    void retrievePassportAndAssociatedStudentTest() {
        Passport passport = em.find(Passport.class, 40001L);
        log.info("Passport -> {}", passport);
        log.info("Student -> {}", passport.getStudent());
    }
}