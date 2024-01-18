package com.optum.bankingapi.services;

import com.optum.bankingapi.models.Customer;
import com.optum.bankingapi.repositories.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.AbstractQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

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
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq=cb.createQuery(Customer.class);

        Root<Customer> customerObject=cq.from(Customer.class);
        cq.where(cb.equal(customerObject.get("email"),email));

        CriteriaQuery<Customer> selectResult= cq.select(customerObject);
        TypedQuery<Customer> tq=entityManager.createQuery(selectResult);
        return tq.getResultList().stream().findFirst().orElse(null);
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
