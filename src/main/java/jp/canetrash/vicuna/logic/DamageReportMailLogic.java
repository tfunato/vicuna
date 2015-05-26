package jp.canetrash.vicuna.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import jp.canetrash.vicuna.parser.DamageReportMail;
import jp.canetrash.vicuna.parser.JsoupMailParser;
import jp.canetrash.vicuna.parser.MailParser;
import jp.canetrash.vicuna.web.websocket.ProcessStatus;
import jp.canetrash.vicuna.web.websocket.ProcessStatus.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * @author tfunato
 *
 */
@Component
public class DamageReportMailLogic {

	@Autowired
	private OAuthLogic oAuthLogic;

	protected Log logger = LogFactory.getLog(DamageReportMailLogic.class);

	private static final String SEARCH_CONDITION = "subject:\"Ingress Damage Report:\" is:unread";

	@Autowired
	private MailParser jsoupMailParser;

	@Async
	public Future<String> processParseMailWithAsync(ProcessStatus status) {

		status.setStatus(Status.PREPARING);
		status.setTotalCount(0);
		status.setProcessCount(0);

		JsoupMailParser parser = new JsoupMailParser();

		Gmail service = oAuthLogic.getGmailService();
		List<String> msgIdList = new ArrayList<>();
		try {
			ListMessagesResponse response = service.users().messages()
					.list(OAuthLogic.USER).setQ(SEARCH_CONDITION).execute();
			for (Message msg : response.getMessages()) {
				msgIdList.add(msg.getId());
			}
			while (response.getNextPageToken() != null) {
				response = service.users().messages().list(OAuthLogic.USER)
						.setQ(SEARCH_CONDITION)
						.setPageToken(response.getNextPageToken()).execute();
				for (Message msg : response.getMessages()) {
					msgIdList.add(msg.getId());
				}
			}
			status.setTotalCount(msgIdList.size());
			status.setStatus(Status.RUNNING);

			int counter = 0;
			for (String msgId : msgIdList) {
				Message msg = service.users().messages()
						.get(OAuthLogic.USER, msgId).setFormat("raw").execute();
				byte[] emailBytes = Base64.decodeBase64(msg.getRaw());

				MimeMessage email = new MimeMessage(null,
						new ByteArrayInputStream(emailBytes));
				DamageReportMail drm = parser.parse(email);
				status.setProcessCount(counter++);
			}
			status.setStatus(Status.STOPED);

		} catch (IOException | MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return new AsyncResult<String>("result");
	}

}
