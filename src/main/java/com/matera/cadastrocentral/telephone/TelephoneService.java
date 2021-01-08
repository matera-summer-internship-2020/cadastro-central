package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.client.Client;
import com.matera.cadastrocentral.client.ClientService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class TelephoneService {

    @Autowired
    public TelephoneService(TelephoneRepository telephoneRepository, ClientService clientService) {
        this.telephoneRepository = telephoneRepository;
        this.clientService = clientService;
    }

    private final TelephoneRepository telephoneRepository;
    private final ClientService clientService;

    public List<Telephone> findAllTelephonesByClientId(UUID clientId) {
        Client client = clientService.getClientById(clientId).orElseThrow(ClientNotFound::new);
        return telephoneRepository.findAllByClientId(client);
    }

    public Optional<Telephone> findByTelephoneId(UUID telephoneId) {
        return telephoneRepository.findById(telephoneId);
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

    public void deleteTelephone(UUID telephoneId) {
        Optional<Telephone> telephoneOptional = telephoneRepository.findById(telephoneId);
        if (telephoneOptional.isPresent()) {
            telephoneRepository.deleteById(telephoneId);
        } else {
            throw new TelephoneNotFound();
        }
    }

    public Optional<Telephone> findByClientIdAndTelephoneId(UUID clientId, UUID telephoneId) {
        List<Telephone> clientList = findAllTelephonesByClientId(clientId);
        Stream<Telephone> telephoneStream = clientList.stream().filter(telephone -> telephone.getTelephoneId().equals(telephoneId));
        Optional<Telephone> telephoneSpecific = telephoneStream.findFirst();
        if (telephoneSpecific.isPresent()) {
            return telephoneSpecific;
        } else if (clientList.isEmpty()) {
            throw new ClientNotFound();
        } else {
            throw new TelephoneNotFound();
        }
    }

    public Telephone alterTelephoneByTelephoneId(TelephoneDTO telephone) {
        Optional<Telephone> optionalTelephone = telephoneRepository.findById(telephone.getTelephoneId());
        if (optionalTelephone.isPresent()) {
            Telephone auxTelephone = optionalTelephone.get();
            auxTelephone.getClientId().setClientId(Optional.ofNullable(telephone.getClientId().getClientId())
                                    .orElse(optionalTelephone.get().getClientId().getClientId()));
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
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "telephone not found")
    public static class TelephoneNotFound extends RuntimeException {
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "client not found")
    public static class ClientNotFound extends RuntimeException {
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "telephone already exists")
    public static class TelephoneAlreadyExists extends BadHttpRequest {
    }
}
