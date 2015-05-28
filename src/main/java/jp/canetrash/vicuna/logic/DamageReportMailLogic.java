package jp.canetrash.vicuna.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import jp.canetrash.vicuna.entity.DamagePortalEntity;
import jp.canetrash.vicuna.entity.DamageReportMailEntity;
import jp.canetrash.vicuna.parser.DamageReportMail;
import jp.canetrash.vicuna.parser.MailParser;
import jp.canetrash.vicuna.parser.Portal;
import jp.canetrash.vicuna.repository.DamagePortalRepository;
import jp.canetrash.vicuna.repository.DamageReportMailRepository;
import jp.canetrash.vicuna.web.websocket.ProcessStatus;
import jp.canetrash.vicuna.web.websocket.ProcessStatus.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail.Users.Messages;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ModifyMessageRequest;

/**
 * @author tfunato
 *
 */
@Component
public class DamageReportMailLogic {

	@Autowired
	private OAuthLogic oAuthLogic;

	@Autowired
	private DamageReportMailRepository damageReportMailRepository;

	@Autowired
	private DamagePortalRepository damagePortalRepository;

	protected Log logger = LogFactory.getLog(DamageReportMailLogic.class);

	private static final String SEARCH_CONDITION = "subject:\"Ingress Damage Report:\" is:unread";

	@Autowired
	private MailParser jsoupMailParser;

	@Async
	public Future<String> processParseMailWithAsync(ProcessStatus status) {

		logger.info("START read gmail");

		status.setStatus(Status.PREPARING); // mark prepare
		status.setTotalCount(0);
		status.setProcessCount(0);

		Messages messages = oAuthLogic.getGmailService().users().messages();

		List<String> msgIdList = new ArrayList<>();
		try {
			ListMessagesResponse response = messages.list(OAuthLogic.USER)
					.setQ(SEARCH_CONDITION).execute();
			if (response.getResultSizeEstimate() == 0 || response.size() == 1) { // api
																					// bug?
				logger.info("no mail for processing");
				status.setStatus(Status.STOPED);
				return new AsyncResult<String>("result");
			}
			logger.info("read mail ids...");
			List<Message> messageList = response.getMessages();
			for (Message msg : messageList) {
				msgIdList.add(msg.getId());
			}
			while (response.getNextPageToken() != null) {
				response = messages.list(OAuthLogic.USER)
						.setQ(SEARCH_CONDITION)
						.setPageToken(response.getNextPageToken()).execute();
				if (response.getResultSizeEstimate() == 0
						|| response.size() == 1) { // api bug?
					break;
				}
				for (Message msg : response.getMessages()) {
					msgIdList.add(msg.getId());
					if (msgIdList.size() % 1000 == 0) {
						logger.info("reading ids:" + msgIdList.size());
					}
				}
			}
			status.setTotalCount(msgIdList.size());
			logger.info("Total count:" + status.getTotalCount());
			status.setStatus(Status.RUNNING); // mark running

			List<String> removeLabels = Arrays
					.asList(new String[] { "UNREAD" });
			for (String msgId : msgIdList) {
				Message msg = messages.get(OAuthLogic.USER, msgId)
						.setFormat("raw").execute();
				byte[] emailBytes = Base64.decodeBase64(msg.getRaw());

				MimeMessage email = new MimeMessage(null,
						new ByteArrayInputStream(emailBytes));
				// store to database
				storeDamageMail(jsoupMailParser.parse(email));

				// marks as 'read' at this mail
				messages.modify(
						OAuthLogic.USER,
						msgId,
						new ModifyMessageRequest()
								.setRemoveLabelIds(removeLabels)).execute();
				status.incrementCounter();
				if (status.getProcessCount() % 1000 == 0) {
					logger.info("processing..." + status.getProcessCount());
				}
			}
			status.setStatus(Status.STOPED); // mark stoped
			logger.info("END read gmail");

		} catch (IOException | MessagingException e) {
			e.printStackTrace();
			status.setStatus(Status.ERROR); // mark error
			throw new RuntimeException(e);
		}
		return new AsyncResult<String>("result");
	}

	/**
	 * store mail
	 * 
	 * @param mail
	 */
	private void storeDamageMail(DamageReportMail mail) {
		if (mail == null) {
			return;
		}
		// exist check
		if (damagePortalRepository.exists(mail.getMessageId())) {
			return;
		}
		DamageReportMailEntity mailEntity = new DamageReportMailEntity();
		mailEntity.setMessageId(mail.getMessageId());
		mailEntity.setAttackDate(mail.getDate());
		mailEntity.setOppsiteAgentName(mail.getOppositeAgentName());
		mailEntity.setCreateDate(new Date());
		this.damageReportMailRepository.save(mailEntity);

		int seq = 0;
		for (Portal portal : mail.getPortals()) {
			DamagePortalEntity portalEntity = new DamagePortalEntity(UUID
					.randomUUID().toString());
			portalEntity.setMessageId(mail.getMessageId());
			portalEntity.setSeq(seq++);
			portalEntity.setPortalName(portal.getPortalName());
			portalEntity.setPortalIntelUrl(portal.getPortalIntelUrl());
			portalEntity.setLongitude(Float.parseFloat(portal.getLongitude()));
			portalEntity.setLatitude(Float.parseFloat(portal.getLatitude()));
			portalEntity.setCreateDate(new Date());
			this.damagePortalRepository.save(portalEntity);
		}
	}
}
