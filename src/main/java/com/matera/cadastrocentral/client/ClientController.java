package com.matera.cadastrocentral.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // 3. Insert a client into the database.
    @PostMapping
    public Client insertClient(@RequestBody @Valid ClientDTO clientDTO){
        return clientService.insertClient(clientDTO);
    }

    // 4. Alter a client information in the database.
    @PutMapping("/{clientId}")
    public Client alterClient(
            @PathVariable("clientId") final UUID clientId,
            @RequestBody final ClientDTO clientDTO
    ) {
        return clientService.alterClient(clientId, clientDTO);
    }
}
