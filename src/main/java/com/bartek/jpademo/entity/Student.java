package com.bartek.jpademo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToOne
    private Passport passport;

    public Student(String name) {
        this.name = name;
    }
}
