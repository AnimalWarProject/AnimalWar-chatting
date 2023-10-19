package com.example.animalwarchatting;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

//  ## 4
@Controller
@RequiredArgsConstructor
public class GreetingController {
    private final GreetingRepository greetingRepository;
    @MessageMapping("/hello") // #4 "/app" 붙어 있는 건.. 여기서 메시지 받기
    @SendTo("/topic/greetings") // #2 "/topic/greetings"를 구독하고 여기에 메세지를 보내겠다..
    // 메시지를 수신하고 처리한 후, 처리된 결과를 특정 대상 주제(topic)로 다시 보내는 데 사용
    // 받은 메시지를 다시 "/topic/greetings"로 보낸다.(SendTo)
    public Greeting greet(HelloMessage message) {
//
//        Greeting greeting = new Greeting(message.getName(), message.getContent());
        Greeting greeting = Greeting.builder()
                .name(message.getName())
                .message(message.getContent())
                .build();
        greetingRepository.save(greeting);



//        채팅 저장
//        Chatting chattiness = Chatting.builder()
//                .name(greeting.getName())
//                .message(greeting.getMessage())
//                .build();
//        chattingRepository.save(chattiness);

        return greeting;
    }

}
