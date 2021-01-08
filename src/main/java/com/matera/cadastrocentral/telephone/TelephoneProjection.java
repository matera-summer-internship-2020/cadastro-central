package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.telephonetype.TelephoneType;

import java.util.UUID;

public interface TelephoneProjection {
    UUID getTelephoneId();
    UUID getClientId();
    TelephoneType getTelephoneType();
    String getNumber();
    String getDdd();
}
