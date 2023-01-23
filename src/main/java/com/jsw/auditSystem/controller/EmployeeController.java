package com.jsw.auditSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsw.auditSystem.exceptions.UserInfoNotFoundException;
import com.jsw.auditSystem.model.Employee;
import com.jsw.auditSystem.model.EmployeeInfo;
import com.jsw.auditSystem.model.JsonEmployeeObject;
import com.jsw.auditSystem.model.Logger;
import com.jsw.auditSystem.repository.EmployeeInfoMangoRepository;
import com.jsw.auditSystem.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeInfoMangoRepository employeeInfoMangoRepository;

    EmployeeController(EmployeeRepository repository, EmployeeInfoMangoRepository employeeInfoMangoRepository) {
        this.repository = repository;
        this.employeeInfoMangoRepository = employeeInfoMangoRepository;
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
                .orElseThrow(() -> new UserInfoNotFoundException("User does not exist with this id :  " + id));

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

    @GetMapping("/employees/mango/{id}")
    List<JsonEmployeeObject> getByIdFromMango(@PathVariable String id) {
     List<EmployeeInfo> infoMap = employeeInfoMangoRepository.findByEmpId(id);

     //creating a list of only logElements
     List<Map<String, String>> listOfMaps = new ArrayList<>();

     //adding each logElement of respective employeeInfo object into listOfMaps list
     for(EmployeeInfo employeeInfo : infoMap){
         listOfMaps.add(employeeInfo.getLogElements());
     }
       /* ObjectMapper mapper = new ObjectMapper();
      List<Employee> li=   infoMap.stream().map(map1 -> {
                  return mapper.convertValue(map1, Employee.class);
              }
                ).collect(Collectors.toList());*/

        //mapping the object fields with the fields of the map(logElements)
        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonEmployeeObject> jsonEmployeeObjectsList = listOfMaps.stream().map(map1 ->
                objectMapper.convertValue(map1, JsonEmployeeObject.class)).collect(Collectors.toList());

        return jsonEmployeeObjectsList;
    }
}
