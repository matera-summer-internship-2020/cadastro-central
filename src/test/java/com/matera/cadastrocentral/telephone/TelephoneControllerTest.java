package com.matera.cadastrocentral.telephone;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TelephoneController.class)
class TelephoneControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TelephoneService telephoneService;

    @Test
    @DisplayName("getAllByClientId should return 200 with empty list when no telephones are found")
    void getAllByClientId_shouldReturn200StatusWithEmptyListBody_whenNoTelephonesFound() throws Exception {
        when(telephoneService.findAllTelephonesByClientId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/client/{clientId}/telephones", UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("getAllByClientId should return 200 with telephone list when telephones are found")
    void getAllByClientId_shouldReturn200StatusWithTelephoneListBody_whenTelephonesFound() throws Exception {
        final UUID clientId = UUID.randomUUID();
        final List<Telephone> telephoneList = new ArrayList<>();

        telephoneList.add(new Telephone(UUID.randomUUID(), clientId, 1, "99999-9999", "19"));
        telephoneList.add(new Telephone(UUID.randomUUID(), clientId, 2, "88888-8888", "11"));

        when(telephoneService.findAllTelephonesByClientId(clientId))
                .thenReturn(telephoneList);

        this.mockMvc.perform(get("/client/{clientId}/telephones", clientId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(telephoneList)));
    }

    @Test
    @DisplayName("getAllByClientId should return 500 when service throws error")
    void getAllByClientId_shouldReturn500_whenServiceThrowsError() throws Exception {
        when(telephoneService.findAllTelephonesByClientId(any(UUID.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "service internal error"));

        this.mockMvc.perform(get("/client/{clientId}/telephones", UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("getAllByClientId should return 400 when invalid clientId UUID is present on request path")
    void getAllByClientId_shouldReturn400_whenInvalidClientIdIsPresentOnPath() throws Exception {
        this.mockMvc.perform(get("/client/{clientId}/telephones", "invalid_UUID"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("insertTelephone should return 200 with a telephone when payload is correct")
    void insertTelephone_shouldReturn200WithTelephone_whenPayloadIsCorrect() throws Exception {
        final UUID clientId = UUID.randomUUID();
        final UUID telephoneId = UUID.randomUUID();

        final TelephoneDTO telephoneDTO = new TelephoneDTO();
        telephoneDTO.setNumber("99999-9999");
        telephoneDTO.setTelephoneTypeId(1);
        telephoneDTO.setDdd("19");

        final Telephone telephone = new Telephone(telephoneId, clientId, telephoneDTO.getTelephoneTypeId(), telephoneDTO.getNumber(), telephoneDTO.getDdd());

        when(telephoneService.insertTelephone(eq(clientId), any(TelephoneDTO.class)))
                .thenReturn(telephone);

        this.mockMvc.perform(post("/client/{clientId}/telephone", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(telephoneDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(telephone)));
    }
}
