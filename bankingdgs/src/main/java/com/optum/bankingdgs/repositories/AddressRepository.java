package com.optum.bankingdgs.repositories;

import com.optum.bankingdgs.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
