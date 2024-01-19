package com.optum.bankingdgs.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.optum.bankingdgs.dtos.CustomerInput;
import com.optum.bankingdgs.models.Customer;
import com.optum.bankingdgs.models.FullName;
import com.optum.bankingdgs.models.Gender;
import com.optum.bankingdgs.repositories.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@DgsComponent
public class CustomerFetcher {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager entityManager;

    @DgsMutation
    public Customer createCustomer(@InputArgument CustomerInput customerInput){
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
        return this.customerRepository.save(customer);

    }

     @DgsMutation
    public Customer updateCustomer(@InputArgument long accountNo, @InputArgument String password, @InputArgument long phoneNo){
         Customer customer = this.customerRepository.findById(accountNo).orElse(null);
         if (customer != null) {
             customer.setPassword(password);
             customer.setPhoneNo(phoneNo);
             return this.customerRepository.save(customer);
         }
         return null;
    }

    @DgsMutation
    public Customer  updateGenderDOBCustomer(@InputArgument long accountNo, @InputArgument Gender gender, @InputArgument LocalDate dob){
        Customer customer = this.customerRepository.findById(accountNo).orElse(null);
        if (customer != null) {
            customer.setGender(gender);
            customer.setDob(dob);
            return this.customerRepository.save(customer);
        }
        return null;
    }
    @DgsMutation
    public boolean deleteCustomer(@InputArgument long accountNo){
        Customer customer = this.customerRepository.findById(accountNo).orElse(null);
        boolean status=false;
        if (customer != null) {
            this.customerRepository.deleteById(accountNo);
            status=true;
        }
        return status;
    }
    @DgsQuery
    public List<Customer> findAllCustomers(){
        return this.customerRepository.findAll();
    }
    @DgsQuery
    public Customer findCustomerByAccountNo(@InputArgument long accountNo){
        return this.customerRepository.findById(accountNo).orElse(null);
    }
    @DgsQuery
   public Customer  findCustomerByEmail(@InputArgument String email){
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq=cb.createQuery(Customer.class);

        Root<Customer> customerObject=cq.from(Customer.class);
        cq.where(cb.equal(customerObject.get("email"),email));

        CriteriaQuery<Customer> selectResult= cq.select(customerObject);
        TypedQuery<Customer> tq=entityManager.createQuery(selectResult);
        return tq.getResultList().stream().findFirst().orElse(null);
   }

}
