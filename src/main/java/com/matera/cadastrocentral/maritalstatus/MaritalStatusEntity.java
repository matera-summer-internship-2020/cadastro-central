package com.matera.cadastrocentral.maritalstatus;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "marital_status")
public class MaritalStatusEntity {

    @Id
    private Integer maritalStatusId;
    private String maritalStatus;
}
