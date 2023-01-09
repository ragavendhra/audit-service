package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.EmployeeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeInfoMangoRepository extends MongoRepository<EmployeeInfo, Long> {
}
