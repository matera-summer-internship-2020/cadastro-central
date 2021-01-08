package com.matera.cadastrocentral.identitydocumenttype;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "identity_document_type")
public class IdentityDocumentTypeEntity {

    @Id
    private Integer identityDocumentTypeId;
    private String identityDocumentType;
}
