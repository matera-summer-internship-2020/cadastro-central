package com.matera.cadastrocentral.client;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class ClientDTO {

    @Id
    private UUID clientId;
    @NotNull
    private Integer maritalStatusId;
    @NotNull
    private String name;
    @NotNull
    private UUID identityDocumentId;

    /* Constructors */

    public ClientDTO(UUID clientId, @NotNull Integer maritalStatusId,
                     @NotNull String name, @NotNull UUID identityDocumentId) {
        this.clientId = clientId;
        this.maritalStatusId = maritalStatusId;
        this.name = name;
        this.identityDocumentId = identityDocumentId;
    }
}
