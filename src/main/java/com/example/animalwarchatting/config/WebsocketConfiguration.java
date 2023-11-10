package com.example.animalwarchatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


//  ## 3
@Configuration
@EnableWebSocketMessageBroker // 웹소켓 STOMP 통신 기능 활성화.. 메시지 브로커를 사용하여 WebSocket 메시지 처리를 활성화하는 데 사용
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // WebSocket 엔드포인트를 등록, 클라이언트가 해당 엔드포인트를 통해 연결할 수 있도록 설정
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        // #1 socket을 front와 연결
        // 클라이언트에서 웹소켓 STOMP 연결을 위해 지정할 엔드포인트 URL을 등록..
        registry.addEndpoint("/stomp-endpoint").setAllowedOriginPatterns("*") // 'stomp-endpoint'라는 엔드포인트를 등록, 클라이언트는 이 엔드포인트를 통해 WebSocket 연결을 시도
                // .setAllowedOriginPatterns("*") : crossOrigin 설정..
                .withSockJS(); // SockJS를 사용하여 연결,  SockJS는 WebSocket이 지원되지 않는 일부 브라우저에서도 WebSocket 연결을 에뮬레이트하게 도와줍니다.
    }

    // 1.메시지 중개기 URL을 설정합니다, 2.메시지 핸들러 메서드 URL을 설정합니다
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // 메시지 브로커를 구성하는 역할
        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);

        // #2 url에서 "/topic"을 접두사로 가지고 있는 건 구독하는 하는 것이다..
        //도착지가 /topic으로 시작하는 메시지는 중개기로 보냅니다
        registry.enableSimpleBroker("/topic"); // "/topic"으로 시작하는 대상 주제를 구독하는 클라이언트에게 메시지를 브로드캐스트하는 데 사용, 이것은 메시지를 구독하는 클라이언트들에게 메시지를 보내는 메시지 브로커를 활성화

        // #4 url에서 "/app"을 접두사로 가지고 있는 게 있으면 메시지 보내는 것이다..
        //  /app으로 시작하는 메시지는 @MassageMapping을 붙인 핸들러 메서드로 보냅니다
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 메시지를 송신할 때 사용하는 목적지 접두어(prefix)를 설정합니다. 즉, 클라이언트가 "/app"으로 시작하는 메시지 목적지에 메시지를 송신할 수 있습니다.
    }
}
