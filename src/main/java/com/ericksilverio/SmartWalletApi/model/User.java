package com.ericksilverio.SmartWalletApi.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table(name="users")
@Entity(name="User")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String login;
    private String password;
    private String fullname;
    private String email;
    private String phonenumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
