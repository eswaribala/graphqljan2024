package com.optum.bankingapi.queries;

import com.optum.bankingapi.models.Customer;
import com.optum.bankingapi.services.CustomerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private CustomerService customerService;

    public List<Customer> findAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    public Customer findCustomerByAccountNo(long accountNo) {
        return this.customerService.getCustomerByAccountNo(accountNo);
    }

   public Customer findCustomerByEmail(String email){
        return this.customerService.getCustomerByEmail(email);
    }

}
