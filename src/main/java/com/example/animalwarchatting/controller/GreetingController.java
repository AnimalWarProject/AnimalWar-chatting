package com.example.animalwarchatting.controller;

import com.example.animalwarchatting.config.JwtService;
import com.example.animalwarchatting.entity.Greeting;
import com.example.animalwarchatting.entity.HelloMessage;
import com.example.animalwarchatting.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

//  ## 4
@RestController
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingService greetingService;
    private final JwtService jwtService;
    @MessageMapping("/hello") // #4 "/app" 붙어 있는 건.. 여기서 메시지 받기
    @SendTo("/topic/greetings") // #2 "/topic/greetings"를 구독하고 여기에 메세지를 보내겠다..
    // 메시지를 수신하고 처리한 후, 처리된 결과를 특정 대상 주제(topic)로 다시 보내는 데 사용
    // 받은 메시지를 다시 "/topic/greetings"로 보낸다.(SendTo)
    public Greeting greet(HelloMessage message) {
        return greetingService.save(message);
    }
}
