package com.ericksilverio.SmartWalletApi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ericksilverio.SmartWalletApi.model.User;
import com.ericksilverio.SmartWalletApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("auth0").withSubject(user.getId().toString()).withExpiresAt(dataExpiracao()).sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("auth0").build().verify(tokenJWT).getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("erro ao validar token JWT", exception);
        }
    }

    private Instant dataExpiracao()
    {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
