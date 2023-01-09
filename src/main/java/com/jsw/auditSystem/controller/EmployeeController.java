package com.jsw.auditSystem.controller;

import com.jsw.auditSystem.model.Employee;
import com.jsw.auditSystem.model.Logger;
import com.jsw.auditSystem.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    //@Logger("Employee viewed.")
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
    }

    @Logger("Employee created.")
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @Logger("Employee updated.")
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    //soft delete
    @Logger("Employee deleted.")
    @DeleteMapping("/employees/{id}")
    Employee deleteEmployee(@PathVariable Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        employee.setDeleted();
        return repository.save(employee);
    }
}
