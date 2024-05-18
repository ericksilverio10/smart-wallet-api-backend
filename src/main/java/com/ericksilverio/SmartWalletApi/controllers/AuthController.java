package com.ericksilverio.SmartWalletApi.controllers;

import com.ericksilverio.SmartWalletApi.model.User;
import com.ericksilverio.SmartWalletApi.model.dto.LoginDTO;
import com.ericksilverio.SmartWalletApi.model.dto.TokenDTO;
import com.ericksilverio.SmartWalletApi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Validated LoginDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(new TokenDTO(tokenService.gerarToken((User) authentication.getPrincipal())));
    }
}
