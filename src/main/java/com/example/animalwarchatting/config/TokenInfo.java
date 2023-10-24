package com.example.animalwarchatting.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Builder @Getter
public class TokenInfo {
    private String id;
    private String nickName;
}

