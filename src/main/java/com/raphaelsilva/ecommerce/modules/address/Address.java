package com.raphaelsilva.ecommerce.modules.address;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id = UUID.randomUUID();

    String street_name;
    String number;
    String city_name;

    @Column(nullable = true)
    String complement = "";
    String state_name;
    String country;
    String postal_code;
    String phone_number;

    @Column(nullable = true)
    String mobile_number = "";

    @CreationTimestamp
    LocalDateTime created_at;

    @UpdateTimestamp
    LocalDateTime updated_at;

    public Address(String street_name, String number, String city_name, String complement, String state_name, String country, String postal_code, String phone_number, String mobile_number) {
        this.street_name = street_name;
        this.number = number;
        this.city_name = city_name;
        this.complement = complement;
        this.state_name = state_name;
        this.country = country;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.mobile_number = mobile_number;
    }

    public Address() {}
}
