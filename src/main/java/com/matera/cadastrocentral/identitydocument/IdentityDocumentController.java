package com.matera.cadastrocentral.identitydocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IdentityDocumentController {

    private IdentityDocumentService identityDocumentService;

    @Autowired
    public IdentityDocumentController(IdentityDocumentService identityDocumentService) {
        this.identityDocumentService = identityDocumentService;
    }

    /* API requests */

    // 1. Get all documents from one client.
    @GetMapping("/clients/{clientId}/documents")
    public List<IdentityDocumentEntity> getAllDocumentsByClientId(@PathVariable final UUID clientId) {
        return identityDocumentService.getAllDocumentsByClientId(clientId);
    }

    // 2. Get a specific document from a specific client.
    @GetMapping("/clients/{clientId}/documents/{documentId}")
    public Optional<IdentityDocumentEntity> getDocumentByDocumentIdAndClientId(
            @PathVariable UUID clientId, @PathVariable UUID documentId) {
        return identityDocumentService.getDocumentByDocumentIdAndClientId(clientId, documentId);
    }
//
//    // 3. Insert a client into the database.
//    @PostMapping
//    public Client insertClient(@RequestBody @Valid ClientDTO clientDTO){
//        return clientService.insertClient(clientDTO);
//    }
//
//    // 4. Alter a client information in the database.
//    @PutMapping("/{clientId}")
//    public Client alterClient(
//            @PathVariable("clientId") final UUID clientId,
//            @RequestBody final ClientDTO clientDTO
//    ) {
//        return clientService.alterClient(clientId, clientDTO);
//    }
//
//    // 5. Delete a client from the database
//    @DeleteMapping("/{clientId}")
//    public void deleteClient(@PathVariable final UUID clientId) {
//        clientService.deleteClient(clientId);
//    }
}
