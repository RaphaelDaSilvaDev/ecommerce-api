package com.raphaelsilva.ecommerce.modules.address.mapper;

import com.raphaelsilva.ecommerce.modules.address.Address;
import com.raphaelsilva.ecommerce.modules.address.dto.request.CreateAddressRequest;
import com.raphaelsilva.ecommerce.modules.address.dto.request.UpdateAddressRequest;
import org.springframework.stereotype.Controller;

@Controller
public class AddressMapper {

    public Address createAddressRequestToAddress(CreateAddressRequest request){
        Address address = new Address(
                request.getStreet_name(),
                request.getNumber(),
                request.getCity_name(),
                request.getComplement(),
                request.getState_name(),
                request.getCountry(),
                request.getPostal_code(),
                request.getPhone_number(),
                request.getMobile_number()
        );

        if(request.getComplement() != null) address.setComplement(request.getComplement());
        if(request.getMobile_number() != null) address.setMobile_number(request.getMobile_number());

        return address;
    }

    public Address updateAddress(UpdateAddressRequest request, Address address){
        if(request.getStreet_name() != null) address.setStreet_name(request.getStreet_name());
        if(request.getNumber() != null) address.setNumber(request.getNumber());
        if(request.getCity_name() != null) address.setCity_name(request.getCity_name());
        if(request.getComplement() != null) address.setComplement(request.getComplement());
        if(request.getState_name() != null) address.setState_name(request.getState_name());
        if(request.getCountry() != null) address.setCountry(request.getCountry());
        if(request.getPostal_code() != null) address.setPostal_code(request.getPostal_code());
        if(request.getPhone_number() != null) address.setPhone_number(request.getPhone_number());
        if(request.getMobile_number() != null) address.setMobile_number(request.getMobile_number());

        return address;
    }
}
