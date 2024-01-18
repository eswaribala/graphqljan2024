package com.optum.bankingapi.services;

import com.optum.bankingapi.models.Address;
import com.optum.bankingapi.models.Customer;

import com.optum.bankingapi.repositories.AddressRepository;
import com.optum.bankingapi.repositories.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AddressService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EntityManager entityManager;

    // insert
    public Address addAddress(long accountNo, Address address){

        Customer customer=this.customerRepository.findById(accountNo).orElse(null);
         if(customer!=null){
             address.setCustomer(customer);
            return this.addressRepository.save(address);
        }else
            return null;
    }

    //select all

    public List<Address> getAllAddresses(){
        return this.addressRepository.findAll();
    }

    //where by accountNo

    public List<Address> getAddressByPinCode(long pincode){
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> cq=cb.createQuery(Address.class);

        Root<Address> addressObject=cq.from(Address.class);
        cq.where(cb.equal(addressObject.get("pincode"),pincode));

        CriteriaQuery<Address> selectResult= cq.select(addressObject);
        TypedQuery<Address> tq=entityManager.createQuery(selectResult);
        return tq.getResultList();
    }

    // where by accountno
    public List<Address> getAddressByAccountNo(long accountNo){

        Customer customer=this.customerRepository.findById(accountNo).orElse(null);
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> cq=cb.createQuery(Address.class);

        Root<Address> addressObject=cq.from(Address.class);
        cq.where(cb.equal(addressObject.get("customer"),customer));

        CriteriaQuery<Address> selectResult= cq.select(addressObject);
        TypedQuery<Address> tq=entityManager.createQuery(selectResult);
        return tq.getResultList();
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
