package com.matera.cadastrocentral.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT doc.client.clientId FROM IdentityDocumentEntity doc WHERE doc.identityDocument = :clientCpf")
    UUID findClientIdByCpf(@Param("clientCpf") String clientCpf);
}
