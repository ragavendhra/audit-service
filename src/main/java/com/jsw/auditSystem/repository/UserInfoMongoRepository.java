package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.UserInfoAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMongoRepository extends MongoRepository<UserInfoAudit, String> {

    @Query(value= "{userId:?0}")
    List<UserInfoAudit> findByUserId(String id);
}
