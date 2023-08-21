package com.bartek.jpademo.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@ToString
public class FullTimeEmployee extends Employee {
    BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}
