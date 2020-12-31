package com.matera.cadastrocentral.address;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
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

    @GetMapping("/{address_id}")
    Address getAddressById(@PathVariable UUID address_id) {
        return addressService.getAddressById(address_id);
    }

    @GetMapping
    List<Address> getAddressListByClientId(@RequestParam(value="clientId", required = true) UUID clientId) {
        return addressService.getAddressListByClientId(clientId);
    }

    @PutMapping("/{address_id}")
    Address alterAddress(@PathVariable UUID address_id, @RequestBody Address newAddress) {
        return addressService.alterAddress(address_id, newAddress);
    }

    @DeleteMapping("/{address_id}")
    void deleteAddress(@PathVariable UUID address_id) {
        addressService.deleteAddress(address_id);
    }
}
