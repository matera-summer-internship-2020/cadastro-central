package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.telephonetype.TelephoneType;
import com.sun.istack.NotNull;

import java.util.UUID;

public class TelephoneDTO implements TelephoneProjection {

    public TelephoneDTO() {
    }

    @NotNull
    private UUID telephoneId;
    @NotNull
    private TelephoneType telephoneType;
    @NotNull
    private String number;
    @NotNull
    private String ddd;

    private Client client;

    public void setClientId(Client client) {
        this.client = client;
    }
    @Override
    public Client getClientId() {
        return client;
    }

    @Override
    public UUID getTelephoneId() {
        return telephoneId;
    }

    @Override
    public TelephoneType getTelephoneType() {
        return telephoneType;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getDdd() {
        return ddd;
    }

    public void setTelephoneId(UUID telephoneId) {
        this.telephoneId = telephoneId;
    }
}
