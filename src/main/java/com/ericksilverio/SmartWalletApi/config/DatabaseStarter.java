package com.ericksilverio.SmartWalletApi.config;


import com.ericksilverio.SmartWalletApi.model.User;
import com.ericksilverio.SmartWalletApi.model.Wallet;
import com.ericksilverio.SmartWalletApi.repositories.UserRepository;
import com.ericksilverio.SmartWalletApi.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.Long.valueOf;

@Component
public class DatabaseStarter implements CommandLineRunner {

    @Autowired
    private final WalletRepository walletRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public DatabaseStarter(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (walletRepository.count() == 0) {
            List<Wallet> wallets = generateFakeWallets();
            walletRepository.saveAll(wallets);
        }

        if (userRepository.count() == 0){
            User user = criarUsuario();

            userRepository.save(user);
        }
    }

    private List<Wallet> generateFakeWallets() {
        List<Wallet> wallets = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Wallet wallet = new Wallet();
            wallet.setName("Wallet " + i);
            wallet.setBalance(Math.random() * 10000);
            wallet.setCurrency("BRL");
            wallet.setOwnerId(UUID.randomUUID().toString());
            wallets.add(wallet);
        }


        return wallets;
    }

    private User criarUsuario() {
        User user = new User(UUID.randomUUID(),"erickblass", "$2a$12$IPhY3N9UB5rxdnBRfTBtkOa1V2cRsDxjqqMSHAHz5QQTCIDIrPYhq", "Erick Silvério Blass", "erick1junior@hotmail.com", "11941822723");

        return user;
    }
}
