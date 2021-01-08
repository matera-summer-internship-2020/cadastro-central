package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.telephonetype.TelephoneType;
import com.sun.istack.NotNull;

import java.util.UUID;

public class TelephoneDTO implements TelephoneProjection {

    public TelephoneDTO() {
    }

    @NotNull
    private UUID telephoneId;
    @NotNull
    private UUID clientId;
    @NotNull
    private TelephoneType telephoneType;
    @NotNull
    private String number;
    @NotNull
    private String ddd;

    @Override
    public UUID getTelephoneId() {
        return telephoneId;
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

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getDdd() {
        return ddd;
    }

}
