package com.bartek.jpademo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Course(String name) {
        this.name = name;
    }
}
