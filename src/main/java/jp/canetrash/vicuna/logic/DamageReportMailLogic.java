package jp.canetrash.vicuna.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

		status.setStatus(Status.PREPARING);
		status.setTotalCount(0);
		status.setProcessCount(0);

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
				if (response.isEmpty()) {
					break;
				}
				for (Message msg : response.getMessages()) {
					msgIdList.add(msg.getId());
				}
			}
			status.setTotalCount(msgIdList.size());
			logger.info("Total count:" + status.getTotalCount());
			status.setStatus(Status.RUNNING);

			int counter = 1;
			for (String msgId : msgIdList) {
				Message msg = service.users().messages()
						.get(OAuthLogic.USER, msgId).setFormat("raw").execute();
				byte[] emailBytes = Base64.decodeBase64(msg.getRaw());

				MimeMessage email = new MimeMessage(null,
						new ByteArrayInputStream(emailBytes));
				storeDamageMail(jsoupMailParser.parse(email));
				status.setProcessCount(counter++);
				if (status.getProcessCount() % 1000 == 0) {
					logger.info("processing..." + status.getProcessCount());
				}
			}
			status.setStatus(Status.STOPED);

		} catch (IOException | MessagingException e) {
			e.printStackTrace();
			status.setStatus(Status.ERROR);
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
