package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.UserInfoAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMongoRepository extends MongoRepository<UserInfoAudit, String> {
}
