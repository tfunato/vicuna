package jp.canetrash.vicuna.web.websocket;

import jp.canetrash.vicuna.logic.DamageReportMailLogic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private ProcessStatus status;

	@Autowired
	private DamageReportMailLogic damageReportMailLogic;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.info("Opened new session in instance " + this);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		String msg = message.getPayload();
		logger.info(msg);
		if (msg.startsWith("start")) {
			status = new ProcessStatus();
			damageReportMailLogic.processParseMailWithAsync(status);
			session.sendMessage(new TextMessage("OK"));
			logger.info("started");
		} else if (msg.startsWith("status")) {
			session.sendMessage(new TextMessage(Integer.toString(status.getProcessCount())));
			logger.info("status!");
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		session.close(CloseStatus.SERVER_ERROR);
	}

}
