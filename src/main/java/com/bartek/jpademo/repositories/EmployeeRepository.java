package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Employee;
import com.bartek.jpademo.entity.FullTimeEmployee;
import com.bartek.jpademo.entity.PartTimeEmployee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
@Slf4j
public class EmployeeRepository {

    EntityManager em;

    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

    public void deleteById(Long id) {
        Employee Employee = findById(id);
        em.remove(Employee);
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
        return employee;
    }
}
