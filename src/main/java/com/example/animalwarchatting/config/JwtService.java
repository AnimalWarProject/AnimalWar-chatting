package com.example.animalwarchatting.config;



import com.example.animalwarchatting.entity.User;
import com.example.animalwarchatting.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    public TokenInfo parseAccessToken(String accesstoken) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(accesstoken)
                .getBody();

        return TokenInfo.builder()
                .id((String) body.get("id"))
                .nickName((String) body.get("nickName"))
                .build();
    }
}
