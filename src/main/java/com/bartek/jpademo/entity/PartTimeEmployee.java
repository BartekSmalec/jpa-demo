package com.bartek.jpademo.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PartTimeEmployee extends Employee{

    private BigDecimal hourlyWage;
    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }
}
