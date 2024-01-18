package com.optum.bankingapi.mutations;

import com.optum.bankingapi.dtos.AddressInput;
import com.optum.bankingapi.models.Address;
import com.optum.bankingapi.services.AddressService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private AddressService addressService;
    public Address createAddress(long accountNo, AddressInput addressInput) {
        Address address=Address.builder()
                .addressId(0)
                .city(addressInput.getCity())
                .doorNo(addressInput.getDoorNo())
                .streetName(addressInput.getStreetName())
                .pincode(addressInput.getPincode())
                .build();
        return addressService.addAddress(accountNo, address);
    }

    public Address updateAddress(long addressId, AddressInput addressInput) {
        Address address = Address.builder()
                .addressId(0)
                .city(addressInput.getCity())
                .doorNo(addressInput.getDoorNo())
                .streetName(addressInput.getStreetName())
                .pincode(addressInput.getPincode())
                .build();
        return addressService.updateAddress(addressId, address);
    }
    public boolean deleteAddress(long addressId) {
        return addressService.deleteAddress(addressId);
    }
}
