package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Passport;
import com.bartek.jpademo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@AllArgsConstructor
@Slf4j
public class StudentRepository {

    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void someOperationToUnderstandPersistenceContext(){
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
}
