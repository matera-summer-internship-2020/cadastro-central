package com.matera.cadastrocentral.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //TODO implement the client API requests
}
