package com.matera.cadastrocentral.address;

import com.matera.cadastrocentral.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AddressDTO {

    private UUID addressId;
    private Client client; //
    private String streetName;
    private String district;
    private String state;
    private String zipCode;
    private String complement;
    private String reference;
    private String city;
}
