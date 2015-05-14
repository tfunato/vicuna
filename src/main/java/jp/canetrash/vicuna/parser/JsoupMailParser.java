package jp.canetrash.vicuna.parser;

import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * simple damage mail parser.<br/>
 * using jsop
 * 
 * @author tfunato
 *
 */
@Component
public class JsoupMailParser extends AbstractParser {

	private static final String AGENT_NAME_CSSSELECTOR = "body > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td > span:nth-child(2)";
	private static final String OPS_AGENT_NAME_CSSSELECTOR = "body > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(5) > td > table > tbody > tr > td:nth-child(1) > div > span";

	@Override
	public DamageReportMail parse(Message msg) {

		String subject = getSubject(msg);
		if (subject == null || !subject.startsWith("Ingress Damage Report")) {
			return null;
		}

		DamageReportMail mail = new DamageReportMail();
		mail.setSubject(subject);
		InternetAddress from = getFrom(msg);
		if (from != null) {
			mail.setFrom(from.getPersonal());
		}
		InternetAddress to = getTo(msg);
		if (to != null) {
			mail.setTo(to.getPersonal());
		}

		try {
			mail.setDate(msg.getSentDate());
			mail.setMessageId(msg.getHeader("Message-ID")[0].toString());

			Object content = msg.getContent();
			if (content instanceof Multipart) {
				final Multipart multiPart = (Multipart) content;
				for (int i = 0; i < multiPart.getCount(); i++) {
					final Part part = multiPart.getBodyPart(i);
					final String contentType = part.getContentType();
					if (contentType.indexOf("text/html") != -1) {
						Document doc = Jsoup
								.parse(part.getContent().toString());
						mail.setAgentName(doc.select(AGENT_NAME_CSSSELECTOR)
								.html());
						mail.setOppositeAgentName(doc.select(
								OPS_AGENT_NAME_CSSSELECTOR).html());
						Elements elements = doc.getElementsByAttributeValue(
								"style",
								"padding-top: 1em; padding-bottom: 1em;");
						for (Element element : elements) {
							Portal portal = new Portal();
							portal.setPortalName(element.select(
									"div:nth-child(1)").html());
							String intelUrl = element
									.select("div:nth-child(2) > a").get(0)
									.attr("href");
							portal.setPortalIntelUrl(intelUrl);
							setPortalLatLng(portal, intelUrl);
							mail.getPortals().add(portal);
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new MailParseException(e);
		}
		return mail;
	}

	private void setPortalLatLng(Portal portal, String intelUrl) {
		try {
			URL url = new URL(intelUrl);
			String query = url.getQuery();
			String[] params = query.split("&");
			for (String param : params) {
				if (param.indexOf("pll") != -1) {
					String[] latlng = param.split("=")[1].split(",");
					// latitude
					portal.setLatitude(latlng[0]);
					// longitude
					portal.setLongitude(latlng[1]);
					break;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
