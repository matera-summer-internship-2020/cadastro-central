package com.matera.cadastrocentral.identitydocument;

import com.matera.cadastrocentral.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocumentEntity, UUID> {

    List<IdentityDocumentEntity> findAllDocumentsByClient(Client client);

    Optional<IdentityDocumentEntity> findDocumentByIdentityDocumentIdAndClient(
            Client client, UUID documentId);
}
