package com.matera.cadastrocentral.client;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matera.cadastrocentral.identitydocument.IdentityDocumentEntity;
import com.matera.cadastrocentral.maritalstatus.MaritalStatusEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    private UUID clientId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "marital_status_id")
    private MaritalStatusEntity maritalStatusEntity;
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<IdentityDocumentEntity> identityDocumentEntityList;

    /* Constructors */

    public Client(ClientDTO clientDTO){
        this.name = clientDTO.getName();
        this.maritalStatusEntity = clientDTO.getMaritalStatusEntity();
        this.identityDocumentEntityList = clientDTO.getIdentityDocumentEntityList();
    }

    @PrePersist
    public void prePersist(){
        this.clientId = UUID.randomUUID();
    }
}
