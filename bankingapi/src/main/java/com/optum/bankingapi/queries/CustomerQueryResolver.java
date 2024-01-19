package com.optum.bankingapi.queries;

import com.optum.bankingapi.dtos.CustomerFilter;
import com.optum.bankingapi.dtos.FilterField;
import com.optum.bankingapi.dtos.GenderFilterField;
import com.optum.bankingapi.models.Customer;
import com.optum.bankingapi.repositories.CustomerRepository;
import com.optum.bankingapi.services.CustomerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
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


        if((customerFilter.getOr()!=null)&&(customerFilter.getOr().size() > 0)) {
            log.info("Or Condition Present");
            for(int i=0;i<customerFilter.getOr().size();i++) {

                log.info(customerFilter.getOr().get(i).getAccountNo().toString());
                log.info(customerFilter.getOr().get(i).getEmail().toString());
                if ((customerFilter.getOr().get(i).getAccountNo() != null) || (customerFilter.getOr().get(i).getEmail() != null)) {
                    log.info(customerFilter.getOr().get(i).getAccountNo().toString());
                    log.info(customerFilter.getOr().get(i).getEmail().toString());
                    if(specification!=null)
                        specification = specification.or(Specification.where(byAccountNo(customerFilter.getOr().get(i).getAccountNo()))
                                .or(byEmail(customerFilter.getOr().get(i).getEmail())));
                    else
                        specification=Specification.where(byAccountNo(customerFilter.getOr().get(i).getAccountNo()))
                                .or(byEmail(customerFilter.getOr().get(i).getEmail()));


                }
            }
            //extends Jpa Specification @ repository
            return this.customerRepository.findAll(specification);
        }



        if((customerFilter.getAnd()!=null)&&(customerFilter.getAnd().size() > 0)) {
            log.info("And Condition Present");
            for(int i=0;i<customerFilter.getAnd().size();i++) {

                log.info(customerFilter.getAnd().get(i).getAccountNo().toString());
                log.info(customerFilter.getAnd().get(i).getEmail().toString());
                if ((customerFilter.getAnd().get(i).getAccountNo() != null) || (customerFilter.getAnd().get(i).getEmail() != null)) {
                    log.info(customerFilter.getAnd().get(i).getAccountNo().toString());
                    log.info(customerFilter.getAnd().get(i).getEmail().toString());
                    if(specification!=null)
                        specification = specification.or(Specification.where(byAccountNo(customerFilter.getAnd().get(i).getAccountNo()))
                                .and(byEmail(customerFilter.getAnd().get(i).getEmail())));
                    else
                        specification=Specification.where(byAccountNo(customerFilter.getAnd().get(i).getAccountNo()))
                                .and(byEmail(customerFilter.getAnd().get(i).getEmail()));


                }
            }
            //extends Jpa Specification @ repository
            return this.customerRepository.findAll(specification);
        }
        else
            return this.customerRepository.findAll();

    }


    private Specification<Customer> byAccountNo(FilterField filterField){
        return (Specification<Customer>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("accountNo"));
    }

    private Specification<Customer> byGender(GenderFilterField genderFilterField){
        return (Specification<Customer>) (root, query, builder) -> genderFilterField.generateCriteria(builder, root.get("gender"));
    }
    private Specification<Customer> byEmail(FilterField filterField) {
        return (Specification<Customer>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("email"));

    }
}
