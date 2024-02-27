package com.raphaelsilva.ecommerce.modules.address;

import com.raphaelsilva.ecommerce.handler.NotFoundException;
import com.raphaelsilva.ecommerce.modules.address.dto.request.CreateAddressRequest;
import com.raphaelsilva.ecommerce.modules.address.dto.request.UpdateAddressRequest;
import com.raphaelsilva.ecommerce.modules.address.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;

    private final String errorMessage = "Address not found!";

    public Address create(CreateAddressRequest request){
        Address address = addressMapper.createAddressRequestToAddress(request);
        return addressRepository.save(address);
    }

    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    public Address getById(String id){
        return addressRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(errorMessage));
    }

    public Address update(String id, UpdateAddressRequest request){
        Address address = getById(id);
        Address updatedAddress = addressMapper.updateAddress(request, address);
        return addressRepository.save(updatedAddress);
    }
}
