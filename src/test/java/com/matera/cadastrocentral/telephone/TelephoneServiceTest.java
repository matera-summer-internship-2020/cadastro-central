package com.matera.cadastrocentral.telephone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TelephoneServiceTest {

    @Mock
    private TelephoneRepository telephoneRepository;

    private TelephoneService telephoneService;

    @BeforeEach
    void setUp() {
        this.telephoneService = new TelephoneService(telephoneRepository);
    }

    @Test
    @DisplayName("findAllTelephonesByClientId should return empty list when no telephones are found on database")
    void findAllTelephonesByClientId_shouldReturnEmptyList_whenNoTelephonesAreFoundOnDatabase() {
        when(telephoneRepository.findAllByClientId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        final List<Telephone> telephoneList = this.telephoneService.findAllTelephonesByClientId(UUID.randomUUID());

        Assertions.assertTrue(telephoneList.isEmpty());
    }

    @Test
    @DisplayName("findAllTelephonesByClientId should return a telephone list when telephones are found on database")
    void findAllTelephonesByClientId_shouldReturnTelephoneList_whenTelephonesAreFoundOnDatabase() {
        final UUID clientId = UUID.randomUUID();
        final List<Telephone> telephoneList = new ArrayList<>();

        telephoneList.add(new Telephone(UUID.randomUUID(), clientId, 1, "99999-9999", "19"));
        telephoneList.add(new Telephone(UUID.randomUUID(), clientId, 2, "88888-8888", "11"));

        when(telephoneRepository.findAllByClientId(clientId))
                .thenReturn(telephoneList);

        final List<Telephone> responseTelephoneList = this.telephoneService.findAllTelephonesByClientId(clientId);

        Assertions.assertEquals(2, responseTelephoneList.size());
        Assertions.assertEquals(telephoneList.get(0).getTelephoneId(), responseTelephoneList.get(0).getTelephoneId());
        Assertions.assertEquals(telephoneList.get(1).getTelephoneId(), responseTelephoneList.get(1).getTelephoneId());
    }

    @Test
    @DisplayName("findAllTelephonesByClientId should throw same exception as repository")
    void findAllTelephonesByClientId_shouldThrowSameExceptionAsRepository() {
        final UUID clientId = UUID.randomUUID();

        when(telephoneRepository.findAllByClientId(any(UUID.class)))
                .thenThrow(new NullPointerException("something was not initialized"));

        Assertions.assertThrows(NullPointerException.class, () -> this.telephoneService.findAllTelephonesByClientId(clientId));
    }
}
