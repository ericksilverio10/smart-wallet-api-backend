package com.ericksilverio.SmartWalletApi.repositories;

import com.ericksilverio.SmartWalletApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByLogin(String username);
    Optional<UserDetails> findByEmail(String username);
}
