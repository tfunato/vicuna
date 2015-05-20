package jp.canetrash.vicuna.web.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
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

	protected Log logger = LogFactory
			.getLog(GmailReadProgressStatusHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("Opened new session in instance " + this);
    }
    
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		String echoMessage = message.getPayload();
		logger.info(echoMessage);
		session.sendMessage(new TextMessage(echoMessage));
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		session.close(CloseStatus.SERVER_ERROR);
	}

}
