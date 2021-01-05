package com.matera.cadastrocentral.telephone;

import com.sun.istack.NotNull;

import java.util.UUID;

public class TelephoneDTO implements TelephoneProjection {

    @NotNull
    private UUID telephoneId;
    @NotNull
    private UUID clientId;
    @NotNull
    private int telephoneTypeId;
    @NotNull
    private String number;
    @NotNull
    private String ddd;

    @Override
    public UUID getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(UUID telephoneId) {
        this.telephoneId = telephoneId;
    }

    @Override
    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    @Override
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
}
