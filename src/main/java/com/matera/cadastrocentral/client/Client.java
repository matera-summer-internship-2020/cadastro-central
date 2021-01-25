package com.matera.cadastrocentral.client;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matera.cadastrocentral.address.Address;
import com.matera.cadastrocentral.identitydocument.IdentityDocumentEntity;
import com.matera.cadastrocentral.maritalstatus.MaritalStatusEntity;
import com.matera.cadastrocentral.telephone.Telephone;
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
    @OneToMany(mappedBy = "client", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    private List<IdentityDocumentEntity> identityDocumentEntityList;
    @OneToMany(mappedBy = "clientId", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    private List<Address> addressList;
    private String password;

    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Telephone> telephoneList;
    /* Constructors */

    public Client(ClientDTO clientDTO){
        this.name = clientDTO.getName();
        this.maritalStatusEntity = clientDTO.getMaritalStatusEntity();
        this.identityDocumentEntityList = clientDTO.getIdentityDocumentEntityList();
        this.telephoneList = clientDTO.getTelephoneList();
        this.password = clientDTO.getPassword();
    }
}
