package com.matera.cadastrocentral.telephone;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matera.cadastrocentral.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Telephone implements TelephoneProjection {

    public Telephone(TelephoneDTO telephoneDTO) {
        this.telephoneId = telephoneDTO.getTelephoneId();
//        this.clientId = telephoneDTO.getClientId();
        this.telephoneTypeId = telephoneDTO.getTelephoneTypeId();
        this.number = telephoneDTO.getNumber();
        this.ddd = telephoneDTO.getDdd();
        this.clientId = telephoneDTO.getClientId();
    }

    public Telephone(UUID telephoneId, int telephoneTypeId, String number, String ddd, Client client) {
        this.telephoneId = telephoneId;
//        this.clientId = clientId;
        this.telephoneTypeId = telephoneTypeId;
        this.number = number;
        this.ddd = ddd;
        this.clientId = client;
    }

    @PrePersist
    public void prePersist() {
        this.telephoneId = UUID.randomUUID();
    }

    @Id
    private UUID telephoneId;

//    private UUID clientId;

    int telephoneTypeId;

    String number;

    String ddd;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client clientId;

}
