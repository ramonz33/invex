package com.api.invex.repository;

import com.api.invex.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface employeeRepository extends JpaRepository<employee, Long> {
    List<employee> findByFirstNameContainingIgnoreCase(String name);
}
