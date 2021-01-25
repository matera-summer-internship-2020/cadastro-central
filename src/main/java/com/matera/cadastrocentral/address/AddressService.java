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
