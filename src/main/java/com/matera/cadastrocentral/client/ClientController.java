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

    // 4. Alter all client information in the database.
    // All the ClientDTO information must be in the payload.
    @PutMapping("/{clientId}")
    public Client alterAllClientInformation(
            @PathVariable("clientId") final UUID clientId,
            @RequestBody final ClientDTO clientDTO
    ) {
        return clientService.alterAllClientInformation(clientId, clientDTO);
    }

    // 5. Alter some client information in the database.
    // It's not necessary to pass all the ClientDTO information in the payload.
    @PatchMapping("/{clientId}")
    public Client alterSomeClientInformation(
            @PathVariable("clientId") final UUID clientId,
            @RequestBody final ClientDTO clientDTO
    ) {
        return clientService.alterSomeClientInformation(clientId, clientDTO);
    }

    // 6. Delete a client from the database
    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable final UUID clientId) {
        clientService.deleteClient(clientId);
    }
}
