package com.matera.cadastrocentral.telephone;

import com.matera.cadastrocentral.client.ClientService;
import javassist.tools.web.BadHttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
        return telephoneRepository.findAllByClientId(clientId);
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
            return telephoneRepository.save(new Telephone(telephone));
        } else {
            throw new TelephoneNotFound();
        }
    }

    public Telephone patchTelephonePropertyByTelephoneId(UUID telephoneId, TelephoneDTO telephone) {
        Optional<Telephone> optionalTelephone = telephoneRepository.findById(telephoneId);
        if (optionalTelephone.isPresent()) {
            Telephone auxTelephone = optionalTelephone.get();
            // Check if client input exists
            if (telephone.getClientId() != null) {
                if (clientService.getClientById(telephone.getClientId()).isPresent()) {
                    auxTelephone.setClientId(telephone.getClientId());
                }
            }
            if (telephone.getTelephoneTypeId() != null)
                auxTelephone.setTelephoneTypeId(telephone.getTelephoneTypeId());
            if (StringUtils.isNotBlank(telephone.getNumber())) {
                auxTelephone.setNumber(telephone.getNumber());
            }
            if (StringUtils.isNotBlank(telephone.getDdd())) {
                auxTelephone.setDdd(telephone.getDdd());
            }
            return telephoneRepository.save(auxTelephone);
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
