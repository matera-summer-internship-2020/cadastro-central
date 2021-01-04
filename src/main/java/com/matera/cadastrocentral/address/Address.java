package com.matera.cadastrocentral.address;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
public class Address {

    private @Id UUID addressId;
    private UUID clientId;
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

    public Address(UUID clientId, String streetName, String district, String state, String zipCode, String complement, String reference) {
        this.clientId = clientId;
        this.streetName = streetName;
        this.district = district;
        this.state = state;
        this.zipCode = zipCode;
        this.complement = complement;
        this.reference = reference;
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

    public UUID getClientId() {
        return clientId;
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
