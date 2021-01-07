package com.matera.cadastrocentral.telephone;

import java.util.UUID;

public interface TelephoneProjection {
    UUID getTelephoneId();
    UUID getClientId();
    Integer getTelephoneTypeId();
    String getNumber();
    String getDdd();
}
