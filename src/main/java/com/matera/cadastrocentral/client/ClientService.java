package com.matera.cadastrocentral.client;

import com.matera.cadastrocentral.identitydocument.IdentityDocumentRepository;
import com.matera.cadastrocentral.telephone.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final IdentityDocumentRepository identityDocumentRepository;
    private final TelephoneRepository telephoneRepository;

    @Autowired
    public ClientService(final ClientRepository clientRepository,
                         final IdentityDocumentRepository identityDocumentRepository,
                         final TelephoneRepository telephoneRepository) {
        this.clientRepository = clientRepository;
        this.identityDocumentRepository = identityDocumentRepository;
        this.telephoneRepository = telephoneRepository;
    }

    /* API requests */

    // 1. Get all clients from the database.
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // 2. Get a specific client by id.
    public Optional<Client> getClientById(UUID clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent()){
            return client;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Client ID does not exist in the database! Try a valid one."
            );
        }
    }

    // 3. Insert a client into the database.
    public Client insertClient(final ClientDTO clientDTO) {
        // Marital Status should be specified in the body
        // since it has a not nullable constraint
        if(clientDTO.getMaritalStatusEntity() != null){
            // Identity Document List should be specified in the body
            // since it has a not nullable constraint, and it should
            // contains at least one identity document
            if(clientDTO.getIdentityDocumentEntityList() != null &&
                    !clientDTO.getIdentityDocumentEntityList().isEmpty()) {
                UUID clientId = UUID.randomUUID();
                Client client = new Client(clientDTO);
                client.setClientId(clientId);
                client.getIdentityDocumentEntityList().forEach(
                        identityDocumentEntity -> identityDocumentEntity.setClient(client)
                );
                return clientRepository.save(client);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "At least one identity document should be specified!"
                );
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Marital Status is an obligatory field!"
            );
        }
    }

    // 4. Alter a client information in the database.
    public Client alterClient(UUID clientId, ClientDTO clientDTO) {
        try {
            Client alteredClient = clientRepository.getOne(clientId);
            alteredClient.setName(Optional.ofNullable(
                    clientDTO.getName()).orElse(alteredClient.getName()));
            alteredClient.setMaritalStatusEntity(Optional.ofNullable(
                    clientDTO.getMaritalStatusEntity()).orElse(alteredClient.getMaritalStatusEntity()));

            return clientRepository.save(alteredClient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Client ID does not exist in the database! Try a valid one."
            );
        }
    }

    // 5. Delete a client from the database
    public void deleteClient(final UUID clientId) {
        try {
            clientRepository.deleteById(clientId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Client ID does not exist in the database! Try a valid one."
            );
        }
    }
}
