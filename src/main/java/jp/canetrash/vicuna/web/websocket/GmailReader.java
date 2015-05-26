package jp.canetrash.vicuna.web.websocket;

import jp.canetrash.vicuna.logic.DamageReportMailLogic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GmailReader {

	@Autowired
	private DamageReportMailLogic damageReportMailLogic;

	private ProcessStatus status = new ProcessStatus();

	private static Log logger = LogFactory.getLog(GmailReader.class);

	@MessageMapping("/read/start")
	@SendTo("/topic/start")
	public ProcessStatus readStart() {
		logger.info("start read gmail");
		damageReportMailLogic.processParseMailWithAsync(status);
		return status;
	}

	@MessageMapping("/read/status")
	@SendTo("/topic/status")
	public ProcessStatus readStatus() {

		return status;

	}

}
