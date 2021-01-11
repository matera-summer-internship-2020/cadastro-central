package com.matera.cadastrocentral.identitydocument;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.identitydocumenttype.IdentityDocumentTypeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class IdentityDocumentDTO {

    @NotNull
    private IdentityDocumentTypeEntity identityDocumentTypeEntity;
    @NotNull
    private String identityDocument;
    @NotNull
    private Client client;
}
