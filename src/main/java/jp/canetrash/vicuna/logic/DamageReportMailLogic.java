package jp.canetrash.vicuna.logic;

import java.util.concurrent.Future;

import jp.canetrash.vicuna.parser.MailParser;
import jp.canetrash.vicuna.web.websocket.ProcessStatus;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * @author tfunato
 *
 */
@Component
public class DamageReportMailLogic {

	protected Log logger = LogFactory.getLog(DamageReportMailLogic.class);

	@Autowired
	private MailParser jsoupMailParser;

	@Async
	public Future<String> processParseMailWithAsync(ProcessStatus status) {

		System.out.println("It's a called!");
		status.setTotalCount(100);
		try {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(500L);
				status.setProcessCount(i + 1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("call end!");
		return new AsyncResult<String>("result");
	}

}
