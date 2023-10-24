package com.example.animalwarchatting.service;

import com.example.animalwarchatting.config.JwtService;
import com.example.animalwarchatting.config.TokenInfo;
import com.example.animalwarchatting.entity.Greeting;
import com.example.animalwarchatting.entity.HelloMessage;
import com.example.animalwarchatting.repository.GreetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingService {
    private final GreetingRepository greetingRepository;

    public Greeting save(HelloMessage message) {
        Greeting greeting = Greeting.builder()
                .name(message.getName())
                .message(message.getContent())
                .build();
        greetingRepository.save(greeting);
        return greeting;
    }
}
