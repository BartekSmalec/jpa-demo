package com.bartek.jpademo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "CourseDetails")
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fullname", nullable = false)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    @CreationTimestamp
    private LocalDateTime createdDate;

    public Course(String name) {
        this.name = name;
    }
}
