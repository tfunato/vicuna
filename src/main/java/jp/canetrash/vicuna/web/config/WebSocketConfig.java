package jp.canetrash.vicuna.web.config;

import jp.canetrash.vicuna.web.websocket.GmailReadProgressStatusHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private GmailReadProgressStatusHandler gmailReadProgressStatusHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		registry.addHandler(gmailReadProgressStatusHandler, "/readGmail").withSockJS();
	}
}
