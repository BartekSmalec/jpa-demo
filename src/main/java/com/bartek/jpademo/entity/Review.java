package com.bartek.jpademo.entity;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.ORDINAL)
    private ReviewRating rating;

    private String desc;

    @ManyToOne
    private Course course;

    public Review(ReviewRating rating, String desc) {
        this.rating = rating;
        this.desc = desc;
    }
}
