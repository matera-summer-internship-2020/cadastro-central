package com.matera.cadastrocentral.authentication;

import com.matera.cadastrocentral.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PatchMapping("/clients/{clientId}/password")
    void changePassword (@PathVariable UUID clientId, @RequestBody PasswordDTO newPassword) {
        authenticationService.changePassword(clientId, newPassword);
    }
}
