package com.optum.bankingdgs.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.optum.bankingdgs.dtos.CustomerInput;
import com.optum.bankingdgs.models.Customer;
import com.optum.bankingdgs.models.Gender;

import java.time.LocalDate;

@DgsComponent
public class CustomerFetcher {


    @DgsMutation
    public Customer createCustomer(CustomerInput customerInput){
            return null;
    }

     @DgsMutation
    public Customer updateCustomer(long accountNo, String password, long phoneNo){
        return null;
    }

    @DgsMutation
    public Customer  updateGenderDOBCustomer(long accountNo, Gender gender, LocalDate dob){
            return  null;
    }
    @DgsMutation
    public boolean deleteCustomer(long accountNo){
        return false;
    }
    @DgsQuery
    public Customer findAllCustomers(){
        return null;
    }
    @DgsQuery
    public Customer findCustomerByAccountNo(long accountNo){
        return null;
    }
    @DgsQuery
   public Customer  findCustomerByEmail(String email){
        return null;
   }

}
