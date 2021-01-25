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
    private int number;
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

    public int getNumber() {
        return this.number;
    }

    public void setNumber( int number) {
        this.number = number;
    }



}
