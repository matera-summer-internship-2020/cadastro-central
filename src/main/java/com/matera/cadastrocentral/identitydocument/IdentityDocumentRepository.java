package com.matera.cadastrocentral.identitydocument;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocumentEntity, UUID> {
}
