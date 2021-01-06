package com.matera.cadastrocentral.address;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {


    @Mock
    private AddressRepository addressRepository;

    private AddressService addressService;

    @BeforeEach
    void setUp() {
        this.addressService = new AddressService(addressRepository);
    }

    @Test
    @DisplayName("getAddressById should return an Address that refers to addressId")
    void getAddressById_ShouldReturnAddressReferringToAddressId() {
        final UUID addressId = UUID.randomUUID();
        final UUID clientId = UUID.randomUUID();

        Address address = new Address(addressId, clientId, "Rua tal", "D1", "MG", "000",
                "123", "Esquina", "Lafaiete");

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        Address responseAddress = addressService.getAddressById(addressId);

        Assertions.assertNotNull(responseAddress);
        Assertions.assertEquals(clientId, responseAddress.getClientId());
        Assertions.assertEquals(addressId, responseAddress.getAddressId());
    }

}
