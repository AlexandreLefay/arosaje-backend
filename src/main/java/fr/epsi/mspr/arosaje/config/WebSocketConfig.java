package fr.epsi.mspr.arosaje.config;

import fr.epsi.mspr.arosaje.handler.ChatHandler;
import fr.epsi.mspr.arosaje.handler.TutorialHandler;
import fr.epsi.mspr.arosaje.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageService messageService;

    public WebSocketConfig(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(tutorialHandler(), "/tutorial")
                .setAllowedOrigins("*");
        registry.addHandler(chatHandler(), "/chat")
                .setAllowedOrigins("*");
    }

    @Bean
    WebSocketHandler tutorialHandler() {
        return new TutorialHandler();
    }

    @Bean
    public WebSocketHandler chatHandler() {
        return new ChatHandler(messageService);
    }
}
