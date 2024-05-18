package com.ericksilverio.SmartWalletApi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String login;
    private String password;
}
