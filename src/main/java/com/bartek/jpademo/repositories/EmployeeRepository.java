package com.bartek.jpademo.repositories;

import com.bartek.jpademo.entity.Employee;
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

    public List<Employee> retrieveAllEmployees() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
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
