package jp.canetrash.vicuna.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import jp.canetrash.vicuna.dao.DamagePortalDao;
import jp.canetrash.vicuna.dao.DamageReportMailDao;
import jp.canetrash.vicuna.entity.DamagePortalEntity;
import jp.canetrash.vicuna.entity.DamageReportMailEntity;
import jp.canetrash.vicuna.parser.DamageReportMail;
import jp.canetrash.vicuna.parser.MailParser;
import jp.canetrash.vicuna.parser.Portal;
import jp.canetrash.vicuna.web.websocket.ProcessStatus;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail.Users.Messages;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ModifyMessageRequest;

@Component
public class ReadMailLogic {

	protected Log logger = LogFactory.getLog(ReadMailLogic.class);

	@Autowired
	private DamageReportMailDao damageReportMailDao;

	@Autowired
	private DamagePortalDao damagePortalDao;

	@Autowired
	private MailParser jsoupMailParser;

	@Async
	public Future<String> parallelReadMailWithAsync(CountDownLatch latch,
			Messages messages, List<String> msgIdList, ProcessStatus status)
			throws IOException, MessagingException {

		logger.info("processing...[" + msgIdList.size() + "]");
		int cnt = 0;

		List<String> removeLabels = Arrays.asList(new String[] { "UNREAD" });
		try {
			for (String msgId : msgIdList) {
				cnt++;
				Message msg = messages.get(OAuthLogic.USER, msgId)
						.setFormat("raw").execute();
				byte[] emailBytes = Base64.decodeBase64(msg.getRaw());

				MimeMessage email = new MimeMessage(null,
						new ByteArrayInputStream(emailBytes));

				// store database
				storeDamageMail(jsoupMailParser.parse(email));

				// marks as 'read' at this mail
				messages.modify(
						OAuthLogic.USER,
						msgId,
						new ModifyMessageRequest()
								.setRemoveLabelIds(removeLabels)).execute();
				status.incrementCounter();
				if (cnt % 200 == 0) {
					logger.info(cnt + " done.");
				}
			}
			logger.info("processing done.");
			return new AsyncResult<String>(Thread.currentThread().getName()
					+ ":" + msgIdList.size());
		} finally {
			latch.countDown();
		}
	}

	/**
	 * store mail
	 * 
	 * @param mail
	 */
	private synchronized void storeDamageMail(DamageReportMail mail) {
		if (mail == null) {
			return;
		}
		// exist check
		if (this.damageReportMailDao.exists(mail.getMessageId())) {
			return;
		}

		DamageReportMailEntity mailEntity = new DamageReportMailEntity();
		mailEntity.setMessageId(mail.getMessageId());
		mailEntity.setAttackDate(mail.getDate());
		mailEntity.setOppsiteAgentName(mail.getOppositeAgentName());
		mailEntity.setCreateDate(new Date());
		this.damageReportMailDao.save(mailEntity);

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
			this.damagePortalDao.save(portalEntity);
		}
	}
}
