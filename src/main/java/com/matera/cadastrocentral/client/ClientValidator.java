package com.matera.cadastrocentral.client;

import com.matera.cadastrocentral.maritalstatus.MaritalStatusValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientValidator {

    private ClientValidator() {
    }

    public static void validate(final ClientDTO clientDTO){
        if(StringUtils.isBlank(clientDTO.getName())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The client name must not be null or a empty string " +
                            "or a string only with whitespaces!"
            );
        }
        if(clientDTO.getName().length() > 255){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The client name must not exceed 255 characters!"
            );
        }
        MaritalStatusValidator.validate(clientDTO.getMaritalStatusEntity());
    }
}
