package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.EmployeeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface EmployeeInfoMangoRepository extends MongoRepository<EmployeeInfo, Long> {
@Query(value= "{empId:?0}")
List<EmployeeInfo> findByEmpId(String id);
}
