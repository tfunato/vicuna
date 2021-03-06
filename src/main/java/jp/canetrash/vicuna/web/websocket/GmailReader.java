package jp.canetrash.vicuna.web.websocket;

import jp.canetrash.vicuna.logic.DamageReportMailLogic;
import jp.canetrash.vicuna.web.websocket.ProcessStatus.Status;

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
		if (status.getStatus() == Status.STOPED) {
			damageReportMailLogic.processParseMailWithAsync(status);
		} else {
			logger.info("can't start.:" + status.getStatus());
			status.setStatus(Status.STOPED);
		}
		return status;
	}

	@MessageMapping("/read/status")
	@SendTo("/topic/status")
	public ProcessStatus readStatus() {

		return status;
	}

}
