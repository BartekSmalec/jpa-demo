package com.bartek.jpademo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String rating;

    private String desc;

    @ManyToOne
    private Course course;

    public Review(String rating, String desc) {
        this.rating = rating;
        this.desc = desc;
    }
}
