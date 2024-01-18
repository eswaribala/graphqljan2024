package com.optum.bankingapi.queries;

import com.optum.bankingapi.dtos.CustomerFilter;
import com.optum.bankingapi.dtos.FilterField;
import com.optum.bankingapi.dtos.GenderFilterField;
import com.optum.bankingapi.models.Customer;
import com.optum.bankingapi.repositories.CustomerRepository;
import com.optum.bankingapi.services.CustomerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;



    public List<Customer> findAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    public Customer findCustomerByAccountNo(long accountNo) {
        return this.customerService.getCustomerByAccountNo(accountNo);
    }

   public Customer findCustomerByEmail(String email){
        return this.customerService.getCustomerByEmail(email);
    }

    public List<Customer> findCustomerByCustomerFilter(CustomerFilter customerFilter){

        Specification<Customer> specification=null;
        if(customerFilter.getAccountNo() != null){
            specification=byAccountNo(customerFilter.getAccountNo());
        }

        if (customerFilter.getGender() != null) {

            specification=byGender(customerFilter.getGender());
        }

        if (specification != null)
            return customerRepository.findAll(specification);
        else
            return customerRepository.findAll();


    }


    private Specification<Customer> byAccountNo(FilterField filterField){
        return (Specification<Customer>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("accountNo"));
    }

    private Specification<Customer> byGender(GenderFilterField genderFilterField){
        return (Specification<Customer>) (root, query, builder) -> genderFilterField.generateCriteria(builder, root.get("gender"));
    }
}
