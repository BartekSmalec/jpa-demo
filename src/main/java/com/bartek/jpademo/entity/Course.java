package com.bartek.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Cacheable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "course")
@NamedQuery(name = "query_get_all_courses", query = "Select c from Course c")
@NamedQuery(name = "query_get_all_courses_join_fetch", query = "Select c from Course c join fetch c.students")
@NamedQuery(name = "query_get_100_steps_courses", query = "Select c from Course c where name like '%100 Steps'")
@Cacheable
@SQLDelete(sql = "update Course set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
@Slf4j
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    public Course(String name) {
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReviews(Review review) {
        this.reviews.remove(review);
    }

    public void addStudent(Student students) {
        this.students.add(students);
    }

    @PreRemove
    private void preRemove(){
        log.info("Setting is deleted to true");
        this.isDeleted = true;
    }
}
