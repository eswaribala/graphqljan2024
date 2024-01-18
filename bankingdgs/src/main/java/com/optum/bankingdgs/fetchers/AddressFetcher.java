package com.optum.bankingdgs.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.optum.bankingdgs.dtos.AddressInput;
import com.optum.bankingdgs.models.Address;

import java.util.List;

@DgsComponent
public class AddressFetcher {

    @DgsMutation
    public Address createAddress(long accountNo, AddressInput addressInput) {
       return null;
    }
    @DgsMutation
    public Address updateAddress(long addressId, AddressInput addressInput) {
        return null;
    }
    @DgsMutation
    public boolean deleteAddress(long addressId) {

        return false;
    }
    @DgsQuery
    public List<Address> findAllAddresses(){

        return null;
    }
    @DgsQuery
    public List<Address> findAddressByPinCode(long pincode){

        return null;
    }
    @DgsQuery
    public List<Address> findAddressByAccountNo(long accountNo){
        return null;
    }


}
