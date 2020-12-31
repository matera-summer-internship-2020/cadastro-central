package com.matera.cadastrocentral.telephone;

import javassist.tools.web.BadHttpRequest;
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
    public TelephoneService(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    private final TelephoneRepository telephoneRepository;

    public List<Telephone> findAllTelephonesByClientId(UUID clientId) {
        List<Telephone> phoneList = telephoneRepository.findAllByClientId(clientId);
        if (phoneList.isEmpty()) {
            throw new ClientNotFound();
        } else {
            return phoneList;
        }
    }

    public Optional<Telephone> findByTelephoneId(UUID telephoneId) {
        Optional<Telephone> optionalTelephone = telephoneRepository.findById(telephoneId);
        if (optionalTelephone.isPresent()) {
            return optionalTelephone;
        } else {
            throw new TelephoneNotFound();
        }
    }

    public Telephone insertTelephone(UUID clientId, TelephoneDTO telephone) throws TelephoneAlreadyExists {
        // Checks if the input already exists in database before inserting
        List<Telephone> phoneList = telephoneRepository.findAllByClientId(clientId);
        Stream<Telephone> telephoneStream = phoneList.stream().filter(telephone1 -> telephone1.getNumber()
                                                                                    .equals(telephone.getNumber()));
        Optional<Telephone> sameNumber = telephoneStream.findFirst();
        boolean isExactlyTheSame = false;
        if (sameNumber.isPresent() && sameNumber.get().getTelephoneTypeId() == telephone.getTelephoneTypeId()) {
            if (sameNumber.get().getNumber().equals(telephone.getNumber())) {
                if (sameNumber.get().getDdd().equals(telephone.getDdd())) {
                    isExactlyTheSame = true;
                }
            }
        }

        if (sameNumber.isPresent() && isExactlyTheSame) {
            throw new TelephoneAlreadyExists();
        } else {
            Telephone auxPhone = new Telephone(telephone);
            auxPhone.setTelephoneId(telephone.getTelephoneId());
            telephoneRepository.save(auxPhone);
            return auxPhone;
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
