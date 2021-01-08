package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.client.Client;

import java.util.UUID;

public interface TelephoneProjection {
    UUID getTelephoneId();
//    UUID getClientId();
    int getTelephoneTypeId();
    String getNumber();
    String getDdd();
    Client getClientId();
}
