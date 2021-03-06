package com.matera.cadastrocentral.address;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private AddressRepository addressRepository;
    private ClientService clientService;

    @Autowired
    public AddressService(AddressRepository addressRepository, ClientService clientService) {
        this.addressRepository = addressRepository;
        this.clientService = clientService;
    }

    public Address getAddressById(UUID id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAddressListByClientId(UUID clientId) {
        Client client = clientService.getClientById(clientId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't Found Client"));
        return addressRepository.findAllByClientId(client);
    }

    public Address insertAddress(UUID clientId, AddressDTO newAddress) {
        Optional<Client> client = clientService.getClientById(clientId);
        if (addressRepository.findAll(Example.of(new Address(newAddress))).isEmpty()) {
            Address address = new Address(newAddress);
            address.setClientId(client.get());
            address.setAddressId(newAddress.getAddressId());
            addressRepository.save(address);
            return address;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço já existe");
        }
    }

    public void deleteAddress(UUID id) {
        addressRepository.deleteById(id);
    }

    public Address alterAddress(UUID id, AddressDTO newAddress) {
        Address alteredAddress = addressRepository.findById(id).orElse(null);

        if (alteredAddress == null) {
            return null;
        }

        alteredAddress.setNumber(Optional.ofNullable(newAddress.getNumber()).orElse(alteredAddress.getNumber()));
        alteredAddress.setZipCode(Optional.ofNullable(newAddress.getZipCode()).orElse(alteredAddress.getZipCode()));
        alteredAddress.setDistrict(Optional.ofNullable(newAddress.getDistrict()).orElse(alteredAddress.getDistrict()));
        alteredAddress.setComplement(Optional.ofNullable(newAddress.getComplement()).orElse(alteredAddress.getComplement()));
        alteredAddress.setStreetName(Optional.ofNullable(newAddress.getStreetName()).orElse(alteredAddress.getStreetName()));
        alteredAddress.setState(Optional.ofNullable(newAddress.getState()).orElse(alteredAddress.getState()));
        alteredAddress.setCity(Optional.ofNullable(newAddress.getCity()).orElse(alteredAddress.getCity()));

        return addressRepository.save(alteredAddress);
    }
}
