package org.example.backendshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Thông báo đến client qua "/topic"
        config.setApplicationDestinationPrefixes("/app"); // Tiền tố cho các request từ client

        // Nếu sử dụng RabbitMQ/ActiveMQ, thay thế bằng:
        // config.enableStompBrokerRelay("/topic", "/queue")
        //       .setRelayHost("localhost")
        //       .setRelayPort(61613)
        //       .setClientLogin("guest")
        //       .setClientPasscode("guest");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000", "https://yourdomain.com") // Chỉ cho phép origin cụ thể
                .withSockJS(); // Hỗ trợ fallback khi WebSocket không khả dụng
    }
}
