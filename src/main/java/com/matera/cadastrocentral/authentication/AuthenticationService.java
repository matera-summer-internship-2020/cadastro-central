package com.matera.cadastrocentral.authentication;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.client.ClientRepository;
import com.matera.cadastrocentral.client.ClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private ClientService clientService;
    private ClientRepository clientRepository;


    @Autowired
    public AuthenticationService(ClientService clientService, ClientRepository clientRepository) {
        this.clientService =  clientService;
        this.clientRepository = clientRepository;
    }


    public void changePassword(UUID clientId, PasswordDTO newPassword) {
        Optional<Client> client =  clientService.getClientById(clientId);
        //if(StringUtils.isNumeric(clientDTO.getPassword()) && clientDTO.getPassword().length() == 6)
        if (StringUtils.isNumeric(newPassword.getPassword()) && newPassword.getPassword().length() == 6) {
            client.get().setPassword(newPassword.getPassword());
            clientRepository.save(client.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insira uma senha numérica de 6 digítos");
        }
    }
}
