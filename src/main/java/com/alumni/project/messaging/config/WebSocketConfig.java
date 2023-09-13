package com.alumni.project.messaging.config;


import com.alumni.project.messaging.handler.UserHandshakeHandler;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(@NotNull final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    @Override
    public void registerStompEndpoints(@NotNull final StompEndpointRegistry registry) {
        registry.addEndpoint("/webSocket")
                .setHandshakeHandler(new UserHandshakeHandler())
                ;
        registry.addEndpoint("/webSocketjs")
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS()
        ;
    }

}
