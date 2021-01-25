package com.matera.cadastrocentral.telephone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TelephoneController {
    // Controller -> Service -> Repository
    // Controller <- DTO

    private final TelephoneService telephoneService;

    @Autowired
    public TelephoneController(TelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }

    // 1. List all telephones from one client
    @GetMapping("/clients/{clientId}/telephones")
    public List<Telephone> getAllByClientId(@PathVariable("clientId") UUID clientId) {
        return telephoneService.findAllTelephonesByClientId(clientId);
    }

    // 2. List one specific telephone if it exists in given client's set
    @GetMapping("/clients/{clientId}/telephones/{telephoneId}")
    public Telephone getByTelephoneId(@PathVariable("clientId") UUID clientId, @PathVariable("telephoneId") UUID telephoneId) {
        return telephoneService.findByTelephoneId(clientId, telephoneId);
    }

    // 3. Insert one telephone for one client
    @PostMapping("/clients/{clientId}/telephones")
    public Telephone insertTelephone(@PathVariable("clientId") UUID clientId,
                                     @RequestBody @Validated TelephoneDTO telephone)
            throws TelephoneService.TelephoneAlreadyExists {
        telephone.setClientId(clientId);
        return telephoneService.insertTelephone(clientId, telephone);
    }

    // 4. Delete a specific telephone
    @DeleteMapping("clients/{clientId}/telephones/{telephoneId}")
    public void deleteTelephone(@PathVariable("clientId") UUID clientId, @PathVariable("telephoneId") UUID telephoneId) {
        telephoneService.deleteTelephone(clientId, telephoneId);
    }

    // 5. Update telephone in database
    @PutMapping("/clients/{clientId}/telephones/{telephoneId}")
    public Telephone alterTelephoneByTelephoneId(@RequestBody @Validated TelephoneDTO telephone) {
        return telephoneService.alterTelephoneByTelephoneId(telephone);
    }


}
