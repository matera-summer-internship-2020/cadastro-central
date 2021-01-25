package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.telephonetype.TelephoneType;

import java.util.UUID;

public interface TelephoneProjection {
    UUID getTelephoneId();
    TelephoneType getTelephoneType();
    String getNumber();
    String getDdd();
    Client getClientId();
}
