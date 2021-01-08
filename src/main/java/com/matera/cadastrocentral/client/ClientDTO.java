package com.matera.cadastrocentral.client;

import com.matera.cadastrocentral.identitydocument.IdentityDocumentEntity;
import com.matera.cadastrocentral.maritalstatus.MaritalStatusEntity;
import com.matera.cadastrocentral.telephone.Telephone;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Data
public class ClientDTO {

    @NotNull
    private String name;
    @NotNull
    private MaritalStatusEntity maritalStatusEntity;
    @NotNull
    private List<IdentityDocumentEntity> identityDocumentEntityList;
    @NotNull
    private List<Telephone> telephoneList;
}
