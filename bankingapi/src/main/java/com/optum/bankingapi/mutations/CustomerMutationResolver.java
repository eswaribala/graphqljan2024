package com.optum.bankingapi.mutations;

import com.optum.bankingapi.dtos.CustomerInput;
import com.optum.bankingapi.models.Customer;
import com.optum.bankingapi.models.FullName;
import com.optum.bankingapi.models.Gender;
import com.optum.bankingapi.services.CustomerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CustomerMutationResolver implements GraphQLMutationResolver {
@Autowired
    private CustomerService customerService;
    public Customer createCustomer(CustomerInput customerInput){

        Customer customer = Customer.builder()
                .accountNo(0)
                .name(FullName.builder().firstName(customerInput.getName().getFirstName())
                        .middleName(customerInput.getName().getMiddleName())
                        .lastName(customerInput.getName().getLastName()).build())
                .email(customerInput.getEmail())
                .phoneNo(customerInput.getPhoneNo())
                .password(customerInput.getPassword())
                .gender(customerInput.getGender())
                .dob(customerInput.getDob())
                .build();
        return this.customerService.addCustomer(customer);
    }

    public Customer updateCustomer(long accountNo, String password, long  phoneNo){
        return this.customerService.updateCustomer(accountNo,password,phoneNo);
    }

    public Customer updateGenderDOBCustomer(long accountNo, Gender gender, LocalDate dob){
        return this.customerService.updateGenderDOBCustomer(accountNo,gender,dob);
    }

    public boolean deleteCustomer(long accountNo){
        return this.customerService.deleteCustomer(accountNo);
    }
}
