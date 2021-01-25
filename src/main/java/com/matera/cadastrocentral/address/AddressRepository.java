package com.matera.cadastrocentral.address;

import com.matera.cadastrocentral.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findAllByClientId(Client clientId);
}
