package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.AddressInfo;
import com.jsw.auditSystem.model.UserInfoAudit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressMangoRepository extends MongoRepository<AddressInfo, Long> {
}
