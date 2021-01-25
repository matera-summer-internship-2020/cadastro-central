package com.matera.cadastrocentral.address;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matera.cadastrocentral.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    private  UUID addressId;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client clientId;
    private String streetName;
    private String district;
    private String state;
    private String zipCode;
    private String complement;
    private int number;
    private String city;

    public Address(AddressDTO newAddress) {
        this.addressId = newAddress.getAddressId();
        this.clientId = newAddress.getClient();
        this.streetName = newAddress.getStreetName();
        this.district = newAddress.getDistrict();
        this.state = newAddress.getState();
        this.zipCode = newAddress.getZipCode();
        this.complement = newAddress.getComplement();
        this.number = newAddress.getNumber();
        this.city = newAddress.getCity();
    }

    @PrePersist
    public void prePersist() {
        this.addressId = UUID.randomUUID();
    }
}
