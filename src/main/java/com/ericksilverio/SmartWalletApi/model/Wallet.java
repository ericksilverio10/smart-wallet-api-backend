package com.ericksilverio.SmartWalletApi.model;

import javax.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Table(name = "wallet")
@Entity(name = "Wallet")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id ;
    private String name;
    private Double balance;
    private String ownerId;
    private String currency;
}
