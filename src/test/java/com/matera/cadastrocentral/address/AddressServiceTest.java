package com.matera.cadastrocentral.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {


    @Mock
    private AddressRepository addressRepository;

    private AddressService addressService;

    @BeforeEach
    void setUp() {
        this.addressService = new AddressService(addressRepository);
    }

    /* GET getAddressById TESTS */

    @Test
    @DisplayName("getAddressById should return an Address that refers to addressId")
    void getAddressById_ShouldReturnAddressReferringToAddressId() {
        final UUID addressId = UUID.randomUUID();
        final UUID clientId = UUID.randomUUID();

        Address address = new Address();
        address.setAddressId(addressId);
        address.setClientId(clientId);

        when(addressRepository.findById(addressId))
                .thenReturn(Optional.of(address));

        Address responseAddress = addressService.getAddressById(addressId);

        Assertions.assertNotNull(responseAddress);
        Assertions.assertEquals(clientId, responseAddress.getClientId());
        Assertions.assertEquals(addressId, responseAddress.getAddressId());
    }

    @Test
    @DisplayName("getAddressById should return null when Address are not found in DB")
    void getAddressById_ShouldReturnNull() {
        when(addressRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        Assertions.assertNull(addressService.getAddressById(UUID.randomUUID()));
    }

    /* GET getAddressListByClientId TESTS */

    @Test
    @DisplayName("getAddressListByClientId should return an Address list when addresses are found on DB")
    void getAddressListByClientId_ShouldReturnAddressList() {
        final UUID clientId = UUID.randomUUID();
        Address address1 = new Address();
        Address address2 = new Address();
        address1.setClientId(clientId);
        address2.setClientId(clientId);
        address1.setAddressId(UUID.randomUUID());
        address2.setAddressId(UUID.randomUUID());

        List<Address> addressesList = new ArrayList<>();
        addressesList.add(address1);
        addressesList.add(address2);

        when(addressRepository.findAllByClientId(clientId))
                .thenReturn(addressesList);

        List<Address> responseAddressList = addressService.getAddressListByClientId(clientId);

        Assertions.assertEquals(2, responseAddressList.size());
        Assertions.assertEquals(address1.getClientId(), responseAddressList.get(0).getClientId());
        Assertions.assertEquals(address2.getClientId(), responseAddressList.get(1).getClientId());
    }

    @Test
    @DisplayName("getAddressListByClientId should return an Empty Address list when addresses are not found on DB")
    void getAddressListByClientId_ShouldReturnEmptyAddressList() {
        final UUID clientId = UUID.randomUUID();

        when(addressRepository.findAllByClientId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        List<Address> responseAddressList = addressService.getAddressListByClientId(UUID.randomUUID());

        Assertions.assertEquals(0, responseAddressList.size());
    }

    /* POST insertAddress TESTS */

    @Test
    @DisplayName("insertAddress should return the Address Provided to Post in DB")
    void insertAddress_ShouldReturnTheAddressProvided() {
        final UUID addressId = UUID.randomUUID();
        final UUID clientId = UUID.randomUUID();

        Address address = new Address();
        address.setAddressId(addressId);
        address.setClientId(clientId);

        when(addressRepository.save(address))
                .thenReturn(address);

        Address responseAddress = addressService.insertAddress(address);

        Assertions.assertEquals(responseAddress.getClientId(), addressService.insertAddress(address).getClientId());
        Assertions.assertEquals(responseAddress.getAddressId(), addressService.insertAddress(address).getAddressId());
        Assertions.assertEquals(responseAddress, addressService.insertAddress(address));
    }

    @Test
    @DisplayName("insertAddress should return DataIntegrityViolationException when nullable::false fields are null")
    void insertAddress_ShouldReturnErrorWhenMissingRequiredFields() {
        final UUID addressId = UUID.randomUUID();
        final UUID clientId = UUID.randomUUID();

        Address address = new Address();
        address.setAddressId(addressId);
        address.setClientId(clientId);
        address.setStreetName(null);
        address.setDistrict("1");
        address.setCity("2");
        address.setState("3");
        address.setZipCode("4");

        when(addressRepository.save(any(Address.class)))
                .thenThrow(DataIntegrityViolationException.class);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> this.addressService.insertAddress(address));
        verify(addressRepository, times(1)).save(address);

    }

    /* DELETE deleteAddress TESTS */

    @Test
    @DisplayName("deleteAddress should delete the Address provided from DB")
    void deleteAddress_ShouldDeleteAddressFromDatabase() {
        final UUID addressId = UUID.randomUUID();

        this.addressService.deleteAddress(addressId);

        verify(addressRepository, times(1)).deleteById(addressId);
    }

    @Test
    @DisplayName("deleteAddress should throw Exception when the Address provided  are not found in DB")
    void deleteAddress_ShouldThrowExceptionWhenAddressNotFoundInDatabase() {
        final UUID addressId = UUID.randomUUID();

        doThrow(new RuntimeException()).when(addressRepository).deleteById(any(UUID.class));

        Assertions.assertThrows(RuntimeException.class, () -> this.addressService.deleteAddress(addressId));
        verify(addressRepository, times(1)).deleteById(addressId);
    }

    /* PUT alterAddress TESTS */

    @Test
    @DisplayName("alterAddress should alter Address provided fields")
    void alterAddress_ShouldAlterAddress() {
        final UUID addressId = UUID.randomUUID();

        Address address = new Address();
        address.setAddressId(addressId);
        address.setState("MG");
        address.setZipCode("00000-000");

        Address newAddress = new Address();
        newAddress.setAddressId(addressId);
        newAddress.setState("SP");
        newAddress.setZipCode("55555-333");

        when(addressRepository.findById(addressId))
                .thenReturn(Optional.of(address));

        when(addressRepository.save(address))
                .thenReturn(address);

        address = addressService.alterAddress(addressId, newAddress);

        Assertions.assertSame("SP", address.getState());
        Assertions.assertNotSame("00000-000", address.getZipCode());
    }

    @Test
    @DisplayName("alterAddress should return null when address are not found on DB and not Save")
    void alterAddress_ShouldReturnNullWhenAddressNotFoundAndNotSave() {
        Address address = new Address();

        when(addressRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        Address responseAddress = addressService.alterAddress(UUID.randomUUID(), new Address());

        Assertions.assertSame(null, responseAddress);
        verify(addressRepository, times(0)).save(address);
    }
}