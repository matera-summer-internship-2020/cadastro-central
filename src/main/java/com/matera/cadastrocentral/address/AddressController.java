package com.matera.cadastrocentral.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/clients/{clientId}/addresses")
    Address newAddress(@PathVariable UUID clientId, @RequestBody AddressDTO newAddress) {
        return addressService.insertAddress(clientId, newAddress);
    }

    @GetMapping("/addresses/{addressId}")
    Address getAddressById(@PathVariable UUID addressId) {
        return addressService.getAddressById(addressId);
    }

    @GetMapping("/clients/{clientId}/addresses")
    List<Address> getAddressListByClientId(@PathVariable UUID clientId) {
        return addressService.getAddressListByClientId(clientId);
    }

    @PutMapping("/addresses/{addressId}")
    Address alterAddress(@PathVariable UUID addressId, @RequestBody AddressDTO newAddress) {
        return addressService.alterAddress(addressId, newAddress);
    }

    @DeleteMapping("/addresses/{addressId}")
    void deleteAddress(@PathVariable UUID addressId) {
        addressService.deleteAddress(addressId);
    }
}
