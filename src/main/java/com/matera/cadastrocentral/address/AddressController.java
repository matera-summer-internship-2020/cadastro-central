package com.matera.cadastrocentral.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    Address newAddress(@RequestBody Address newAddress) {
        return addressService.insertAddress(newAddress);
    }

    @GetMapping("/{addressId}")
    Address getAddressById(@PathVariable UUID addressId) {
        return addressService.getAddressById(addressId);
    }

    @GetMapping
    List<Address> getAddressListByClientId(@PathVariable(value = "clientId", required = true) UUID clientId) {
        return addressService.getAddressListByClientId(clientId);
    }

    @PutMapping("/{addressId}")
    Address alterAddress(@PathVariable UUID addressId, @RequestBody Address newAddress) {
        return addressService.alterAddress(addressId, newAddress);
    }

    @DeleteMapping("/{addressId}")
    void deleteAddress(@PathVariable UUID addressId) {
        addressService.deleteAddress(addressId);
    }
}
