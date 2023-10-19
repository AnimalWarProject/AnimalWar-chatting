package com.example.animalwarchatting.Service;

import com.example.animalwarchatting.Entity.Greeting;
import com.example.animalwarchatting.Entity.HelloMessage;
import com.example.animalwarchatting.Repository.GreetingRepository;
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
