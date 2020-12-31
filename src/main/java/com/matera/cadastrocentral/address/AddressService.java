package com.matera.cadastrocentral.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address getAddressById(UUID id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAddressListByClientId(UUID clientId) {
        return addressRepository.findAllByClientId(clientId);
    }

    public Address insertAddress(Address newAddress) {
        return addressRepository.save(newAddress);
    }

    public void deleteAddress(UUID id) {
        addressRepository.deleteById(id);
    }

    public Address alterAddress(UUID id, Address newAddress) {
        Address alteredAddress = addressRepository.findById(id).orElse(null);

        alteredAddress.setReference(Optional.ofNullable(newAddress.getReference()).orElse(alteredAddress.getReference()));
        alteredAddress.setZip_code(Optional.ofNullable(newAddress.getZip_code()).orElse(alteredAddress.getZip_code()));
        alteredAddress.setDistrict(Optional.ofNullable(newAddress.getDistrict()).orElse(alteredAddress.getDistrict()));
        alteredAddress.setComplement(Optional.ofNullable(newAddress.getComplement()).orElse(alteredAddress.getComplement()));
        alteredAddress.setStreet_name(Optional.ofNullable(newAddress.getStreet_name()).orElse(alteredAddress.getStreet_name()));
        alteredAddress.setState(Optional.ofNullable(newAddress.getState()).orElse(alteredAddress.getState()));
        alteredAddress.setCity(Optional.ofNullable(newAddress.getCity()).orElse(alteredAddress.getCity()));

        return addressRepository.save(alteredAddress);
    }
}
