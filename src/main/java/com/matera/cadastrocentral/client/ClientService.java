package com.matera.cadastrocentral.client;

import com.matera.cadastrocentral.identitydocument.IdentityDocumentRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.TransientPropertyValueException;
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

    @Autowired
    public ClientService(final ClientRepository clientRepository,
                         final IdentityDocumentRepository identityDocumentRepository) {
        this.clientRepository = clientRepository;
        this.identityDocumentRepository = identityDocumentRepository;
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
                Client client = clientRepository.save(new Client(clientDTO));
                client.getIdentityDocumentEntityList().forEach(
                        identityDocumentEntity -> {
                            identityDocumentEntity.setClient(client);
                            identityDocumentRepository.save(identityDocumentEntity);
                        }
                );
                return client;
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

    // 4. Alter all client information in the database.
    // All the ClientDTO information must be in the payload.
    public Client alterAllClientInformation(UUID clientId, ClientDTO clientDTO) {
        try {
            Client alteredClient = clientRepository.getOne(clientId);
            ClientValidator.validate(clientDTO);
            alteredClient.setName(clientDTO.getName());
            alteredClient.setMaritalStatusEntity(clientDTO.getMaritalStatusEntity());
            return clientRepository.save(alteredClient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Client ID does not exist in the database! Try a valid one."
            );
        }
    }

    // 5. Alter some client information in the database.
    // It's not necessary to pass all the ClientDTO information in the payload.
    public Client alterSomeClientInformation(UUID clientId, ClientDTO clientDTO) {
        try {
            Client alteredClient = clientRepository.getOne(clientId);
            if(clientDTO.getName() == null &&
                    clientDTO.getMaritalStatusEntity() == null){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "At least one client information must be informed to update it!"
                );
            }
            if(StringUtils.isNotBlank(clientDTO.getName())){
                alteredClient.setName(clientDTO.getName());
            }
            if(clientDTO.getMaritalStatusEntity() != null){
                if(clientDTO.getMaritalStatusEntity().getMaritalStatusId() == null){
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "The request must specify the correct marital status!"
                    );
                }
                if(clientDTO.getMaritalStatusEntity().getMaritalStatusId() < 1 ||
                        clientDTO.getMaritalStatusEntity().getMaritalStatusId() > 3){
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Invalid option of marital status! Choose a valid one."
                    );
                }
                alteredClient.setMaritalStatusEntity(clientDTO.getMaritalStatusEntity());
            }
            return clientRepository.save(alteredClient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Client ID does not exist in the database! Try a valid one."
            );
        } catch (TransientPropertyValueException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getTransientEntityName() + " reference doesn't exist in the database!" +
                            "Try a valid one."
            );
        }
    }

    // 6. Delete a client from the database
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
