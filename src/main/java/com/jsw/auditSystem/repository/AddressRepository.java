package com.jsw.auditSystem.repository;

import com.jsw.auditSystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
