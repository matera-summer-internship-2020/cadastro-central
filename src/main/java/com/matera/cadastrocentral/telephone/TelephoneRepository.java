package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, UUID> {

    List<Telephone> findAllByClientId(Client clientId);

    @Query("SELECT tel FROM Telephone tel WHERE tel.telephoneId = :telephoneId AND tel.clientId = :clientId")
    Optional<Telephone> findByTelephoneIdAndClientId(@Param("clientId") UUID clientId, @Param("telephoneId") UUID telephoneId);
}
