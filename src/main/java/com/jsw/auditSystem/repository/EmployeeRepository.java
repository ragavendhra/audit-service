package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
