package com.matera.cadastrocentral.telephone;

import javassist.tools.web.BadHttpRequest;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class TelephoneService {

    @Autowired
    public TelephoneService(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    private final TelephoneRepository telephoneRepository;

    public List<Telephone> findAllTelephonesByClientId(UUID clientId) {
        return telephoneRepository.findAllByClientId(clientId);
    }

    public Telephone findByTelephoneId(UUID clientId, UUID telephoneId) {
        return telephoneRepository.findByTelephoneIdAndClientId(clientId, telephoneId)
                .orElseThrow(() -> new TelephoneNotFound());
    }

    public Telephone insertTelephone(UUID clientId, TelephoneDTO telephone) throws TelephoneAlreadyExists {
        // Checks if the input already exists in database before inserting
        if (telephoneRepository.findAll(Example.of(new Telephone(telephone))).isEmpty()) {
            Telephone auxPhone = new Telephone(telephone);
            auxPhone.setTelephoneId(telephone.getTelephoneId());
            telephoneRepository.save(auxPhone);
            return auxPhone;
        } else {
            throw new TelephoneAlreadyExists();
        }
    }

    public void deleteTelephone(UUID clientId, UUID telephoneId) {
        telephoneRepository.delete(telephoneRepository.findByTelephoneIdAndClientId(clientId, telephoneId)
                .orElseThrow(() -> new TelephoneNotFound()));
    }

    public Telephone alterTelephoneByTelephoneId(TelephoneDTO telephone) {
        Optional<Telephone> optionalTelephone = telephoneRepository.findById(telephone.getTelephoneId());
        if (optionalTelephone.isPresent()) {
            Telephone auxTelephone = optionalTelephone.get();
            auxTelephone.setClientId(Optional.ofNullable(telephone.getClientId())
                                    .orElse(optionalTelephone.get().getClientId()));
            auxTelephone.setTelephoneTypeId(Optional.of(telephone.getTelephoneTypeId())
                                    .orElse(optionalTelephone.get().getTelephoneTypeId()));
            auxTelephone.setNumber(Optional.ofNullable(telephone.getNumber())
                                    .orElse(optionalTelephone.get().getNumber()));
            auxTelephone.setDdd(Optional.ofNullable(telephone.getDdd())
                                    .orElse(optionalTelephone.get().getDdd()));
            telephoneRepository.save(auxTelephone);
            return auxTelephone;
        } else {
            throw new TelephoneNotFound();
        }
    }

    // Exceptions
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Telephone not found")
    public static class TelephoneNotFound extends RuntimeException {
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Client not found")
    public static class ClientNotFound extends RuntimeException {
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Telephone already exists")
    public static class TelephoneAlreadyExists extends BadHttpRequest {
    }
}
