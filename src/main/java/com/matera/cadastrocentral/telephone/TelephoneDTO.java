package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.client.Client;
import com.sun.istack.NotNull;

import java.util.UUID;

public class TelephoneDTO implements TelephoneProjection {

    public TelephoneDTO() {
    }

    @NotNull
    private UUID telephoneId;
//    @NotNull
//    private UUID clientId;
    @NotNull
    private int telephoneTypeId;
    @NotNull
    private String number;
    @NotNull
    private String ddd;

    private Client client;

    @Override
    public Client getClientId() {
        return client;
    }

    @Override
    public UUID getTelephoneId() {
        return telephoneId;
    }

//    @Override
//    public UUID getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(UUID clientId) {
//        this.clientId = clientId;
//    }

    @Override
    public int getTelephoneTypeId() {
        return telephoneTypeId;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getDdd() {
        return ddd;
    }

}
