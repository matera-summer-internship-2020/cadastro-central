package com.matera.cadastrocentral.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /* API requests */

    // 1. Get all clients from the database.
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // 2. Get a specific client by id.
    @GetMapping("/{clientId}")
    public Optional<Client> getClientById(@PathVariable UUID clientId) {
        return clientService.getClientById(clientId);
    }
}
