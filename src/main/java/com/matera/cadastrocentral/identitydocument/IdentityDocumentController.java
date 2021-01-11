package com.matera.cadastrocentral.identitydocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
