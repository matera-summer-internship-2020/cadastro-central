package com.matera.cadastrocentral.identitydocument;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.identitydocumenttype.IdentityDocumentTypeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "identity_document")
public class IdentityDocumentEntity {

    @Id
    private UUID identityDocumentId;
    @ManyToOne
    @JoinColumn(name = "identity_document_type_id")
    private IdentityDocumentTypeEntity identityDocumentTypeEntity;
    private String identityDocument;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    /* Constructors */

    public IdentityDocumentEntity(IdentityDocumentDTO identityDocumentDTO) {
        this.identityDocumentTypeEntity = identityDocumentDTO.getIdentityDocumentTypeEntity();
        this.identityDocument = identityDocumentDTO.getIdentityDocument();
        this.client = identityDocumentDTO.getClient();
    }

    @PrePersist
    public void prePersist(){
        this.identityDocumentId = UUID.randomUUID();
    }
}
