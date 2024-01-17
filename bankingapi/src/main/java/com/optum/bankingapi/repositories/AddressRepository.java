package com.optum.bankingapi.repositories;

import com.optum.bankingapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
