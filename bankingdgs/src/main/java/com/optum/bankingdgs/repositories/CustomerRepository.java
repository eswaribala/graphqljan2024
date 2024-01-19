package com.optum.bankingdgs.repositories;

import com.optum.bankingdgs.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
