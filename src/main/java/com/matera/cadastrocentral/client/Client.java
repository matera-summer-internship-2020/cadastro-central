package com.matera.cadastrocentral.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    private UUID clientId;
    private Integer maritalStatusId;
    private String name;
    private UUID identityDocumentId;

    /* Constructors */

    public Client(ClientDTO clientDTO) {
        this.clientId = clientDTO.getClientId();
        this.maritalStatusId = clientDTO.getMaritalStatusId();
        this.name = clientDTO.getName();
        this.identityDocumentId = clientDTO.getIdentityDocumentId();
    }

    public Client(UUID clientId, Integer maritalStatusId,
                  String name, UUID identityDocumentId) {
        this.clientId = clientId;
        this.maritalStatusId = maritalStatusId;
        this.name = name;
        this.identityDocumentId = identityDocumentId;
    }
}
