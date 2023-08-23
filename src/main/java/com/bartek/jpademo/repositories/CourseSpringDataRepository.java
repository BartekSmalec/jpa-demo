package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
}
