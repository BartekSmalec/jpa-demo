package com.bartek.jpademo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    public Review(String rating, String desc) {
        this.rating = rating;
        this.desc = desc;
    }
}
