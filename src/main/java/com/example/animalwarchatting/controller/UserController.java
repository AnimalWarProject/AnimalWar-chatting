package com.example.animalwarchatting.controller;

import com.example.animalwarchatting.config.JwtService;
import com.example.animalwarchatting.config.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final JwtService jwtService;

    @GetMapping("/me")
    public TokenInfo me(@RequestHeader("Authorization") String accessToken) {
        return jwtService.parseAccessToken(accessToken.replace("Bearer ", ""));
    }
}
