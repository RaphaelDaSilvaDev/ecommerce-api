package com.raphaelsilva.ecommerce.modules.address.dto.request;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class UpdateAddressRequest {
    @Nullable
    String street_name;

    @Nullable
    String number;

    @Nullable
    String city_name;

    @Nullable
    String complement;

    @Nullable
    String state_name;

    @Nullable
    String country;

    @Nullable
    String postal_code;

    @Nullable
    String phone_number;

    @Nullable
    String mobile_number;
}
