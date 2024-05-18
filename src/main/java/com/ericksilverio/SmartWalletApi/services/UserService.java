package com.ericksilverio.SmartWalletApi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ericksilverio.SmartWalletApi.model.User;
import com.ericksilverio.SmartWalletApi.model.dto.UserDTO;
import com.ericksilverio.SmartWalletApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity create(UserDTO userData) {

        var encryptPwd = passwordEncoder.encode(userData.getPassword());

        if (userRepository.findByEmail(userData.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("e-mail já cadastrado!");
        }

        if (userRepository.findByLogin(userData.getLogin()).isPresent()) {
            return ResponseEntity.badRequest().body("nome de usuário já existente!");
        }

        var user = new User(UUID.randomUUID(), userData.getLogin(), encryptPwd, userData.getFullname(), userData.getEmail(), userData.getPhonenumber());

        var savedUser = userRepository.save(user);

        return ResponseEntity.ok(savedUser);
    }

    public ResponseEntity findById(String userId) {

        var user = userRepository.findById(UUID.fromString(userId));

        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("usuário não encontrado!");
        }

        return ResponseEntity.ok(user);
    }

    public ResponseEntity findAll() {

        var users = userRepository.findAll();


        return ResponseEntity.ok(users);
    }
}
