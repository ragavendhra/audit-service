package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.AddressInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMangoRepository extends MongoRepository<AddressInfo, String> {

    @Query(value= "{addressId:?0}")
    List<AddressInfo> findByAddressId(String id);
}
