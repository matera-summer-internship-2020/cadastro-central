package com.matera.cadastrocentral.telephone;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matera.cadastrocentral.client.Client;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.matera.cadastrocentral.telephonetype.TelephoneType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Telephone implements TelephoneProjection {

    public Telephone(TelephoneDTO telephoneDTO) {
        this.telephoneId = telephoneDTO.getTelephoneId();
        this.telephoneType = telephoneDTO.getTelephoneType();
        this.number = telephoneDTO.getNumber();
        this.ddd = telephoneDTO.getDdd();
        this.clientId = telephoneDTO.getClientId();
    }

    @PrePersist
    public void prePersist() {
        this.telephoneId = UUID.randomUUID();
    }

    @Id
    private UUID telephoneId;

    @ManyToOne
    @JoinColumn(name = "telephone_type_id")
    TelephoneType telephoneType;

    String number;

    String ddd;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client clientId;

}
