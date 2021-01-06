package com.matera.cadastrocentral.telephone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, UUID> {

    List<Telephone> findAllByClientId(UUID clientId);

}
