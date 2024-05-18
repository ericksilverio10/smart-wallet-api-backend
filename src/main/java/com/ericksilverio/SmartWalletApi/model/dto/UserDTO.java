package com.ericksilverio.SmartWalletApi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String login;
    private String password;
    private String fullname;
    private String email;
    private String phonenumber;
}
