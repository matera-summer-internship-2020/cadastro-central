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
    private Client clientId; //client doesn't work
    private String streetName;
    private String district;
    private String state;
    private String zipCode;
    private String complement;
    private String reference;
    private String city;

    @PrePersist
    public void prePersist() {
        this.addressId = UUID.randomUUID();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDistrict() {
        return district;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }



}
