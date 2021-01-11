package com.matera.cadastrocentral.identitydocument;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IdentityDocumentService {

    private final IdentityDocumentRepository identityDocumentRepository;
    private final ClientService clientService;

    @Autowired
    public IdentityDocumentService(IdentityDocumentRepository identityDocumentRepository,
                                   ClientService clientService) {
        this.identityDocumentRepository = identityDocumentRepository;
        this.clientService = clientService;
    }

    /* API requests */

    // 1. Get all documents from one client.
    public List<IdentityDocumentEntity> getAllDocumentsByClientId(final UUID clientId) {
        Optional<Client> client = clientService.getClientById(clientId);
        return identityDocumentRepository.findAllDocumentsByClient(client.get());
    }
}
