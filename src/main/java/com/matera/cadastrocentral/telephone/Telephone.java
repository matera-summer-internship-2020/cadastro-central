package com.matera.cadastrocentral.telephone;

import javax.persistence.*;
import java.util.Optional;
import java.util.UUID;

@Entity
public class Telephone implements TelephoneProjection {
    public Telephone() {
    }

    public Telephone(TelephoneDTO telephoneDTO) {
        this.telephoneId = telephoneDTO.getTelephoneId();
        this.clientId = telephoneDTO.getClientId();
        this.telephoneTypeId = telephoneDTO.getTelephoneTypeId();
        this.number = telephoneDTO.getNumber();
        this.ddd = telephoneDTO.getDdd();
    }

    public Telephone(UUID telephoneId, UUID clientId, int telephoneTypeId, String number, String ddd) {
        this.telephoneId = telephoneId;
        this.clientId = clientId;
        this.telephoneTypeId = telephoneTypeId;
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
    @ManyToOne
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Override
    @OneToOne
    public int getTelephoneTypeId() {
        return telephoneTypeId;
    }

    public void setTelephoneTypeId(int telephoneTypeId) {
        this.telephoneTypeId = telephoneTypeId;
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

    int telephoneTypeId;

    String number;

    String ddd;

}
