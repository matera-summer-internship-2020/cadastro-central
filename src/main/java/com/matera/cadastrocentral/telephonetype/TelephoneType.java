package com.matera.cadastrocentral.telephonetype;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class TelephoneType {

    @Id
    private Integer telephoneTypeId;
    private String telephoneType;
}