package com.matera.cadastrocentral.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginDTO {

    @NotNull
    private String clientCPF;
    @NotNull
    private String clientPassword;
}
