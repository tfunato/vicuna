package jp.canetrash.vicuna.web.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * gmail read status progress pusher
 * 
 * @author tfunato
 *
 */
@Component
public class GmailReadProgressStatusHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> sessionPool = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		this.sessionPool.put(session.getId(), session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		this.sessionPool.remove(session.getId());
	}

}
