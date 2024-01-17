package com.optum.bankingapi.repositories;

import com.optum.bankingapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("Select c from Customer c where c.email=:email")
    public Customer findByEmail(@Param("email") String email);

}