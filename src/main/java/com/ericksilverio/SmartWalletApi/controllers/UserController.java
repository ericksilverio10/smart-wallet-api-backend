package com.ericksilverio.SmartWalletApi.controllers;

import com.ericksilverio.SmartWalletApi.model.User;
import com.ericksilverio.SmartWalletApi.model.Wallet;
import com.ericksilverio.SmartWalletApi.model.dto.UserDTO;
import com.ericksilverio.SmartWalletApi.repositories.UserRepository;
import com.ericksilverio.SmartWalletApi.repositories.WalletRepository;
import com.ericksilverio.SmartWalletApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody @Validated UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return userService.findAll();
    }
}
