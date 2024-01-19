package com.optum.bankingapi.queries;

import com.optum.bankingapi.models.Address;
import com.optum.bankingapi.services.AddressService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private AddressService addressService;

   public List<Address> findAllAddresses(){
       return this.addressService.getAllAddresses();
   }
 public List<Address> findAddressByPinCode(long pincode){
       return this.addressService.getAddressByPinCode(pincode);
    }
    public List<Address> findAddressByAccountNo(long accountNo){
       return this.addressService.getAddressByAccountNo(accountNo);
    }

}
