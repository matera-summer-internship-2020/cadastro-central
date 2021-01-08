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
    @GetMapping("/client/{clientId}/telephones")
    public List<Telephone> getAllByClientId(@PathVariable("clientId") UUID clientId) {
        return telephoneService.findAllTelephonesByClientId(clientId);
    }

    // 2. List one specific telephone if it exists
    @GetMapping("/{telephoneId}/telephone")
    public Optional<Telephone> getByTelephoneId(@PathVariable("telephoneId") UUID telephoneId) {
        if (telephoneService.findByTelephoneId(telephoneId).isPresent()) {
            return telephoneService.findByTelephoneId(telephoneId);
        } else {
            throw new TelephoneService.TelephoneNotFound();
        }
    }

    // 3. Insert one telephone for one client
    @PostMapping("/client/{clientId}/telephone")
    public Telephone insertTelephone(@PathVariable("clientId") UUID clientId,
                                     @RequestBody @Validated TelephoneDTO telephone)
            throws TelephoneService.TelephoneAlreadyExists {
        telephone.getClientId().setClientId(clientId);
        return telephoneService.insertTelephone(clientId, telephone);
    }

    // 4. Delete a specific telephone
    @DeleteMapping("/{telephoneId}/telephone")
    public void deleteTelephone(@PathVariable("telephoneId") UUID telephoneId) {
        telephoneService.deleteTelephone(telephoneId);
    }

    // 5. Find specific telephone for specific client
    @GetMapping("/client/{clientId}/telephone/{telephoneId}")
    public Optional<Telephone> getByClientIdAndTelephoneId(@PathVariable("clientId") UUID clientId,
                                                           @PathVariable("telephoneId") UUID telephoneId) {
        return telephoneService.findByClientIdAndTelephoneId(clientId, telephoneId);
    }

    // 6. Update telephone in database
    @PutMapping("/telephone")
    public Telephone alterTelephoneByTelephoneId(@RequestBody @Validated TelephoneDTO telephone) {
        return telephoneService.alterTelephoneByTelephoneId(telephone);
    }


}
