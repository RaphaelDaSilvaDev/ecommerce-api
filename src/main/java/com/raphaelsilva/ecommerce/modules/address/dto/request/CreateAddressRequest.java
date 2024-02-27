package com.raphaelsilva.ecommerce.modules.address.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAddressRequest {
    @NotBlank
    String street_name;

    @NotBlank
    String number;

    @NotBlank
    String city_name;

    String complement;

    @NotBlank
    String state_name;

    @NotBlank
    String country;

    @NotBlank
    String postal_code;

    @NotBlank
    String phone_number;

    String mobile_number;
}
