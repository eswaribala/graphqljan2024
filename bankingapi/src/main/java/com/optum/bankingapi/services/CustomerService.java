package com.optum.bankingapi.services;

import com.optum.bankingapi.models.Customer;
import com.optum.bankingapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // insert
    public Customer addCustomer(Customer customer){

        return this.customerRepository.save(customer);

    }

    //select all

    public List<Customer> getAllCustomers(){
     return this.customerRepository.findAll();
    }

    //where by accountNo

    public Customer getCustomerByAccountNo(long accountNo){
        return this.customerRepository.findById(accountNo).orElse(null);
    }

    // where by email
    public Customer getCustomerByEmail(String email){
        return this.customerRepository.findByEmail(email);
    }

    //update

    public Customer updateCustomer(long accountNo, String password, long phoneNo){
        Customer customer = this.customerRepository.findById(accountNo).orElse(null);
        if (customer != null) {
            customer.setPassword(password);
            customer.setPhoneNo(phoneNo);
            return this.customerRepository.save(customer);
        }
        return null;
    }

    public boolean deleteCustomer(long accountNo){
        Customer customer = this.customerRepository.findById(accountNo).orElse(null);
        boolean status=false;
        if (customer != null) {
            this.customerRepository.deleteById(accountNo);
            status=true;
        }
        return status;
    }


}
