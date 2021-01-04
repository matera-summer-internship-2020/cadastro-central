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

    @PrePersist
    public void prePersist(){
        this.clientId = UUID.randomUUID();
    }
}
