package com.optum.bankingapi.services;

import com.optum.bankingapi.models.Address;
import com.optum.bankingapi.models.Customer;
import com.optum.bankingapi.repositories.AddressRepository;

import com.optum.bankingapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    // insert
    public Customer addAddress(long accountNo, Address address){

        Customer customer=this.customerRepository.findById(accountNo).orElse(null);
         if(customer!=null){
             address.setCustomer(customer);
            return this.customerRepository.save(customer);
        }else
            return null;
    }

    //select all

    public List<Address> getAllAddresses(){
        return this.addressRepository.findAll();
    }

    //where by accountNo

    public List<Address> getAddressByPinCode(long pincode){
        return this.addressRepository.findByPinCode(pincode);
    }

    // where by accountno
    public List<Address> getAddressByAccountNo(long accountNo){
        Customer customer=this.customerRepository.findById(accountNo).orElse(null);
        if(customer!=null)
         return this.addressRepository.findByCustomerKey(customer);
        else
            return null;
    }

    //update

    public Address updateAddress(long addressId, Address address){
        Address addressResponse = this.addressRepository.findById(addressId).orElse(null);
        if (addressResponse != null) {
            addressResponse.setCity(address.getCity());
            addressResponse.setPincode(address.getPincode());
            addressResponse.setDoorNo(address.getDoorNo());
            addressResponse.setStreetName(address.getStreetName());

            return this.addressRepository.save(addressResponse);
        }
        return null;
    }

    public boolean deleteAddress(long addressId){
        Address address= this.addressRepository.findById(addressId).orElse(null);
        boolean status=false;
        if (address != null) {
            this.addressRepository.deleteById(addressId);
            status=true;
        }
        return status;
    }

}
