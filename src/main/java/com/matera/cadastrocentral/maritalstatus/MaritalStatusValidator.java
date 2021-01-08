package com.matera.cadastrocentral.maritalstatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MaritalStatusValidator {

    private MaritalStatusValidator(){
    }

    public static void validate(MaritalStatusEntity maritalStatusEntity){
        if(maritalStatusEntity == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The request must have the marital status information!"
            );
        }
        if(maritalStatusEntity.getMaritalStatusId() == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The request must specify the correct marital status!"
            );
        }
        if(maritalStatusEntity.getMaritalStatusId() < 1 ||
                maritalStatusEntity.getMaritalStatusId() > 3){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid option of marital status! Choose a valid one."
            );
        }
    }
}
