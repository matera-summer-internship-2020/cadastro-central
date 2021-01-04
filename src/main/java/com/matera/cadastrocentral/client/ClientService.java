package com.matera.cadastrocentral.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /* API requests */

    // 1. Get all clients from the database.
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // 2. Get a specific client by id.
    public Optional<Client> getClientById(UUID clientId) {
        return clientRepository.findById(clientId);
    }
}
