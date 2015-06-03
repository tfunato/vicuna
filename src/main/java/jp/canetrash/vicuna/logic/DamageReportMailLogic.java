package jp.canetrash.vicuna.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.mail.MessagingException;

import jp.canetrash.vicuna.web.websocket.ProcessStatus;
import jp.canetrash.vicuna.web.websocket.ProcessStatus.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.google.api.services.gmail.Gmail.Users.Messages;
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
	private ReadMailLogic readMailLogic;

	protected Log logger = LogFactory.getLog(DamageReportMailLogic.class);

	private static final String SEARCH_CONDITION = "subject:\"Ingress Damage Report:\" is:unread";

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
					.setQ(SEARCH_CONDITION).setMaxResults(1000L).execute();
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

			List<List<String>> splitIdList = splitIdList(msgIdList);
			final CountDownLatch latch = new CountDownLatch(splitIdList.size());
			List<Future<String>> results = new ArrayList<>();
			for (List<String> ids : splitIdList) {
				results.add(readMailLogic.parallelReadMailWithAsync(latch, messages, ids,
						status));
			}
			latch.await();
			for (Future<String> res : results) {
				logger.info(res.get());
			}
			status.setStatus(Status.STOPED); // mark stoped
			logger.info("END read gmail");

		} catch (IOException | MessagingException | InterruptedException
				| ExecutionException e) {
			e.printStackTrace();
			status.setStatus(Status.ERROR); // mark error
			throw new RuntimeException(e);
		}
		return new AsyncResult<String>("result");
	}

	private List<List<String>> splitIdList(List<String> msgIdList) {

		int size = 5; // threads
		if (msgIdList == null || msgIdList.isEmpty()) {
			return Collections.emptyList();
		}
		List<List<String>> devidedList = new ArrayList<List<String>>();

		if (msgIdList.size() <= 200) {
			devidedList.add(msgIdList);
			return devidedList;
		}

		int block = msgIdList.size() / size + msgIdList.size() % size;

		for (int i = 0; i < size; i++) {
			int start = i * block;
			int end = Math.min(start + block, msgIdList.size());
			devidedList
					.add(new ArrayList<String>(msgIdList.subList(start, end)));
		}
		return devidedList;
	}
}
