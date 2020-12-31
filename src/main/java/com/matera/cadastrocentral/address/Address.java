package com.matera.cadastrocentral.address;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Address {

    private @Id @GeneratedValue UUID address_id;
    private UUID clientId;
    private String street_name;
    private String district;
    private String state;
    private String zip_code;
    private String complement;
    private String reference;
    private String city;


    public Address() {}

    public Address(UUID clientId, String street_name, String district, String state, String zip_code, String complement, String reference) {
        this.clientId = clientId;
        this.street_name = street_name;
        this.district = district;
        this.state = state;
        this.zip_code = zip_code;
        this.complement = complement;
        this.reference = reference;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getDistrict() {
        return district;
    }

    public UUID getAddress_id() {
        return address_id;
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

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
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
