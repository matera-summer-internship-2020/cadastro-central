package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.telephonetype.TelephoneType;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Telephone implements TelephoneProjection {
    public Telephone() {
    }

    public Telephone(TelephoneDTO telephoneDTO) {
        this.telephoneId = telephoneDTO.getTelephoneId();
        this.clientId = telephoneDTO.getClientId();
        this.telephoneType = telephoneDTO.getTelephoneType();
        this.number = telephoneDTO.getNumber();
        this.ddd = telephoneDTO.getDdd();
    }

    public Telephone(UUID telephoneId, UUID clientId, TelephoneType telephoneType, String number, String ddd) {
        this.telephoneId = telephoneId;
        this.clientId = clientId;
        this.telephoneType = telephoneType;
        this.number = number;
        this.ddd = ddd;
    }

    @Override
    public UUID getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(UUID telephoneId) {
        this.telephoneId = telephoneId;
    }

    @PrePersist
    public void prePersist() {
        this.telephoneId = UUID.randomUUID();
    }

    @Override
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Override
    public TelephoneType getTelephoneType() {
        return telephoneType;
    }

    public void setTelephoneType(TelephoneType telephoneType) {
        this.telephoneType = telephoneType;
    }

    @Override
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @Id
    private UUID telephoneId;

    private UUID clientId;

    @ManyToOne
    @JoinColumn(name = "telephone_type_id")
    TelephoneType telephoneType;

    String number;

    String ddd;

}
