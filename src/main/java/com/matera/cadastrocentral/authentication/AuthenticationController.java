package com.matera.cadastrocentral.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PatchMapping("{clientId}/change-password")
    void changePassword (@PathVariable UUID clientId, @RequestBody PasswordDTO newPassword) {
        authenticationService.changePassword(clientId, newPassword);
    }

    @PostMapping("/login")
    void loginValidation(@RequestBody final LoginDTO loginDTO){
        authenticationService.loginValidation(loginDTO);
    }

    @PostMapping("{clientId}/validate-password")
    void passwordValidation(@PathVariable UUID clientId, @RequestBody PasswordDTO clientPassword){
        authenticationService.passwordValidation(clientId, clientPassword);
    }
}
