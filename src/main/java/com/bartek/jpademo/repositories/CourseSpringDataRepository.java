package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameAndId(String name, Long id);

    List<Course> findByName(String name);

    List<Course> countByName(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    List<Course> deleteByName(String name);

    @Query("Select c from Course c where name like '%100 Steps'")
    List<Course> coursesWith100StepsInName();

    @Query(name = "query_get_100_steps_courses")
    List<Course> courseWith100InNameUsingNameQuery();
}
