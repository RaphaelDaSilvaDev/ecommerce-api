package com.raphaelsilva.ecommerce.modules.address;

import com.raphaelsilva.ecommerce.modules.address.dto.request.CreateAddressRequest;
import com.raphaelsilva.ecommerce.modules.address.dto.request.UpdateAddressRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping()
    public Address create(@RequestBody @Valid CreateAddressRequest request){
        return addressService.create(request);
    }

    @GetMapping()
    public List<Address> listAll(){
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable String id){
        return  addressService.getById(id);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable String id, @RequestBody @Valid UpdateAddressRequest request){
        return addressService.update(id, request);
    }
}
