package com.optum.bankingapi.repositories;

import com.optum.bankingapi.models.Address;
import com.optum.bankingapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("Select a from Address a where a.pincode=:pincode")
    public List<Address> findByPinCode(@Param("pincode") long pincode);

    @Query("Select a from Address a where a.customer=:customer")
    public List<Address> findByCustomerKey(@Param("customer") Customer customer);
}
