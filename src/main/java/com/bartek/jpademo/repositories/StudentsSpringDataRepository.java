package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "students")
public interface StudentsSpringDataRepository extends JpaRepository<Student, Long> {

}
