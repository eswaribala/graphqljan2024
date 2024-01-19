package com.optum.bankingdgs.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.optum.bankingdgs.dtos.AddressInput;
import com.optum.bankingdgs.models.Address;
import com.optum.bankingdgs.models.Customer;
import com.optum.bankingdgs.repositories.AddressRepository;
import com.optum.bankingdgs.repositories.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class AddressFetcher {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EntityManager entityManager;
    @DgsMutation
    public Address createAddress(@InputArgument long accountNo, @InputArgument AddressInput addressInput) {

        Address address=Address.builder()
                .addressId(0)
                .city(addressInput.getCity())
                .doorNo(addressInput.getDoorNo())
                .streetName(addressInput.getStreetName())
                .pincode(addressInput.getPincode())
                .build();
        Customer customer=this.customerRepository.findById(accountNo).orElse(null);
        if(customer!=null){
            address.setCustomer(customer);
            return this.addressRepository.save(address);
        }else
            return null;
    }
    @DgsMutation
    public Address updateAddress(@InputArgument long addressId, @InputArgument AddressInput addressInput) {
        Address address=Address.builder()
                .addressId(0)
                .city(addressInput.getCity())
                .doorNo(addressInput.getDoorNo())
                .streetName(addressInput.getStreetName())
                .pincode(addressInput.getPincode())
                .build();

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
    @DgsMutation
    public boolean deleteAddress(@InputArgument long addressId) {

        Address address= this.addressRepository.findById(addressId).orElse(null);
        boolean status=false;
        if (address != null) {
            this.addressRepository.deleteById(addressId);
            status=true;
        }
        return status;
    }
    @DgsQuery
    public List<Address> findAllAddresses(){

        return this.addressRepository.findAll();
    }
    @DgsQuery
    public List<Address> findAddressByPinCode(@InputArgument long pincode){

        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> cq=cb.createQuery(Address.class);

        Root<Address> addressObject=cq.from(Address.class);
        cq.where(cb.equal(addressObject.get("pincode"),pincode));

        CriteriaQuery<Address> selectResult= cq.select(addressObject);
        TypedQuery<Address> tq=entityManager.createQuery(selectResult);
        return tq.getResultList();
    }
    @DgsQuery
    public List<Address> findAddressByAccountNo(@InputArgument long accountNo){
        Customer customer=this.customerRepository.findById(accountNo).orElse(null);
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> cq=cb.createQuery(Address.class);

        Root<Address> addressObject=cq.from(Address.class);
        cq.where(cb.equal(addressObject.get("customer"),customer));

        CriteriaQuery<Address> selectResult= cq.select(addressObject);
        TypedQuery<Address> tq=entityManager.createQuery(selectResult);
        return tq.getResultList();
    }


}
