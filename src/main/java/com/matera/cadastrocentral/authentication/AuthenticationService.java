package com.matera.cadastrocentral.authentication;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.client.ClientRepository;
import com.matera.cadastrocentral.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        client.get().setPassword(newPassword.getNewPassword());
        clientRepository.save(client.get());
    }
}
